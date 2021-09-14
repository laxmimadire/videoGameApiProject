package testCases;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TC_VideoGameAPI {
	@Test(priority = 1)
	public void test_getAllVideoGames()
	{
		given()
		.when()
		.get("http://localhost:8080/app/videogames")
		.then()
		 .statusCode(200);
	}
	@Test(priority = 2)
	public void test_addNewVideoGame()
	{
		HashMap data=new HashMap();
		data.put("id","100");
		data.put("name", "Bhargavi");
		data.put("releaseDate","2019-09-20T08:55:58.510Z");
		data.put("reviewScore","5");
		data.put("category","Adventure");
		data.put("rating","Universal");
		
		Response res=
		given()
		  .contentType("application/json")
		.body(data)
		.when()
		  .post("http://localhost:8080/app/videogames")
	  .then()
		   .statusCode(200)
		   .log().body()
		   .extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);
			
		}
	@Test(priority = 3)
	public void test_getVideoGame()
	{
		given()
		.when()
		    .get("http://localhost:8080/app/videogames/100")
		.then()
		   .statusCode(200)
		   .log().body()
		.body("videoGame.id", equalTo("100"))
		.body("videoGame.name", equalTo("Bhargavi"));		
	}
	@Test(priority = 4)
	public void test_UpdateVideoGame()
	{
		HashMap data=new HashMap();
		data.put("id","100");
		data.put("name", "Madire Bhargavi");
		data.put("releaseDate","2019-09-20T08:57:58.510Z");
		data.put("reviewScore","4");
		data.put("category","Adventure");
		data.put("rating","Universal");
		given()
		  .contentType("application/json")
	   .body(data)
	   .when()
		   .put("http://localhost:8080/app/videogames/100")
	   .then()
		 .statusCode(200)
		 .log().body()
	   .body("videoGame.id", equalTo("100"))
       .body("videoGame.name", equalTo("Madire Bhargavi")); 
		
	}
	@Test(priority = 5)
	public void test_DeleteVideoGame() throws InterruptedException
	{
		Response resl=
		given()
		.when()
				  .delete("http://localhost:8080/app/videogames/100")
		.then()
				   .statusCode(200)
				   .log().body()
				   .extract().response();
		
		Thread.sleep(3000);
		String jsondelString=resl.asString();
		Assert.assertEquals(jsondelString.contains("Record Deleted Successfully"),true);
			
		
	}
	
	}


