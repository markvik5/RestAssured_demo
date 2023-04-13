package reuseableMethod;

import io.restassured.path.json.JsonPath;

public class ReuseableMethod {

	public static JsonPath rawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	}

}
