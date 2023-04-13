package dynamicJson;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.payload;
import reuseableMethod.ReuseableMethod;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";

		String Response = given().log().all().header("Content-Type", "application/json").body(payload.addBookJson())
				.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract()
				.asString();
		JsonPath js =  ReuseableMethod.rawToJson(Response);
		String id = js.getString("ID");
		System.out.println(id);
	}

}
