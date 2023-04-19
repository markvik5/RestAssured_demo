package jsonFiles;

public class Jira_payload {

	public static String addComment() {
		String addComment = "{\r\n"
				+ "\"body\":\"adding comment for checking in debit card issue via automation\"\r\n"
				+ "\r\n"
				+ "}";
		return addComment;
	}
	
	public static String login() {
		String loginBody = "{\r\n"
				+ "    \"username\": \"vikash.kunj1996\",\r\n"
				+ "    \"password\": \"Aircel@123\"\r\n"
				+ "}";
		return loginBody;
	}
	
	public static String addIssue() {
		String addIssue = "{\r\n"
				+ "	\"fields\": {\r\n"
				+ "		\"project\": {\r\n"
				+ "			\"key\": \"MAR\"\r\n"
				+ "		},\r\n"
				+ "		\"summary\": \"End to end testing bug\",\r\n"
				+ "		\"description\": \"Creating a defect for testing end to testing flow through API\",\r\n"
				+ "		\"issuetype\": {\r\n"
				+ "			\"name\": \"Bug\"\r\n"
				+ "		}\r\n"
				+ "	}\r\n"
				+ "}";
		return addIssue;
	}
	
	public static String addCommentEndtoEndflow() {
		String addComment = "{\r\n"
				+ "\"body\":\"adding comment for end to end testcase via automation\"\r\n"
				+ "\r\n"
				+ "}";
		return addComment;
	}
	
	public static String addCommentEndtoEndflowPass() {
		String addComment = "{\r\n"
				+ "\"body\":\"adding comment for end to end testcase via automation -- Passed\"\r\n"
				+ "\r\n"
				+ "}";
		return addComment;
	}

}
