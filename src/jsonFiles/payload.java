package jsonFiles;

public class payload {
	
	public static String AddPlace()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Bhola niwas \",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://facebook.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ " \r\n"
				+ "";
	}
	public static String updatePlace(String placeId, String newAddress) {
		String updatedata = "{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
		return updatedata;
	}
	
	public static String deletePlace(String placeId) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String coursePrice() {
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"PA\": 910,\r\n"
				+ "    \"website\": \"https://vik.com\"\r\n"
				+ "  },\r\n"
				+ "    \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"selenium\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n"
				+ "      \"copies\": 10\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
	}
	public static String check() {
		return "[\r\n"
				+ "	{\r\n"
				+ "		\"self\": \"http://localhost:8080/rest/api/2/attachment/10024\",\r\n"
				+ "		\"id\": \"10024\",\r\n"
				+ "		\"filename\": \"jira.txt\",\r\n"
				+ "		\"author\": {\r\n"
				+ "			\"self\": \"http://localhost:8080/rest/api/2/user?username=vikash.kunj1996\",\r\n"
				+ "			\"name\": \"vikash.kunj1996\",\r\n"
				+ "			\"key\": \"JIRAUSER10000\",\r\n"
				+ "			\"emailAddress\": \"vikash.kunj1996@gmail.com\",\r\n"
				+ "			\"avatarUrls\": {\r\n"
				+ "				\"48x48\": \"http://localhost:8080/secure/useravatar?avatarId=10341\",\r\n"
				+ "				\"24x24\": \"http://localhost:8080/secure/useravatar?size=small&avatarId=10341\",\r\n"
				+ "				\"16x16\": \"http://localhost:8080/secure/useravatar?size=xsmall&avatarId=10341\",\r\n"
				+ "				\"32x32\": \"http://localhost:8080/secure/useravatar?size=medium&avatarId=10341\"\r\n"
				+ "			},\r\n"
				+ "			\"displayName\": \"vikash.kunj1996@gmail.com\",\r\n"
				+ "			\"active\": true,\r\n"
				+ "			\"timeZone\": \"Asia/Calcutta\"\r\n"
				+ "		},\r\n"
				+ "		\"created\": \"2023-04-19T23:53:13.778+0530\",\r\n"
				+ "		\"size\": 30,\r\n"
				+ "		\"mimeType\": \"text/plain\",\r\n"
				+ "		\"content\": \"http://localhost:8080/secure/attachment/10024/jira.txt\"\r\n"
				+ "	}\r\n"
				+ "]";	
		}
	
	public static String addBookJson(String isbn, String aisle) {
		String addBook = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java1\",\r\n"
				+ "\"isbn\":\""+isbn +"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe1\"\r\n"
				+ "}";
		
		return addBook;
	}
	
	public static String deleteBook(String id) {
		String deleteBook = "{\r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ "} \r\n"
				+ "";
		
		return deleteBook;
	}
	
	
	

}
