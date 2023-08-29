package testprgrest;


import org.testng.annotations.Test;

import files.paylod;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	public void SumofCourses()
	{
	 JsonPath js = new JsonPath(paylod.CoursePrice());
	 int count = js.getInt("courses.size()");
	// int sum =0;
		for(int i=0; i <count; i++)
		{
			
			int price = js.getInt("courses["+i+"].price");
			int tcopies = js.getInt("courses["+i+"].copies");
			int sum= price * tcopies;
			System.out.println(sum);
		}
	 
	 
	}

}
