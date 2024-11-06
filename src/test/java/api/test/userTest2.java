package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.playload.User;
import io.restassured.response.Response;

public class userTest2 {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setUpData() {
		
		faker=new Faker();
		userpayload=new User();
		
		//public Logger logger;
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setUsername(faker.name().username());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void TestpostUser()
	{
		
		Response response=UserEndpoints2.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=2)
	public void getUserByName()
	{
		Response response=UserEndpoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints2.updateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().body().statusCode(200);
		//Aert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update  responseAfterUpdate
		
		Response responseAfterUpdate=UserEndpoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
		
	}
	public void testDeleteUserByName() {
		Response response=UserEndpoints2.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
