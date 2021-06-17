package RestAssured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;

public class DummyRestApiTest {
    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    @Test
    public void PositiveTestGetAllOperation(){
        given()
                .get("/api/v1/employees")
                .then()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems(1,2,3));
    }

    @Test
    public void NegativeTestGetAllOperation(){
        given()
                .get("/api/v1")
                .then()
                .log().body()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
    }

    @Test
    public void PositiveTestPostNewEmployeeOperation(){
        PostRequestModel postRequestModelObject = new PostRequestModel("test","123","23");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("test", "123","23"), "Successfully! Record has been added.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postRequestModelObject)
                .log().all()
                .when()
                .request("POST","/api/v1/create")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }

    @Test
    public void NegativeTestPostNewEmployeeOperation(){

        PostRequestModel postRequestModelObject = new PostRequestModel("test","123","23");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("test", "123","23"), "Successfully! Record has been added.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postRequestModelObject)
                .log().all()
                .when()
                .request("POST","/api/v1/createqwerty")
                .then()
                .log().body()
                .statusCode(405)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);

    }

    @Test
    public void PositiveTestDeleteEmployeeOperation(){
        when()
                .request("DELETE","/api/v1/delete")
                .then()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
    }
    @Test
    public void NegativeTestDeleteEmployeeOperation(){
        when()
                .request("DELETE","/api/v1/delete")
                .then()
                .log().body()
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Error Occured! Page Not found, contact rstapi2example@gmail.com"));
    }

    @Test
    public void PositiveTestGetIdEmployeeOperation(){
        given()
                .get("/api/v1/employee/1")
                .then()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.employee_name", equalTo("Tiger Nixon"));
    }
    @Test
    public void NegativeTestGetIdEmployeeOperation(){
        given()
                .get("/api/v1/employee/100")
                .then()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo("null"));
    }
    @Test
    public void PositiveTestPutUpdateEmployeeOperation(){
        PostRequestModel postRequestModelObject = new PostRequestModel("Tom","5000","50");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("Tom", "5000","50"), "Successfully! Record has been updated.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postRequestModelObject)
                .log().all()
                .when()
                .request("PUT","/api/v1/update/21")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }

    @Test
    public void NegativeTestPutUpdateEmployeeOperation(){
        PostRequestModel postRequestModelObject = new PostRequestModel("Tom","5000","50");
        PostResponseModel expectedResponseModelObject = new PostResponseModel("success", new Data("Tom", "5000","50"), "Successfully! Record has been updated.");

        PostResponseModel responseModelObject = given()
                .with()
                .contentType("application/json")
                .body(postRequestModelObject)
                .log().all()
                .when()
                .request("PUT","/api/v1/updateqwe/21")
                .then()
                .log().body()
                .statusCode(405)
                .extract()
                .as(PostResponseModel.class);
        assertEquals(responseModelObject, expectedResponseModelObject);
    }
}
