package util;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class JsonHelper {
	
	public static String j = "{\r\n" + 
			"  \"Students\": [\r\n" + 
			"    {\r\n" + 
			"      \"id\": 1,\r\n" + 
			"      \"Name\": \"Student1\",\r\n" + 
			"      \"FName\": \"FName1\",\r\n" + 
			"      \"Class\": \"I\",\r\n" + 
			"      \"City\": \"Delhi\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"id\": 2,\r\n" + 
			"      \"Name\": \"Student2\",\r\n" + 
			"      \"FName\": \"FName2\",\r\n" + 
			"      \"Class\": \"II\",\r\n" + 
			"      \"City\": \"Mumbai\"\r\n" + 
			"    },\r\n" + 
			"     {\r\n" + 
			"      \"id\": 3,\r\n" + 
			"      \"Name\": \"Student3\",\r\n" + 
			"      \"FName\": \"FName3\",\r\n" + 
			"      \"Class\": \"II\",\r\n" + 
			"      \"City\": \"Delhi\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"id\": 4,\r\n" + 
			"      \"Name\": \"Student4\",\r\n" + 
			"      \"FName\": \"FName4\",\r\n" + 
			"      \"Class\": \"III\",\r\n" + 
			"      \"City\": \"Mumbai\"\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}";
	
	public static JSONArray filter(String json,String mainObj,String field,String value) {
		return JsonPath.read(json, "$."+mainObj+"[?(@."+field+"=="+value+")]");
	}
}
