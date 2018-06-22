package RestTests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import Data.Users;
import restApiClient.RestClient;
import restApiPage.TestBase;

public class RestClientPostTest extends TestBase {
	TestBase t;
String url;
String serviceUrl;
String apiUrl;
CloseableHttpResponse httpResponse;
//HashMap<String,String>HeaderMap

@BeforeMethod
public void setUp() throws ClientProtocolException, IOException
{
 t=new  TestBase();
serviceUrl=prop.getProperty("URL");
apiUrl=prop.getProperty("ServiceURL");
 url=serviceUrl+apiUrl;
}
@Test
public void postUrl() throws ClientProtocolException, IOException
{
	RestClient r=new RestClient();
	//headrer
	HashMap <String,String> headerMap=new HashMap<String,String>();
	headerMap.put("Content-Type", "application/json");//expected users
	//jacksonApi
	ObjectMapper mapper=new ObjectMapper();
	Users users=new Users("morpheus","leader");
	//object to Json
	mapper.writeValue(new File("C:\\Users\\sunit_000\\Desktop\\testing\\RestApiTest\\src\\main\\java\\Data\\users.json"),users);
	//object to Json String
	String jsonString=mapper.writeValueAsString(users);
	System.out.println("Object to Json String"+jsonString);
	httpResponse=r.Post(url, jsonString, headerMap);
	//1.status code
	int statusCode=httpResponse.getStatusLine().getStatusCode();
	Assert.assertEquals(statusCode, t.response_status_201);
	//json String
	String responseString=EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
	JSONObject responseJson=new JSONObject(responseString);
	System.out.println("The Response from API"+responseJson);
	//json to java object
	Users usersResObj=mapper.readValue(responseString,Users.class);
	System.out.println("UserResPonseObject........"+usersResObj);
	Assert.assertTrue(users.getName().equals(usersResObj.getName()));
	Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
	System.out.println(usersResObj.getId());
	System.out.println(usersResObj.getCreatedAt());
	System.out.println("UserResPonseObject........"+usersResObj.getClass());
	
	}
	

}
