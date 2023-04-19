package jiraTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import jsonFiles.Jira_payload;
import reuseableMethod.ReuseableMethod;

public class Jiratest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";

		// we can used relaxedHTTPSValidation() method in given() in https certification

		// Login scenario and getting Cookie for running next testcase
		SessionFilter session = new SessionFilter();
		given().header("Content-Type", "application/json").body(Jira_payload.login()).filter(session).log().all().when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();

		String issueId = "10003"; // getting it through running the create issue API
		// add comment in create comment api
		String response = given().pathParam("issueId", issueId).header("Content-Type", "application/json")
				.body(Jira_payload.addComment()).filter(session).log().all().when()
				.post("/rest/api/2/issue/{issueId}/comment").then().log().all().assertThat().statusCode(201).extract()
				.response().asString();
		JsonPath js = ReuseableMethod.rawToJson(response);
		String comment_id = js.get("id");
		System.out.println(comment_id);
		String comment_body = "adding comment for checking in debit card issue via automation";

		// add attachment
		given().pathParam("issueId", issueId).header("X-Atlassian-Token", "no-check").filter(session)
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File(System.getProperty("user.dir") + "\\src\\jsonFiles\\jira.txt")).when()
				.post("/rest/api/2/issue/{issueId}/attachments").then().assertThat().statusCode(200).log().all();

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		String getCommentBody = null;
		// get issue details by using issue id
		String get_response = given().pathParam("issueID", issueId).filter(session).queryParam("fields", "comment")
				.when().get("/rest/api/2/issue/{issueID}").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath get_response_js = ReuseableMethod.rawToJson(get_response);
		int commentSize = get_response_js.get("fields.comment.comments.size()");
		for (int i = 0; i < commentSize; i++) {
			String getId = get_response_js.get("fields.comment.comments[" + i + "].id");
			if (getId.equals(comment_id)) {
				getCommentBody = get_response_js.get("fields.comment.comments[" + i + "].body");
				Assert.assertEquals(getCommentBody, comment_body);
				System.out.println(getCommentBody);
			}
		}

		System.out.println("pass");

	}

}
