package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import jsonFiles.payload;

public class SumValidation {

	@Test
	public void sumOfCourse() {

		JsonPath js = new JsonPath(payload.coursePrice());
		int pa = js.getInt("dashboard.PA");
		System.out.println(pa);
		int courseSize = js.getInt("courses.size()");

		int totalPrice = 0;
		// sum of all course prices match with PA amount
		for (int i = 0; i < courseSize; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			totalPrice = totalPrice + (price * copies);
			// totalPrice = totalPrice + (price * copies) + 1;  -- for fail
			System.out.println(totalPrice);
		}
		Assert.assertEquals(totalPrice, pa, "both are not equal");
		if (totalPrice == pa) {
			System.out.println("Sum of all course match with purchase ammount");
		} else {
			System.out.println(" both the price do not match");
		}

	}
}
