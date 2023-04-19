package jiraTest;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import jsonFiles.Jira_payload;
import reuseableMethod.ReuseableMethod;

public class Jira_Test_first {

	@Test
	public void endtoendFlow() {
		RestAssured.baseURI = "http://localhost:8080";

		// we can used relaxedHTTPSValidation() method in given() in https certification

		// Login scenario and getting Cookie for running next testcase
		SessionFilter session = new SessionFilter();
		given().header("Content-Type", "application/json").body(Jira_payload.login()).filter(session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();

		// Creating a issue or bug and getting issue id

		String issueResonse = given().header("Content-Type", "application/json").body(Jira_payload.addIssue())
				.filter(session).when().post("/rest/api/2/issue").then().assertThat().statusCode(201).log().all()
				.extract().response().asString();
		JsonPath issueJS = ReuseableMethod.rawToJson(issueResonse);
		String issueID = issueJS.getString("id");
		System.out.println(issueID);

		// adding comment to the bug
		String commentResponse = given().pathParam("issueId", issueID).header("Content-Type", "application/json")
				.body(Jira_payload.addCommentEndtoEndflow()).filter(session).log().all().when()
				.post("/rest/api/2/issue/{issueId}/comment").then().log().all().assertThat().statusCode(201).extract()
				.response().asString();
		JsonPath js = ReuseableMethod.rawToJson(commentResponse);
		String commentId = js.get("id");
		System.out.println(commentResponse);
		String comment_body = "adding comment for end to end testcase via automation";

		// add attachment
		String attachmentRespose = given().pathParam("issueId", issueID).header("X-Atlassian-Token", "no-check")
				.filter(session).header("Content-Type", "multipart/form-data")
				.multiPart("file", new File(System.getProperty("user.dir") + "\\src\\jsonFiles\\jira.txt")).when()
				.post("/rest/api/2/issue/{issueId}/attachments").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath attachmentJS = ReuseableMethod.rawToJson(attachmentRespose);
		System.out.println(attachmentRespose);

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		String getCommentBody = null;
		// get issue details by using issue id
		String get_response = given().pathParam("issueID", issueID).filter(session).queryParam("fields", "comment")
				.when().get("/rest/api/2/issue/{issueID}").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath get_response_js = ReuseableMethod.rawToJson(get_response);
		int commentSize = get_response_js.get("fields.comment.comments.size()");
		for (int i = 0; i < commentSize; i++) {
			String getId = get_response_js.get("fields.comment.comments[" + i + "].id");
			if (getId.equals(commentId)) {
				getCommentBody = get_response_js.get("fields.comment.comments[" + i + "].body");
				Assert.assertEquals(getCommentBody, comment_body);
				System.out.println(getCommentBody);
			}
		}

		System.out.println("pass for validating the comments");

		// deleting the comments
		given().filter(session).pathParam("commentId", commentId).pathParam("issueId", issueID).when()
				.delete("/rest/api/2/issue/{issueId}/comment/{commentId}").then().assertThat().statusCode(204);
		
		 given().pathParam("issueId", issueID).header("Content-Type", "application/json")
			.body(Jira_payload.addCommentEndtoEndflowPass()).filter(session).log().all().when()
			.post("/rest/api/2/issue/{issueId}/comment").then().log().all().assertThat().statusCode(201).extract()
			.response().asString();

		// Deleting the created issue

//		given().filter(session).pathParam("issueId", issueID).when().delete("rest/api/2/issue/{issueId}").then()
//				.assertThat().statusCode(204);
	}
}
