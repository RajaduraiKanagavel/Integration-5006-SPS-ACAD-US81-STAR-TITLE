package com.oup.integration.sps.acad.titlemaster.routes;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFileOperationFailedException;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.GroupedBodyAggregationStrategy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.gson.JsonSyntaxException;

import com.oup.integration.sps.acad.titlemaster.processor.SAPTitleFileGenerationProcessor;

import com.oup.integration.sps.acad.titlemaster.biblio.pojo.StarTitleBiblioGroup;
import com.oup.integration.sps.acad.titlemaster.processor.StarFileProcessor;
import com.oup.integration.sps.acad.titlemaster.star.pojo.StarTitleMasterDataUS81;



@Component
public class MainRouteUS81 extends RouteBuilder {
	
	BindyCsvDataFormat bindy = new BindyCsvDataFormat(StarTitleMasterDataUS81.class);

	@Override
	public void configure() throws Exception {
		
		onException(JsonSyntaxException.class, InvalidFormatException.class, IllegalStateException.class, NullPointerException.class, IllegalArgumentException.class, NumberFormatException.class, StringIndexOutOfBoundsException.class)
			.maximumRedeliveries(0)
			.log(LoggingLevel.ERROR, "com.oup.sps", "Exceptions due to data issues in the ${header.InterfaceFullName} message processing in Route ${routeId} .\n Exception Message: ${exchangeProperty.CamelExceptionCaught}")
			.setBody(simple("Exceptions due to data issues in the ${header.InterfaceFullName} message processing in Route ${routeId} .\\n Exception Message: ${exchangeProperty.CamelExceptionCaught}"))
			.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{property.ReceivedFileName}_$simple{header.RequestReceivedTime}.txt")
			.handled(true);
			
		onException(IOException.class, GenericFileOperationFailedException.class, FileSystemException.class, FileAlreadyExistsException.class)
			.maximumRedeliveries(3)
			.maximumRedeliveryDelay(300000)
			.log(LoggingLevel.ERROR, "com.oup.sps", "Exception due to IO operations in ${header.InterfaceFullName} message processing in Route ${routeId} .\\n Exception Message: ${exchangeProperty.CamelExceptionCaught}")
			.setBody(simple("Exception due to IO operations in ${header.InterfaceFullName} message processing in Route ${routeId} .\\n Exception Message: ${exchangeProperty.CamelExceptionCaught}"))
			.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{property.ReceivedFileName}_$simple{header.RequestReceivedTime}.txt")
			.handled(true);
		
		onException(Exception.class)
			.maximumRedeliveries(0)
			.log(LoggingLevel.ERROR, "com.oup.sps", "Exception occurred in ${header.InterfaceFullName} message processing in Route ${routeId} .\\n Exception Message: ${exchangeProperty.CamelExceptionCaught}")
			.setBody(simple("Exception occurred in ${header.InterfaceFullName} message processing in Route ${routeId}.\\n Exception Message: ${exchangeProperty.CamelExceptionCaught}"))
			.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{property.ReceivedFileName}_$simple{header.RequestReceivedTime}.txt")
			.handled(true);
		
		from("direct:ReceivedStarTitleAggregatedMessage")
		.routeId("mainRouteForStarTitle")
		.log(LoggingLevel.INFO, "com.oup.sps", "Received Aggregated ${header.InterfaceFullName} Message ${body}")
		.setProperty("InputBody", simple("${body}"))
		.convertBodyTo(String.class)
		.wireTap("file:{{file.backup.location}}/1.0 ReceivedFromBiblio?fileName=${date:now:yyyy/MM/dd/}$simple{header.InterfaceName}_$simple{header.RequestReceivedTime}.dat")
		.choice()
			.when(header("Region").isEqualTo("UK"))
				.to("controlbus:route?async=true&routeId=StarRouteUS81&action=stop")
			.when(header("Region").isEqualTo("US"))
				.to("controlbus:route?async=true&routeId=StarRouteUS81&action=stop")
		.end()
		.to("direct:GetStarTitleLatestSequenceNumber")
		.log(LoggingLevel.INFO, "com.oup.sps", "Current ${header.InterfaceFullName} Sequence Number being processed : ${header.SequenceNumber}")
		.setHeader("DestinationFileName", simple("${header.InterfaceName}${header.SequenceNumber}"))
		.setBody(simple("${property.InputBody}"))
		.convertBodyTo(String.class)
		.unmarshal().json(JsonLibrary.Jackson, StarTitleBiblioGroup.class)
		.log(LoggingLevel.INFO, "com.oup.sps", "${header.InterfaceFullName} Split Started for file ${header.DestinationFileName}")			
		.split(body(), new GroupedBodyAggregationStrategy())
			.streaming()
			.log(LoggingLevel.INFO, "com.oup.sps", "Body ${body}")
			
			.to("direct:transform_titledata_biblio_to_star")
		.end()
		
		.log(LoggingLevel.INFO, "com.oup.sps", "Debug 9999 : ${body}")
		.marshal(bindy)
		.log(LoggingLevel.INFO, "com.oup.sps", "Generated CSV message for ${header.InterfaceFullName} interface : ${body}")
		
		.process(new SAPTitleFileGenerationProcessor())
		.setBody(simple("${header.fileHeader}${body}${header.fileFooter}"))
		.wireTap("file:{{file.backup.location}}/2.0 SentToStar?fileName=${date:now:yyyy/MM/dd/}$simple{header.DestinationFileName}_$simple{header.RequestReceivedTime}.dat")
		.choice()
			.when(header("Region").isEqualTo("UK"))
				/*.toD("ftp:{{ftp.uk.server}}:{{ftp.uk.port}}{{ftp.uk.drop.location}}?FileName=$simple{header.DestinationFileName}.dat&doneFileName=$simple{header.DestinationFileName}.go&password={{ftp.uk.password}}&username={{ftp.uk.username}}&passiveMode=true")*/
			.when(header("Region").isEqualTo("US"))
				.toD("ftp:{{ftp.us.server}}:{{ftp.us.port}}{{ftp.us.drop.location}}?FileName=$simple{header.DestinationFileName}.dat&doneFileName=$simple{header.DestinationFileName}.go&password={{ftp.us.password}}&username={{ftp.us.username}}&passiveMode=true")
		.end()
		.log(LoggingLevel.INFO, "com.oup.sps", "${header.InterfaceFullName} file ${header.DestinationFileName} generated")
		.to("direct:UpdateStarSequenceNumber")
		.log(LoggingLevel.INFO, "com.oup.sps", "${header.InterfaceFullName} Sequence number updated");
			
}
			
}
