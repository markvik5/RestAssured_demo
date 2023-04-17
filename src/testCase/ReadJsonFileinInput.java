package testCase;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public record ReadJsonFileinInput() {

	public static void main(String[] args) throws IOException {
		String placeId = null;
		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		 for reading json file we need to do the following:-
//		 content of the file to string --> convert the file into byte(by using inbuilt
//		 function) --> byte data to string

		// post method
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths
						.get(System.getProperty("user.dir")+"\\src\\jsonFiles\\addPlace.json"))))
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response()
				.asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		placeId = js.getString("place_id");
		System.out.println(placeId);
		
		
		String deleteResponse = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.deletePlace(placeId)).when().delete("maps/api/place/delete/json").then().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println("Deleted status:- " + deleteResponse);
	}

}
