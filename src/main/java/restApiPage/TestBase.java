package restApiPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
public Properties prop;

public int  response_status_200=200;
public  int response_status_201=201;
public  int response_status_300=300;
public int response_status_401=401;
public int response_status_404=404;

public	TestBase()
{
	try{
		prop=new Properties();
		FileInputStream f=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\restApiConfig\\config.properties");
		prop.load(f);
		}
catch(FileNotFoundException e)
{
e.printStackTrace();	
}
catch(IOException e)
{
	e.printStackTrace();
}
}}

