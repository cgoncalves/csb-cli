package pt.it.av.atnog.csb_client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.it.av.atnog.csb_client.entity.ApplicationCreateResponse;
import pt.it.av.atnog.csb_client.entity.ApplicationDeleteResponse;
import pt.it.av.atnog.csb_client.entity.ApplicationRestartResponse;
import pt.it.av.atnog.csb_client.entity.ApplicationStartResponse;
import pt.it.av.atnog.csb_client.entity.ApplicationStatusResponse;
import pt.it.av.atnog.csb_client.entity.CsbEntityFactory;
import pt.it.av.atnog.csb_client.entity.DeployApp;
import pt.it.av.atnog.csb_client.entity.Manifest;
import pt.it.av.atnog.csb_client.entity.ManifestResponse;
import pt.it.av.atnog.csb_client.entity.PaasProvider;
import pt.it.av.atnog.csb_client.entity.PaasProviders;
import pt.it.av.atnog.csb_client.entity.Response;

/**
 * Cloud Service Broker client
 * 
 * @author <a href="mailto:cgoncalves@av.it.pt">Carlos Gon&ccedil;alves</a>
 */
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	private static final String APP_HEADER = "Cloud Service Broker Client\n---";
	private static final String USAGE = "java -jar csb_client-0.0.1-SNAPSHOT-jar-with-dependencies.jar";
	private static final String OPTIONS_HEADER = "---\n- Nothing yet\n" + "---\nOptions:";

	public static final String PROPERTIES_FILE_NAME = "/pt/it/av/atnog/csb_client/client.properties";
	private static PropertiesConfiguration propConfig;

	private static String SERVER_URI;
	private static String MANIFEST_URI;
	private static String OFFERINGS_URI;
	private static String CREATE_APP_URI;
	private static String DEPLOY_APP_URI;
	private static String START_APP_URI;
	private static String STOP_APP_URI;
	private static String RESTART_APP_URI;
	private static String DELETE_APP_URI;
	private static String STATUS_APP_URI;

	private static ClientRequest request;

	/**
	 * The application <code>main</code> method.
	 * 
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(final String args[]) {
		// Override the main thread name
		Thread.currentThread().setName("main");

		// Load client properties
		try {
			loadConfig();
		} catch (ConfigurationException ce) {
			logger.error("Cloud not load client configurations.\nError: {}", new Object[] { ce.getMessage() });
		}

		// create the command line parser
		final CommandLineParser parser = new PosixParser();

		// create the CLI options
		final Options options = createCliOptions();

		// parse the command line arguments
		try {
			// parse the command line arguments
			final CommandLine line = parser.parse(options, args);

			// get the path(s) to corpus directories and files
			final List<String> argList = line.getArgList();

			// check for mandatory arguments or the help flag
			if (line.hasOption("help")) {
				printHelpMessage(options);
				return;
			}

			if (line.hasOption("manifest")) {
				if (line.getOptionValue("manifest") == null || line.getOptionValue("manifest").isEmpty()) {
					printHelpMessage(options);
				}

				evaluateManifest(line.getOptionValue("manifest"));

			} else if (line.hasOption("list-paas")) {
				listPaasProviders();
			} else if (line.hasOption("start-app")) {
				if (line.getOptionValue("start-app") == null || line.getOptionValue("start-app").isEmpty()) {
					printHelpMessage(options);
				}

				startApp(line.getOptionValue("start-app"));
			} else if (line.hasOption("stop-app")) {
				if (line.getOptionValue("stop-app") == null || line.getOptionValue("stop-app").isEmpty()) {
					printHelpMessage(options);
				}

				stopApp(line.getOptionValue("stop-app"));
			} else if (line.hasOption("restart-app")) {
				if (line.getOptionValue("restart-app") == null || line.getOptionValue("restart-app").isEmpty()) {
					printHelpMessage(options);
				}

				restartApp(line.getOptionValue("restart-app"));
			} else if (line.hasOption("delete-app")) {
				if (line.getOptionValue("delete-app") == null || line.getOptionValue("delete-app").isEmpty()) {
					printHelpMessage(options);
				}

				deleteApp(line.getOptionValue("delete-app"));
			} else if (line.hasOption("deploy-app")) {
				if (argList.size() != 3) {
					printHelpMessage(options);
					throw new MissingArgumentException("Argument malformated. Expected args: <filePath> <appID> <PaaS>");
				}

				deployApp(argList.get(0), argList.get(1), argList.get(2));
				
			} else if (line.hasOption("create-app")) {
				if (line.getOptionValue("create-app") == null || line.getOptionValue("create-app").isEmpty()) {
					printHelpMessage(options);
				}
				createApp(line.getOptionValue("create-app"));
				
			} else if (line.hasOption("status-app")) {
				if (argList.size() != 1) {
					printHelpMessage(options);
					throw new MissingArgumentException("Argument malformated. Expected args: <appID>");
				}

				statusApp(argList.get(0));
			} else {
				printHelpMessage(options);
			}

		} catch (Exception e) {
			System.err.printf("Unexpected exception: %s\n", e.getMessage());
			e.printStackTrace(System.err);
		}

	}

	private static void printResponse(Response response) {
		logger.info("\nApp ID:\t{}\nStatus:\t{}", new Object[] { response.getAppID(), response.getStatus() });
	}
	
	private static void createApp(final String appId) throws Exception {
		String createAppUri = getUriByQuery(CREATE_APP_URI, appId);
		ClientRequest request = new ClientRequest(createAppUri);
		ApplicationCreateResponse response = request.post(ApplicationCreateResponse.class).getEntity();
		
		logger.info("\nAppId:\t{}" +
					"\nStatus:\t{}" +
					"\nURL:\t{}",
					new Object[] { response.getAppID(), response.getAppStatus(), response.getAppUrl()
					});
	}

	private static void statusApp(final String appId) throws Exception {
		String statusAppUri = getUriByQuery(STATUS_APP_URI, appId);
		ClientRequest request = new ClientRequest(statusAppUri);
		ApplicationStatusResponse response = request.get(ApplicationStatusResponse.class).getEntity();
		
		logger.info("\nAppId:\t{}" +
					"\nStatus:\t{}" +
					"\nURL:\t{}",
					new Object[] { response.getAppID(), response.getAppStatus(), response.getAppUrl()
					});
	}

	private static void startApp(final String appId) throws Exception {
		String createAppUri = getUriByQuery(CREATE_APP_URI, appId);
		ClientRequest request = new ClientRequest(createAppUri);
		ApplicationStartResponse response = request.post(ApplicationStartResponse.class).getEntity();
		
		logger.info("\nAppId:\t{}" +
					"\nStatus:\t{}" +
					"\nURL:\t{}",
					new Object[] { response.getAppID(), response.getAppStatus(), response.getAppUrl()
					});
	}

	private static void stopApp(final String appId) throws Exception {
		String createAppUri = getUriByQuery(CREATE_APP_URI, appId);
		ClientRequest request = new ClientRequest(createAppUri);
		ApplicationDeleteResponse response = request.post(ApplicationDeleteResponse.class).getEntity();
		
		logger.info("\nAppId:\t{}" +
					"\nStatus:\t{}" +
					"\nURL:\t{}",
					new Object[] { response.getAppID(), response.getAppStatus(), response.getAppUrl()
					});
	}

	private static void restartApp(final String appId) throws Exception {
		String createAppUri = getUriByQuery(CREATE_APP_URI, appId);
		ClientRequest request = new ClientRequest(createAppUri);
		ApplicationRestartResponse response = request.post(ApplicationRestartResponse.class).getEntity();
		
		logger.info("\nAppId:\t{}" +
					"\nStatus:\t{}" +
					"\nURL:\t{}",
					new Object[] { response.getAppID(), response.getAppStatus(), response.getAppUrl()
					});
	}

	private static void deleteApp(final String appId) throws Exception {
		ClientRequest request = new ClientRequest(DELETE_APP_URI);
		request = request.formParameter("appID", appId);

		ClientResponse<Response> response = request.post(Response.class);

		printResponse(response.getEntity());
	}

	private static void deployApp(final String filePath, final String appId, final String paas) throws Exception {
		CsbEntityFactory factory = new CsbEntityFactory();

		PaasProvider pp = factory.createPaasProvider();
		pp.setName(paas);

		DeployApp deployApp = factory.createDeployApp();
		deployApp.setPaasProvider(pp);
		deployApp.setAppID(appId);

		// marshal deployapp
		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(DeployApp.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		m.marshal(deployApp, out);

		System.out.println(out.toString());

		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(DEPLOY_APP_URI);
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT);

		ContentBody deployXml = new StringBody(out.toString(), "application/xml", Charset.forName("UTF-8"));
		ContentBody appFile = new FileBody(new File(filePath), "application/octet-stream");

		entity.addPart("app", appFile);
		entity.addPart("xml", deployXml);

		post.setEntity(entity);
		HttpResponse httpResponse = client.execute(post);

		// ClientRequestFactory crf = new ClientRequestFactory();
		// ClientRequest request = crf.createRequest(DEPLOY_APP_URI);
		// request.accept(MediaType.APPLICATION_XML);
		//
		// MultipartFormDataOutput form = new MultipartFormDataOutput();
		// form.addFormData("app", out.toString(),
		// MediaType.APPLICATION_XML_TYPE);
		// form.addFormData("xml", new FileBody(new File(filePath)),
		// MediaType.APPLICATION_OCTET_STREAM_TYPE);
		//
		// request.body(MediaType.MULTIPART_FORM_DATA_TYPE, form);
		//
		// ClientResponse<DeployAppResponse> response =
		// request.post(DeployAppResponse.class);

		// // create JAXB context and instantiate unmarshaller
		String responseEntity = EntityUtils.toString(httpResponse.getEntity(), Charset.defaultCharset().toString());

		JAXBContext jc = JAXBContext.newInstance(Response.class);
		Unmarshaller u = jc.createUnmarshaller();
		Response response = (Response) u.unmarshal(new StringReader(responseEntity));

		printResponse(response);
	}

	private static void listPaasProviders() throws Exception {
		request = new ClientRequest(OFFERINGS_URI);
		request.accept(MediaType.APPLICATION_XML);

		ClientResponse<PaasProviders> response = request.get(PaasProviders.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed: HTTP error code : " + response.getStatus());
		}

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(PaasProviders.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		m.marshal(response.getEntity(), System.out);
	}

	private static void evaluateManifest(final String xmlFilePath) throws Exception {

		JAXBContext jc = JAXBContext.newInstance(Manifest.class);
		Unmarshaller u = jc.createUnmarshaller();
		Manifest manifest = (Manifest) u.unmarshal(new FileInputStream(xmlFilePath));

		request = new ClientRequest(MANIFEST_URI);
		request.accept(MediaType.APPLICATION_XML);
		request.body(MediaType.APPLICATION_XML, manifest);

		ClientResponse<ManifestResponse> response = request.post(ManifestResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(ManifestResponse.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		m.marshal(response.getEntity(), System.out);
	}
	
	private static String getUriByQuery(String uri, String... args) {
	    return MessageFormat.format(uri, (Object[]) args);
    }

	private static void printHelpMessage(final Options options) {
		System.out.println(APP_HEADER);
		final HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.setWidth(80);
		helpFormatter.printHelp(USAGE, OPTIONS_HEADER, options, null);
	}

	private static Options createCliOptions() {
		final Options options = new Options();
		options.addOption("h", "help", false, "print this message");
		options.addOption("ca", "create-app", true, "create an app");
		options.addOption("da", "deploy-app", false, "deploy an app");
		options.addOption("sa", "start-app", true, "start an app");
		options.addOption("spa", "stop-app", true, "stop an app");
		options.addOption("ra", "restart-app", true, "restart an app");
		options.addOption("dla", "delete-app", true, "delete an app");
		options.addOption("sta", "status-app", false, "get an app status");
		options.addOption("m", "manifest", true, "best matching cloud providers given a manifest");
		options.addOption("lp", "list-paas", false, "list all available PaaS providers");

		return options;
	}

	private static void loadConfig() throws ConfigurationException {
		InputStream inputStream = App.class.getResourceAsStream(PROPERTIES_FILE_NAME);
		propConfig = new PropertiesConfiguration();
		propConfig.load(inputStream);

		SERVER_URI = propConfig.getString("server_uri");
		System.out.println("SERVER URI: " + SERVER_URI);

		MANIFEST_URI = SERVER_URI + propConfig.getString("manifest_uri");
		OFFERINGS_URI = SERVER_URI + propConfig.getString("offerings_uri");
		CREATE_APP_URI = SERVER_URI + propConfig.getString("create_app_uri");
		DEPLOY_APP_URI = SERVER_URI + propConfig.getString("deploy_app_uri");
		START_APP_URI = SERVER_URI + propConfig.getString("start_app_uri");
		STOP_APP_URI = SERVER_URI + propConfig.getString("stop_app_uri");
		RESTART_APP_URI = SERVER_URI + propConfig.getString("restart_app_uri");
		DELETE_APP_URI = SERVER_URI + propConfig.getString("delete_app_uri");
		STATUS_APP_URI = SERVER_URI + propConfig.getString("status_app_uri");
	}

}
