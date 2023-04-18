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

}
