package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.playload.User;

//import com.github.scribejava.core.model.Response;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndpoints {
	
	
	
	public static Response createUser(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return response;
	}
	public static Response readUser(String UserName)
	{
		Response response=given()
		.pathParam("username", UserName)
		.when()
		.get(Routes.get_url);
		
		return response;
	}
	public static Response updateUser(String userName,User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
		
		.when()
		.put(Routes.update_url);
		
		return response;
	}
	public static Response deleteUser(String UserName)
	{
		Response response=given()
		.pathParam("username", UserName)
		.when()
		.delete(Routes.delete_url);
		
		return response;
	}

}
