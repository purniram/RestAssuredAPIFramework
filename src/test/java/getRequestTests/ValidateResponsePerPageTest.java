package getRequestTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class ValidateResponsePerPageTest {


    @Test
    public void validateSuccessResponse() {

        Response response = RestAssured.get("https://reqres.in/api/users?page=1");
        assertEquals(response.getStatusCode(), 200);

        System.out.println(response.getBody().toString());
    }

    @Test
    public void validateResponseBody() {

        RestAssured.baseURI = "https://reqres.in/api/users?page=1";
        Response response = RestAssured.get("https://reqres.in/api/users?page=1");

        // List<Map<String, ?>> actualData = response.jsonPath().getList("data");

        JsonPath jsonResponse = response.jsonPath();
        ArrayList<HashMap<String, ?>> dataList = jsonResponse.get("data");
        ArrayList<HashMap<String, ?>> expectedData = getExpectedData();

        printDataSet (dataList);
        assertEquals((int) jsonResponse.get("per_page"), 3);
        assertEquals((int) jsonResponse.get("page"), 1);
        assertEquals((int) jsonResponse.get("total_pages"), 4);

        assertEquals(getValuesFromList(dataList, "first_name"),
                     getValuesFromList(expectedData, "first_name"));
        assertEquals(getValuesFromList(dataList, "last_name"),
                getValuesFromList(expectedData, "last_name"));
        assertEquals(getValuesFromList(dataList, "id"),
                getValuesFromList(expectedData, "id"));

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

    public ArrayList<HashMap<String, ?>> getExpectedData() {

        ArrayList<HashMap<String, ?>> expectedData = new ArrayList<HashMap<String, ?>>();

        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        HashMap<String, String> map3 = new HashMap<>();

        map1.put("first_name","George" );
        map2.put("first_name", "Janet");
        map3.put("first_name", "Emma");
        map1.put("last_name", "Bluth");
        map2.put("last_name", "Weaver");
        map3.put("last_name", "Wong");
        map1.put("id","1");
        map2.put("id", "2");
        map3.put("id","3");


        expectedData.add(map1);
        expectedData.add(map2);
        expectedData.add(map3);

        return expectedData;
    }

    public ArrayList<String> getValuesFromList (ArrayList<HashMap<String, ?>> data, String key) {

        ArrayList<String> list = new ArrayList<String>();

        for (HashMap<String, ?> d : data) {

            System.out.println();
            Iterator<? extends Map.Entry<String, ?>> iterator = d.entrySet().iterator();

            while (iterator.hasNext()) {

                Map.Entry<String, ?> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    list.add((String.valueOf(entry.getValue())) );
                    System.out.println("getting "+ entry.getKey()+ " :"+ entry.getValue());
                }

            }
        }
        return list;
    }


}
