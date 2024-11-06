package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import api.endpoints.UserEndpoints;
import api.playload.User;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

import org.testng.annotations.Test;

public class DDTest {
	
	@Test(priority=1, dataProvider="Data",dataProviderClass=Dataprovider.class)
	public void testPostUser(String UserID,String UserName,String fname,String lname,String email,String pwd,String phone)
	{
		User userpayload=new User();
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phone);
		
		Response response=UserEndpoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass=Dataprovider.class)
	public void testUserDeleteByname(String UserName)
	{
		
		Response response=UserEndpoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(),200);
		
	}

}
