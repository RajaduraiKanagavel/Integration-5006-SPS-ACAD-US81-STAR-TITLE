package com.oup.integration.sps.acad.titlemaster.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.oup.integration.sps.acad.startitle.strategy.aggregation.BiblioAggregationStrategy;

@Component("sapRouteUK")
public class StarRouteUS81 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("activemq:{{activemq.us.queuename}}")
		.routeId("StarTitleSchedulerRouteUS")
		/*.autoStartup(false)
		.routePolicyRef("startPolicyUK")*/
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
				.to("controlbus:route?async=true&routeId=StarTitleSchedulerRouteUS&action=stop")
				.convertBodyTo(String.class)
				.to("file:{{file.backup.location}}/Error?fileName=${date:now:yyyy/MM/dd/}$simple{header.InterfaceName}_$simple{header.RequestReceivedTime}.json&fileExist=Append")
			.otherwise()					
				.to("direct:ReceivedStarTitleAggregatedMessage")
		.end();
		
	}

}
