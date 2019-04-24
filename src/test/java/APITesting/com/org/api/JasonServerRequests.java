package APITesting.com.org.api;

import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes._Posts;

import static com.jayway.restassured.RestAssured.*;

public class JasonServerRequests {
	
	@Test(enabled = true)
	public void test_01() {
		Response resp = given().
						when().
						get("http://localhost:3000/posts");
		System.out.println("Get response is: " + resp.asString());
	}
	
	@Test(enabled = true)
	public void test_02_1() {
		Response resp = given().
						body("  {\"id\":\"1\","
								+ " \"title\":\"json-server\","
								+ " \"author\":\"typicode\" }  ").
						when().
						contentType(ContentType.JSON).
						post("http://localhost:3000/posts");
		System.out.println("Get response is: " + resp.asString());
	}
	
	@Test(enabled = true)
	public void test_02() {
		Response resp = given().
						body("  {\"id\":\"2\","
								+ " \"title\":\"dummyTitle\","
								+ " \"author\":\"Vaibhav\" }  ").
						when().
						contentType(ContentType.JSON).
						post("http://localhost:3000/posts");
		System.out.println("Get response is: " + resp.asString());
	}
	
	@Test(enabled = true)
	public void test_03() {
		Posts posts = new Posts();
		posts.setId("3");
		posts.setTitle("something to send");
		posts.setAuthor("Lira");
		
		Response resp = given().
						when().
						contentType(ContentType.JSON).
						body(posts).
						post("http://localhost:3000/posts");
		System.out.println("Get response is: " + resp.asString());
	}
	
	@Test(enabled = true)
	public void test_04() {
		Response resp = given().
						when().
						get("http://localhost:3000/posts/3");
		System.out.println("Get response is: " + resp.asString());
	}
	
	@Test(enabled = true)
	public void test_05() {
		Posts posts = new Posts();
		posts.setId("3");
		posts.setTitle("update something to send");
		posts.setAuthor("update Lira");
		
		Response resp = given().
						when().
						contentType(ContentType.JSON).
						body(posts).
						put("http://localhost:3000/posts/3");
		System.out.println("Put API response is: " + resp.asString());	
	}
	
	@Test(enabled = true)
	public void test_06() {
		Response resp = given().
						body("{\"title\":\"update by PATCH request\" }").
						when().
						contentType(ContentType.JSON).
						patch("http://localhost:3000/posts/3");
		System.out.println("PATCH request: " + resp.asString());	
	}
	
	@Test(enabled = true)
	public void test_07() {
		Response resp = given().
						when().
						delete("http://localhost:3000/posts/3");
		System.out.println("Deleting Response: " + resp.asString());	
	}	
	
	@Test(enabled = true)
	public void test_08() {
		Info info = new Info();
		info.setEmail("info@appium-selenium.com");
		info.setPhone("1111111");
		info.setAddress("India");
		
		_Posts posts = new _Posts();
		posts.setAuthor("Author");
		posts.setId("10");
		posts.setTitle("title");
		posts.setInfo(info);
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		post("http://localhost:3000/posts");
		
		System.out.println("Response : "+ resp.asString());
		
	}	
}