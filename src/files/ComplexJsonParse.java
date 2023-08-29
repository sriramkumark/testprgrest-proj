package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(paylod.CoursePrice());
	int count=	js.getInt("courses.size()");
		System.out.println(count);
		int totalamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamount);
		String firsttitle = js.getString("courses[0].title");  //use get or getstring , by default get method return string
		System.out.println(firsttitle);
		
		for(int i=0; i<count ; i++)
		{
			String cTitle= js.get("courses["+i+"].title");
			System.out.println(cTitle);
		System.out.println(js.getInt("courses["+i+"].price".toString()));
			
		}
		
		
		for(int i=0; i < count; i++)
		{
			String ctitle = js.get("courses["+i+"].title");
			if (ctitle.equalsIgnoreCase("JAVA"))
			{
				int copy= js.get("courses["+i+"].copies");
				System.out.println(copy);
				//break;   can use break once particular verification done 
			}
			}
		//int[] amount=new int[4];
		int sum =0;
		for(int i=0; i <count; i++)
		{
			
			sum = js.getInt("courses["+i+"].price");
			int tcount = js.getInt("courses["+i+"].copies");
			sum= sum * tcount;
			System.out.println(sum);
		}
		if (sum == js.getInt("dashboard.purchaseAmount"))
		{
			System.out.println("Both purchase and sum of price Amount equal");	
		}
		else
		{
			System.out.println("Both purchase and sum of price Amount NOt equal");
		}
		//(int j=0;j < amount.size();)
		
			//if(sum == js.getInt("dashboard.purchaseAmount"))
            //System.out.println("Both purchase and sum of price Amount equal");
				//break;   can use break once particular verification done 
			}
			}
	//}



