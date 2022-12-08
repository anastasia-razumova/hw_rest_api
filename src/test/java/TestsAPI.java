import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;


public class TestsAPI {

    @BeforeAll
    static void configure() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    void getSingleUser() {
        given()
                .log().uri()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2));
    }

    @Test
    void getUsersList() {
        given()
                .log().uri()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(containsString("id"));

    }

    @Test
    void deleteUser() {
        given()
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void registerUserSuccess() {

        given()
                .body("{\n"
                        + "    \"email\": \"eve.holt@reqres.in\",\n"
                        + "    \"password\": \"pistol\"\n"
                        + "}")
                .contentType("application/json")
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void userLoginFail() {
        given()
                .body("{\n"
                        + "    \"email\": \"peter@klaven\",\n"
                        + "}")
                .contentType("application/json")
                .when()
                .post("/register")
                .then()
                .statusCode(400);
    }
}
