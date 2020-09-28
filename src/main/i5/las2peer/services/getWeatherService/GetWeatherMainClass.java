package i5.las2peer.services.getWeatherService;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import i5.las2peer.api.Context;
import i5.las2peer.restMapper.RESTService;
import i5.las2peer.restMapper.annotations.ServicePath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

// TODO Describe your own service
/**
 * las2peer-Template-Service
 * 
 * This is a template for a very basic las2peer service that uses the las2peer WebConnector for RESTful access to it.
 * 
 * Note: If you plan on using Swagger you should adapt the information below in the SwaggerDefinition annotation to suit
 * your project. If you do not intend to provide a Swagger documentation of your service API, the entire Api and
 * SwaggerDefinition annotation should be removed.
 * 
 */
// TODO Adjust the following configuration
@Api
@SwaggerDefinition(
		info = @Info(
				title = "las2peer Template Service",
				version = "1.0.0",
				description = "A las2peer Template Service for demonstration purposes.",
				termsOfService = "",
				contact = @Contact(
						name = "Lan Anh",
						url = "facebook",
						email = "mail"),
				license = @License(
						name = "",
						url = "")))
@ServicePath("weather")
// TODO Your own service class
public class GetWeatherMainClass extends RESTService {
	
	
	public GetWeatherMainClass() {
		
	}
	
	// get Weather Service's Template
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public Response getWeatherTeamplate() throws Exception{
		
		String onAction = "retrieving HTML";
		try {
			// load template
			Scanner scanner;
			
			// scanner all html's contents
			scanner = new Scanner(new File("./etc/frontEnd/index.html"));
			String html = "";
			
			//catch the first character of hmtl's content
			html = scanner.useDelimiter("\\A").next();
			scanner.close();
		
			// finally return resulting HTML
			return Response.status(Status.OK).entity(html).build();
		} catch (Exception e) {
			e.printStackTrace();
			return internalError(onAction);
		}
	}

	// Get weather's info of one location 
	@GET
	@Path("/getTemp/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeather(@PathParam("location") String location) {
		
		  OkHttpClient client = new OkHttpClient();
		 
		  Gson gson = new Gson();
		 
		  String API_KEY = "347e72f54a7cde54465418abd431fcf0";
		  
		  Request urlString = new Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY).build();
		  
		  JsonResult data = null;
		  
		  String onAction = "retrieving HTML";

	      try {

	    	okhttp3.Response response = client.newCall(urlString).execute();
	    
	        ResponseBody curWeather = response.body();
	        
	        data = gson.fromJson(curWeather.string(), JsonResult.class);
	        
	        
	        Scanner scanner;
			scanner = new Scanner(new File("./etc/frontEnd/index.html"));
			String html = "";
			html = scanner.useDelimiter("\\A").next();
			scanner.close();
			
			
			
			return Response.status(Status.OK).entity(data).build();
	      } catch (Exception e) {
	            e.printStackTrace();
	            return internalError(onAction);
	  	  }	     
	}
	
			

	// return response's error 
	private Response internalError(String onAction) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal error while " + onAction + "!")
				.type(MediaType.TEXT_PLAIN).build();
	}
	
	
	
	
}
