package restApiClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{
	//without headers
	public CloseableHttpResponse  get(String url) throws ClientProtocolException, IOException 
	{
CloseableHttpClient httpClient=HttpClients.createDefault();

HttpGet httpGet=new HttpGet(url);//http get request

CloseableHttpResponse httpResponse=httpClient.execute(httpGet);//hit GET url
return httpResponse;
	}
	//with headers
	public CloseableHttpResponse  get(String url,HashMap<String,String> HeaderMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=HttpClients.createDefault();

		HttpGet httpGet=new HttpGet(url);//http get request
		for(Map.Entry<String,String> entry:HeaderMap.entrySet())
		{
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);//hit GET url
		return httpResponse;
		
	}
	//post call
	public CloseableHttpResponse  Post(String url,String entityString,HashMap<String,String> HeaderMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);//post url
		httpPost.setEntity(new StringEntity(entityString));//payload
		for(Map.Entry<String,String> entry:HeaderMap.entrySet())
		{
			httpPost.addHeader(entry.getKey(),entry.getValue());//post headers
		}
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		return httpResponse;
	}
	}

/*
//a.StstusCode
int statusCode=httpResponse.getStatusLine().getStatusCode();
System.out.println("StatusCode="+statusCode);
//b.Json String
String responseString =EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
JSONObject jsonObject=new JSONObject(responseString);
System.out.println("The Json String"+jsonObject);
//c. Header
Header[] headerArray=httpResponse.getAllHeaders();
HashMap<String,String> allHeaders=new HashMap<String,String>();
for(Header header:headerArray)
{
	allHeaders.put(header.getName(), header.getValue());
}
System.out.println("Headers array----"+allHeaders);*/
	
