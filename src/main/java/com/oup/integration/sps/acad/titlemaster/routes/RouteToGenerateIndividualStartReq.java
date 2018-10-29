package com.oup.integration.sps.acad.titlemaster.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.dozer.MappingException;
import org.springframework.stereotype.Component;

import com.oup.integration.sps.acad.titlemaster.star.pojo.StarTitleMasterDataUS81;

@Component
public class RouteToGenerateIndividualStartReq extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		onException(MappingException.class, IllegalStateException.class, NullPointerException.class,
				NumberFormatException.class).maximumRedeliveries(0).log(LoggingLevel.ERROR, "com.oup.sps",
						"Exceptions due to data issues in the file processing in ${routeId} .\n Exception Message: ${exchangeProperty.CamelExceptionCaught}")
						.setBody(simple(
								"Exceptions due to data issues in the file processing in ${routeId} .\n Exception Message: ${exchangeProperty.CamelExceptionCaught}"))
						.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{header.DestinationFileName}_$simple{header.RequestReceivedTime}.json")
						.handled(true);

		onException(Exception.class).maximumRedeliveries(0).log(LoggingLevel.ERROR, "com.oup.sps",
				"Exception occurred while processing messages in ${routeId} Exception Message: ${exchangeProperty.CamelExceptionCaught}")
				.setBody(simple(
						"Exception occurred while processing messages in ${routeId} Exception Message: ${exchangeProperty.CamelExceptionCaught}"))
				.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{header.DestinationFileName}_$simple{header.RequestReceivedTime}.json")
				.handled(true);

		from("direct:transform_titledata_biblio_to_star").routeId("routeToGenerateStarReq")
				
/*
				.process(e -> {

					com.oup.integration.sps.acad.titlemaster.star.pojo.StarTitleMasterDataUS81 obj = (StarTitleMasterDataUS81) e
							.getIn().getBody();

					obj.setSubject1(obj.getSubject1() + obj.getSubject2() + obj.getSubject3());

				})*/

				.to("dozer:Biblio_Star_Transformationus81?sourceModel=com.oup.integration.sps.acad.titlemaster.biblio.pojo.StarTitleMasterDataBiblioUS81&targetModel=com.oup.integration.sps.acad.titlemaster.star.pojo.StarTitleMasterDataUS81&mappingFile=Transformations/Biblio_To_Star_TransformationUS81.xml");

	}

}
