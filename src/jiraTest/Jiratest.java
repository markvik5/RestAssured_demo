package jiraTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import jsonFiles.Jira_payload;

public class Jiratest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";

		// Login scenario and getting Cookie for running next testcase
		SessionFilter session = new SessionFilter();
		String response = given().header("Content-Type", "application/json").body(Jira_payload.login()).filter(session)
				.log().all().when().post("/rest/auth/1/session").then().log().all().extract().response().asString();

		String issueId = "10003"; // getting it through running the create issue API
		// add comment in create comment api
		given().pathParam("issueId", issueId).header("Content-Type", "application/json").body(Jira_payload.addComment())
				.filter(session).log().all().when().post("/rest/api/2/issue/{issueId}/comment").then().log().all()
				.assertThat().statusCode(201);

	}

}
