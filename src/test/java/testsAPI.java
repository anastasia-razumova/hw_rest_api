import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;


public class testsAPI {
    @Test
    void getSingleUser() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
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
                .get("https://reqres.in/api/users?page=2")
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
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    public void registerUserSuccess() {

        given()
                .body("{\n"
                        + "    \"email\": \"eve.holt@reqres.in\",\n"
                        + "    \"password\": \"pistol\"\n"
                        + "}")
                .contentType("application/json").
                when().
                post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void userLoginFail() {
        given()
                .body("{\n"
                        + "    \"email\": \"peter@klaven\",\n"
                        + "}")
                .contentType("application/json").
                when().
                post("https://reqres.in/api/register")
                .then()
                .statusCode(400);
    }
}
