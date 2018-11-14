package com.oup.integration.sps.acad.startitle.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.oup.integration.sps.acad.startitle.strategy.aggregation.BiblioAggregationStrategy;

@Component("StarRouteUS81")
public class StarRouteUS81 extends RouteBuilder {
	
	@Value("${scheduler.route.autoStartup:false}")
	private boolean autoStartup;

	@Override
	public void configure() throws Exception {
		
		from("activemq:{{activemq.us.queuename}}")
		.routeId("StarRouteUS81")
		.autoStartup(autoStartup)
		.routePolicyRef("startPolicyUS")
		.log(LoggingLevel.INFO, "com.oup.sps", "Adding UK SAPTitle Message--- ${body}")
		.aggregate(new BiblioAggregationStrategy())
		.constant(true)
		.completionTimeout(30000)
		.setHeader("RequestDate", simple("${date:now:ddMMyyyy}"))
		.setHeader("RequestTime", simple("${date:now:HHmmss}"))
		.setHeader("RequestReceivedTime", simple("${date:now:HHmmssSSS}"))
		.setHeader("InterfaceName", simple("us81"))
		.setHeader("Region", simple("US"))
		.setHeader("InterfaceFullName", simple("ACAD US81 StarTitle"))
		.choice()
			.when(simple("${property.ErrorOccured} == 'true'"))
				.log(LoggingLevel.ERROR, "com.oup.sps", "Exception occurred while aggregating the Biblio json message in $simple{header.InterfaceName} interface.The application processing has been terminated")
				.to("controlbus:route?async=true&routeId=StarRouteUS81&action=stop")
				.convertBodyTo(String.class)
				.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{header.InterfaceName}_$simple{header.RequestReceivedTime}.json&fileExist=Append")
			.otherwise()					
				.to("seda:ReceivedStarTitleAggregatedMessage")
		.end();
		
	}

}
