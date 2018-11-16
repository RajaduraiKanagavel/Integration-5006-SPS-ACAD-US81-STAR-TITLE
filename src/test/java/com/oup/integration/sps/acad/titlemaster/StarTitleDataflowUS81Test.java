package  com.oup.integration.sps.acad.titlemaster;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oup.integration.sps.acad.startitle.biblio.pojo.StarTitleMasterDataBiblioUS81;

@RunWith(CamelSpringBootRunner.class)
//@RunWith(CamelSpringBootJUnit4ClassRunner.class) 
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) // ensure that the CamelContext, routes, and mock
																// endpoints are reinitialized between test methods.
@MockEndpointsAndSkip("ftp:{{ftp.us.server}}:{{ftp.us.port}}{{ftp.us.drop.location}}?*|file:{{file.backup.location}}?*") // All
																															// invoked.
@MockEndpoints // All endpoints are sniffed and recorded in a mock endpoint.
@UseAdviceWith
public class StarTitleDataflowUS81Test {

	@Autowired
	private CamelContext camelContext;

	@EndpointInject(uri = "activemq:{{activemq.us.queuename}}")
	protected ProducerTemplate acriveMQ;

	@EndpointInject(uri = "mock:ftp:{{ftp.us.server}}:{{ftp.us.port}}{{ftp.us.drop.location}}")
	private MockEndpoint dropLocation;

	private FakeFtpServer fakeFtpServer;

	@Value("${ftp.us.port}")
	private int ftpPort;

	@Value("${ftp.us.username}")
	private String ftpUsername;

	@Value("${ftp.us.password}")
	private String ftpPassword;

	@Value("${ftp.us.drop.location}")
	private String ftpDropLocation;

	@Autowired
	private JdbcTemplate jdbcTemplete;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		fakeFtpServer = new FakeFtpServer();
		fakeFtpServer.setServerControlPort(ftpPort); // use any free port
		UnixFakeFileSystem fileSystem = new UnixFakeFileSystem();
		fileSystem.add(new DirectoryEntry(ftpDropLocation));
		fakeFtpServer.setFileSystem(fileSystem);
		UserAccount userAccount = new UserAccount(ftpUsername, ftpPassword, "/");
		fakeFtpServer.addUserAccount(userAccount);
		fakeFtpServer.start();

		camelContext.getRouteDefinition("StarRouteUS81").autoStartup(true).routePolicyRef(null);
		camelContext.start();
	}

	@Test
	public void singleMessage() throws InterruptedException, IOException {

		Double d=new Double(11.6);

		List<StarTitleMasterDataBiblioUS81> inputList = new ArrayList<>();
		inputList.add(new StarTitleMasterDataBiblioUS81("productISBN", d,  "fullTitle",  "subTitle",
				 101,  d,  "true",  "formatDescription",
				 "oxfordSubjectCode", " referenceSubject", " additionalReferenceSubject",
				 "onlineProductSubjectCode",  "onlineProductSubjectDescription", " audienceCode",
				 "audienceDescription",  "editorName",  55.5, " authorBigraphy",  d,
				 "seriesCode",  "seriesName",  "true", " previousEditionPubDate1",
				 "previousEditionPubDate2",  "previousEditionPubDate3", " institutionalAccess",
				 "retailMarketApproval",  "previewOrSearch", " workID",  "productRelationCode",
				 "memberOfOnlineProductCode") );
		
		
	

		String message = objectMapper.writeValueAsString(inputList);
		
		
		

		acriveMQ.sendBody(message);

		Thread.sleep(50000);

		dropLocation.assertIsSatisfied();
		dropLocation.expectedMessageCount(1);

		Exchange exchange = dropLocation.getExchanges().get(0);
		validateMessage(exchange, inputList);

		FileSystem fileSystem = fakeFtpServer.getFileSystem();
		

	}

	private void validateMessage(Exchange exchange, List<StarTitleMasterDataBiblioUS81> aggregatedList) {

		String[] values = exchange.getIn().getBody(String.class).split("\n");

		System.out.print("SPLITTED MESSAGE" + values[0]);
		assertTrue(values.length > 1);

		// Header*/
		String firstrow = values[0];
		String[] firstrowcolumns = firstrow.split(",");
		assertThat("Wrong sequence number or date", firstrow,
				startsWith("us810001 " + new SimpleDateFormat("ddMMyyyy").format(new Date()) + " "));
		assertThat("Header Count not match", firstrow, endsWith(",,,,"));

		// Footer
		String lastrow = values[values.length - 1];
		assertEquals("ZZRC=00000000" + values.length + ",,,,", lastrow);
		assertEquals(aggregatedList.size(), values.length - 2);

		
	}

	@After
	public void tearDown() throws Exception {
		File dropsql = ResourceUtils.getFile("classpath:drop.sql");
		String[] sqls = new String(Files.readAllBytes(dropsql.toPath())).split(";");
		for (String sql : sqls) {
			jdbcTemplete.execute(sql);
		}
		fakeFtpServer.stop();
	}
}
