package testCase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class FirstTestCase {
	public static void main(String[] args) {
		// validate the add place API

		// there are three method which are basically used in API.
		// Given :- All the input needed
		// when :- submit the api request -- resourse, http method (post , get, delete,
		// put)
		// Then :- validate the api response
		String placeId = null;
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// post method
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();
		System.out.println(response);

		JsonPath js = new JsonPath(response);
		placeId = js.getString("place_id");
		System.out.println(placeId);

		System.out.println("address method -- put method -- \n");
		String newAddress = "22 Main, USA";

		// put method
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.updatePlace(placeId, newAddress)).when().put("maps/api/place/update/json").then().log()
				.all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		// get method
		System.out.println("--- get method ---");
		String get_response_body = given().queryParam("key", "qaclick123").queryParam("place_id", placeId).when()
				.get("/maps/api/place/get/json").then().assertThat().statusCode(200)
				.body("address", equalTo(newAddress)).extract().response().asString();
		System.out.println(get_response_body);

		js = new JsonPath(get_response_body);
		String actualAddress = js.getString("address");
		Assert.assertEquals(newAddress, actualAddress);
		System.out.println("pass");

		// Add place --> palceId --> update place(placeId) --> get place and
		// validate(placeId)

		String deleteResponse = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.deletePlace(placeId)).when().delete("maps/api/place/delete/json").then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("Deleted status:- " + deleteResponse);
	}
}
