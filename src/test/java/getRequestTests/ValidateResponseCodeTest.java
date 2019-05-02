package getRequestTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class ValidateResponseCodeTest {


    @Test
    public void validateSuccessResponse() {

        RestAssured.baseURI = "https://reqres.in/api/users?page=1";

        Response response = RestAssured.get("https://reqres.in/api/users?page=1");
        assertEquals(response.getStatusCode(), 200);

        System.out.println(response.getBody().toString());
    }

    @Test
    public void validateResponseBody() {
        RestAssured.baseURI = "https://reqres.in/api/users?page=1";

        Response response = RestAssured.get("https://reqres.in/api/users?page=1");

        JsonPath jsonResponse = response.jsonPath();

        //  System.out.println(jsonResponse.prettyPrint());

        System.out.println("per_page: " + jsonResponse.get("per_page").toString());
        System.out.println("page: " + jsonResponse.get("page").toString());
        System.out.println("Total pages :" + jsonResponse.get("total_pages").toString());
        System.out.println("Data: \n" + jsonResponse.get("data").toString());

        assertEquals((int) jsonResponse.get("per_page"), 3);

        // List<Map<String, ?>> actualData = response.jsonPath().getList("data");
        ArrayList<HashMap<String, ?>> dataList = jsonResponse.get("data");
        ArrayList<HashMap<String, String>> expectedData = getExpectedData();

        printDataSet (dataList);


    }

    private void printDataSet(ArrayList<HashMap<String,?>> dataList) {
        for (HashMap<String, ?> data : dataList) {

            System.out.println();
            Iterator<? extends Map.Entry<String, ?>> iterator = data.entrySet().iterator();

            while (iterator.hasNext()) {

                Map.Entry<String, ?> entry = iterator.next();
                System.out.print("key:" + entry.getKey());
                System.out.print(" Value:" + entry.getValue());
                System.out.println();
            }
        }
    }

    public ArrayList<HashMap<String, String>> getExpectedData() {
        ArrayList<HashMap<String, String>> expectedData = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        HashMap<String, String> map3 = new HashMap<String, String>();
        map1.put("first_name", "George");
        map2.put("first_name", "Janet");
        map3.put("first_name", "Emma");
        map1.put("last_name", "Bluth");
        map2.put("last_name", "Weaver");
        map3.put("last_name", "Wong");
        map1.put("id", "1");
        map2.put("id", "2");
        map3.put("id", "3");

        expectedData.add(map1);
        expectedData.add(map2);
        expectedData.add(map3);

        return expectedData;
    }

}
