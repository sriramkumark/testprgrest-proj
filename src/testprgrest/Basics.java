package testprgrest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.ReUseableMethods;
import files.paylod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;	
public class Basics {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Rest assured API follow the steps to create a code
		// Given - All Input given through this method
		//  given.log.all.query params(k,v).header(cont-typ).body(payload)
		
		//When - Submit the API (need Resource URL and Request Method using ie: post,get,put...)
		//when.post(resouce url)
		
		//Then - Validate the request and send response with success status 200  
		// then.log.all.asserThat().statuscode(no)
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	//Addplace 	
	// Need to log the request followed by Given and Then method ie:log().all()	
    //wants to store entire response in a Variable(should be String) by declaring var can do
    //Entire Body code can kept as seprate package and call under body()
		
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				
				
		.body(paylod.AddPlace())
		.when().post("maps/api/place/add/json")
		
		// do validation in response body can do as like below
		//reposne body code validation 
		.then().log().all().statusCode(200).body("scope",equalTo("APP"))   //equalTo under Hemcrest package need to import
		
		//reposne header validation 
		//and extract the response code as string format and store in String Variable
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString(); 
		
		//print response as string
		System.out.println(response);
		
	//Sceranio, if you remove log method from response and store in a String VAriable and to print the response variable
	//	String var=given.when.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header()  
  
		 // To Parse the string into JSON by using class JsonPath		
		
		// from the response string need to extract placeid for update 
		// need to create JsonPath object and pass the path to that constructor 
		// can validate direct Given.When.Then response OR passing variable to JsonPath class and do.
		JsonPath js=new JsonPath(response);
		String place_Id = js.getString("place_id");
		System.out.println(place_Id);
		
		//update place 
		String newAddress = "First avenu , coimbatore";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_Id+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		       .when().put("maps/api/place/update/json")
		       .then().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
     	//get place  no header and body method
		String getPlaceResponse= given().log().all().queryParam("key","qaclick123").queryParam("place_id", place_Id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		System.out.println(getPlaceResponse);
		
		
		//JsonPath js1= new JsonPath(getPlaceResponse);
		//create new customize class resuablemethod , create method rawtoJson and invokes as like below.
		JsonPath js1=ReUseableMethods.rawToJson(getPlaceResponse);
		String actualAdress = js1.getString("address");
		System.out.println(actualAdress);
		//Cucumber - junit ,TestNG Testing Framework s
		
		//TO do asseration ,JAVA doesn't have method we need to depend on (or) relie on Testing Frameowrks

		//To validate to value which is stored in string variable
		// NEED to download only TestGN.jar file remaining no need
		// add the TestNG.jar in Java project-->properaties-> choose exteranal jar options and add the jar 
		Assert.assertEquals(actualAdress,newAddress);
}
	
}


