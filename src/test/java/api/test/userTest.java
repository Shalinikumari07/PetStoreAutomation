package api.test;

import org.apache.logging.log4j.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndpoints;
import api.playload.User;
import io.restassured.response.Response;

public class userTest {
	
	Faker faker;
	User userpayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
		
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setUsername(faker.name().username());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	//
	
	@Test(priority=1)
	public void TestpostUser()
	{
		logger.info("---------user post-----");
		Response response=UserEndpoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("---------user post tested-----");
		
	}
	
	//
	
	@Test(priority=2)
	public void getUserByName()
	{
		logger.info("---------creating user-----");
		Response response=UserEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("---------user created-----");
	}
	
	//update
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		logger.info("---------updating user-----");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints.updateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().body().statusCode(200);
		//Aert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update  responseAfterUpdate
		
		Response responseAfterUpdate=UserEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
		logger.info("---------user updated-----");
	}
	
	///delete
	public void testDeleteUserByName() {
		logger.info("---------user deleting-----");
		Response response=UserEndpoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("---------user deleted-----");
	}

}
