package dynamicJson;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.payload;
import reuseableMethod.ReuseableMethod;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";

		// adding the book
		String Response = given().log().all().header("Content-Type", "application/json")
				.body(payload.addBookJson(isbn, aisle)).when().post("Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().asString();
		JsonPath js = ReuseableMethod.rawToJson(Response);
		String id = js.getString("ID");
		System.out.println(id);

		// deleting the added book
		given().header("Content-Type", "application/json").body(payload.deleteBook(id)).when()
				.post("Library/DeleteBook.php").then().log().all().assertThat().statusCode(200);

	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		// create a multi-dimensional array.
		// multi-dimensional = collection of array.
		Object Books_Data_Details[][] = new Object[][] { { "mobyu", "9811" }, { "yteuj", "1911" }, { "hengt", "19221" },
				{ "yterq", "12948" }, { "uytmw", "16243" }, { "kjyeeeaa", "623411" }, { "hyenja", "12091" },
				{ "juyeef", "76121" } };
		return Books_Data_Details;
	}
}
