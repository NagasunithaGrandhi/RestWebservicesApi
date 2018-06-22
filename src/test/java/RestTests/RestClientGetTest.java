package RestTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import RestUtil.TestUtil;
import restApiClient.RestClient;
import restApiPage.TestBase;

public class RestClientGetTest extends TestBase
{
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
@Test(priority=1)
public void getApisWithoutHeader() throws ClientProtocolException, IOException
{
	RestClient r=new RestClient();
	httpResponse=r.get(url);	
	
	//a.StatusCode
	int statusCode=httpResponse.getStatusLine().getStatusCode();
	System.out.println("StatusCode="+statusCode);
	Assert.assertEquals(statusCode,response_status_200,"the respose is wrong");
	
	//b.Json String
	String responseString =EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
	JSONObject jsonObject=new JSONObject(responseString);
	String perPageValue=TestUtil.getValueByJpath(jsonObject,"/per_page");
	System.out.println("The Json String...."+perPageValue);
	Assert.assertEquals(Integer.parseInt(perPageValue), 3);
	String totalValue=TestUtil.getValueByJpath(jsonObject,"/total");
	System.out.println("The Json String...."+totalValue);
	Assert.assertEquals(Integer.parseInt(totalValue),12);
	
	//get the data fron JSon 
	
	String last_name=TestUtil.getValueByJpath( jsonObject,"/data[0]/last_name");
	String id=TestUtil.getValueByJpath( jsonObject,"/data[0]/id");
	String avatar=TestUtil.getValueByJpath( jsonObject,"/data[0]/avatar");
	String first_name=TestUtil.getValueByJpath( jsonObject,"/data[0]/first_name");
	System.out.println("Last_Name="+last_name+"......id="+id+"....avatar="+avatar+"......First_Name="+first_name);
	//String lat_name=TestUtil.getValueByJpath( jsonObject, "/data[0]/last_name");
	
		//c. Header
	Header[] headerArray=httpResponse.getAllHeaders();
	HashMap<String,String> allHeaders=new HashMap<String,String>();
	for(Header header:headerArray)
	{
		allHeaders.put(header.getName(), header.getValue());
	}
	System.out.println("Headers array----"+allHeaders);
}

@Test(priority=2)
public void getApisWithHeaders() throws ClientProtocolException, IOException
{
	RestClient r=new RestClient();
	HashMap<String,String>HeaderMap=new HashMap<String,String>();
	HeaderMap.put("Content-Type", "application/json");
	//HeaderMap.put("username", "test");
	//HeaderMap.put("password", "test123");
	//HeaderMap.put("AuthToken", "rewst");
	httpResponse=r.get(url,HeaderMap);
	System.out.println(httpResponse);
	
	//a.StatusCode
	int statusCode=httpResponse.getStatusLine().getStatusCode();
	System.out.println("StatusCode="+statusCode);
	Assert.assertEquals(statusCode,response_status_200,"the respose is wrong");
	
	//b.Json String
	String responseString =EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
	JSONObject jsonObject=new JSONObject(responseString);
	String perPageValue=TestUtil.getValueByJpath(jsonObject,"/per_page");
	System.out.println("The Json String...."+perPageValue);
	Assert.assertEquals(Integer.parseInt(perPageValue), 3);
	String totalValue=TestUtil.getValueByJpath(jsonObject,"/total");
	System.out.println("The Json String...."+totalValue);
	Assert.assertEquals(Integer.parseInt(totalValue),12);
	
	//get the data fron JSon 
	
	String last_name=TestUtil.getValueByJpath( jsonObject,"/data[0]/last_name");
	String id=TestUtil.getValueByJpath( jsonObject,"/data[0]/id");
	String avatar=TestUtil.getValueByJpath( jsonObject,"/data[0]/avatar");
	String first_name=TestUtil.getValueByJpath( jsonObject,"/data[0]/first_name");
	System.out.println("Last_Name="+last_name+"......id="+id+"....avatar="+avatar+"......First_Name="+first_name);
	//String lat_name=TestUtil.getValueByJpath( jsonObject, "/data[0]/last_name");
	
		//c. Header
	Header[] headerArray=httpResponse.getAllHeaders();
	HashMap<String,String> allHeaders=new HashMap<String,String>();
	for(Header header:headerArray)
	{
		allHeaders.put(header.getName(), header.getValue());
	}
	System.out.println("Headers array----"+allHeaders);
}
}
