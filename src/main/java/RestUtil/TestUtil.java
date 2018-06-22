package RestUtil;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil
{
public static JSONObject responseObject;
public static String getValueByJpath(JSONObject responseObject,String jPath)
{
	Object obj=responseObject;
	for(String s:jPath.split("/"))
		if(!s.isEmpty())
			if(!(s.contains("[")||s.contains("]")))
              obj=((JSONObject)obj).get(s);
			else if (s.contains("[")||s.contains("]"))
				obj=((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]","")));
				
			return obj.toString();
}
}
