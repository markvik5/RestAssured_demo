package testCase;

import java.util.Iterator;

import io.restassured.path.json.JsonPath;
import jsonFiles.payload;

public class ComplexJson {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.coursePrice());
		// total prize
		int pa = js.getInt("dashboard.PA");
		System.out.println(pa);

		// size of the array
		int courseSize = js.getInt("courses[].size()");
		System.out.println(courseSize);

		// title of first course
		String firstCourse = js.get("courses[0].title");
		System.out.println(firstCourse);

		// all the object present inside course
		for (int i = 0; i < courseSize; i++) {

			System.out.println(js.getString("courses[" + i + "].title"));
			System.out.println(js.getInt("courses[" + i + "].price"));

		}

		// print the number of copy sold for RPA course
		for (int i = 0; i < courseSize; i++) {

			String CourseName = js.getString("courses[" + i + "].title");
			if (CourseName.equalsIgnoreCase("RPA")) {
				System.out.println("Number of RPA copy sold: ");
				System.out.println(js.getInt("courses[" + i + "].copies"));
				break;
			}
		}

		int totalPrice = 0;
		// sum of all course prices match with PA amount
		for (int i = 0; i < courseSize; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			totalPrice = totalPrice + (price * copies);
		}
		if (totalPrice == pa) {
			System.out.println("Sum of all course match with purchase ammount");
		} else {
			System.out.println(" both the price do not match");
		}

		System.out.println("---- The End ----");
	}

}
