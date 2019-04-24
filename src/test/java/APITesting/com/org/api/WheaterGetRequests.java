package APITesting.com.org.api;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;

public class WheaterGetRequests {

	@Test(enabled = true)
	public void Test_01() {
		RestAssured.useRelaxedHTTPSValidation();
		Response resp = when().get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		System.out.println(resp.getStatusCode());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
	}

	@Test(enabled = true)
	public void Test_02() {
		RestAssured.useRelaxedHTTPSValidation();
		Response resp = when().get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=");
		System.out.println(resp.getStatusCode());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
	}

	@Test(enabled = true)
	public void Test_03() {
		RestAssured.useRelaxedHTTPSValidation();
		Response resp = given()
				.param("q", "London,uk")
				.param("appid", "b6907d289e10d714a6e88b30761fae22")
				.when()
				.get("https://samples.openweathermap.org/data/2.5/weather");
		System.out.println(resp.getStatusCode());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
	}

	@Test(enabled = true)
	public void Test_04() {
		RestAssured.useRelaxedHTTPSValidation();
		given().
		param("q", "London,uk").
		param("appid", "b6907d289e10d714a6e88b30761fae22").
		when().
		get("https://samples.openweathermap.org/data/2.5/weather").
		then().
		assertThat().statusCode(200);
	}
	
	@Test(enabled = true)
	public void Test_05() {
		RestAssured.useRelaxedHTTPSValidation();
		Response resp = given()
			.param("q", "London,uk")
			.param("appid", "b6907d289e10d714a6e88b30761fae22")
			.when()
			.get("https://samples.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());
	}
	
	@Test(enabled = true)
	public void Test_06() {
		RestAssured.useRelaxedHTTPSValidation();
		Response resp = given()
			.param("q", "London,uk")
			.param("appid", "b6907d289e10d714a6e88b30761fae22")
			.when()
			.get("https://samples.openweathermap.org/data/2.5/weather");
		Assert.assertEquals(resp.getStatusCode(), 200);	
		System.out.println(resp.asString());
	}
	
	@Test(enabled = true)
	public void Test_07() {
		RestAssured.useRelaxedHTTPSValidation();
		Response resp = given()
			.param("zip", "2172797,in")
			.param("appid", "b6907d289e10d714a6e88b30761fae22")
			.when()
			.get("https://samples.openweathermap.org/data/2.5/weather");
		Assert.assertEquals(resp.getStatusCode(), 200);	
		System.out.println(resp.asString());
	}
	
	@Test(enabled = true)
	public void Test_08(){
		String weatherReport = given().
				parameter("id","2172797").
				parameter("appid", "673c5650a20311041c26d61291b186ae").
				when().
				get("http://api.openweathermap.org/data/2.5/weather").
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");
		System.out.println("wheather report : "+ weatherReport);
	}
	
	@Test(enabled = true)
	public void Test_09(){
		Response resp = given().
				parameter("id","2172797").
				parameter("appid", "673c5650a20311041c26d61291b186ae").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");	
		
		String actualWeatherReport = resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");

	    String expectedWeathereport = null;		
			
			if(actualWeatherReport.equalsIgnoreCase(expectedWeathereport)){
				System.out.println("Testcase pass");
			}
			else
				System.out.println("Testcase fail");
	}
	
	@Test(enabled = true)
	public void Test_10(){
		Response resp = given().
				parameter("id", "2172797").
				parameter("appid", "673c5650a20311041c26d61291b186ae").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");
				
		String reportbyID =  resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");
		
		System.out.println("weather description by ID : " + reportbyID);
		
		String lon = String.valueOf(resp.
					then().
					contentType(ContentType.JSON).
					extract().
					path("coord.lon"));
		
		System.out.println("longitude is : " + lon);

		String lat = String.valueOf(resp.
				then().
				contentType(ContentType.JSON).
				extract().
				path("coord.lat"));
	
		System.out.println("latitude is : " + lat);
		
		String reportbyCoordinates = given().
				parameter("lat", lat).
				parameter("lon", lon).
				parameter("appid", "673c5650a20311041c26d61291b186ae").
				when().
				get("http://api.openweathermap.org/data/2.5/weather").
				then().
				contentType(ContentType.JSON).
				extract().
				path("weather[0].description");

		System.out.println("report by coordinates : " + reportbyCoordinates);
	
		Assert.assertEquals(reportbyID, reportbyCoordinates);
	}
}