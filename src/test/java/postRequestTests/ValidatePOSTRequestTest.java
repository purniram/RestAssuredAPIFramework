package postRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ValidatePOSTRequestTest {


    @Test
    public void validateSuccessResponse (){

        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification request = RestAssured.given();

        JSONObject requestParam = new JSONObject();
        requestParam.put("name", "morpheus");
        requestParam.put("job", "leader");

        request.body(requestParam.toJSONString());
        Response response = RestAssured.post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(), 200);

        System.out.println(response.getBody().toString());
    }
}
