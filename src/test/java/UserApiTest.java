import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.UserCreationModel;
import models.lombok.UserCreationResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static helpers.CustomApiListener.withCustomTemplates;
import static org.hamcrest.Matchers.*;
import static specs.Specs.*;

public class UserApiTest extends TestBase{


    @DisplayName("Создание пользователя с Lombok")
    @Test
    void createUserLombokTest() {
        UserCreationModel userModel = new UserCreationModel();
        userModel.setJob("leader");
        userModel.setName("morpheus");

        UserCreationResponse responseModel = given()
                    .log().all()
                    .contentType(JSON)
                    .body(userModel)
                    .when()
                    .post("/users")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(201)
                    .extract().as(UserCreationResponse.class);

            assertThat(responseModel.getJob()).isEqualTo("leader");
            assertThat(responseModel.getName()).isEqualTo("morpheus");
        }

    @DisplayName("Создание пользователя с Specs")
    @Test
    void createUserWithSpecsTest() {
        UserCreationModel userModel = new UserCreationModel();
        userModel.setJob("leader");
        userModel.setName("morpheus");

        UserCreationResponse responseModel = given()
                //given(createUserRequestSpec)
                .spec(request)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .spec(responseStatus201)
                .extract().as(UserCreationResponse.class);

        assertThat(responseModel.getJob()).isEqualTo("leader");
        assertThat(responseModel.getName()).isEqualTo("morpheus");
    }

    @DisplayName("Запрос данных о конкретном пользователе")
    @Test
    void getSingleUser() {
        given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseStatus200)
                .body("data.id", is(2));
    }

    @DisplayName("Запрос данных о списке пользователей")
    @Test
    void getUsersList() {
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseStatus200)
                .body(containsString("id"));

    }

    @DisplayName("Удаление пользователя")
    @Test
    void deleteUser() {
        given()
                .spec(request)
                .when()
                .delete("/users/2")
                .then()
                .spec(responseStatus204);
    }

    @DisplayName("Негативный тест на поиск несуществующего пользователя")
    @Test
    void notFoundUserTest() {
        given()
                .spec(request)
                .when()
                .get("/api/users/23")
                .then()
                .spec(responseStatus404);
    }


    @DisplayName("Создание пользователя с Allure Listener")
    @Test
    void createUserWithAllureListenerTest() {
        UserCreationModel userModel = new UserCreationModel();
        userModel.setJob("leader");
        userModel.setName("morpheus");

        UserCreationResponse responseModel = given()
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(JSON)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201) // Created
                .extract().as(UserCreationResponse.class);

        assertThat(responseModel.getJob()).isEqualTo("leader");
        assertThat(responseModel.getName()).isEqualTo("morpheus");
    }

    @DisplayName("Создание пользователя с Custom Allure Listener")
    @Test
    void createUserWithCustomAllureListenerTest() {
        UserCreationModel userModel = new UserCreationModel();
        userModel.setJob("leader");
        userModel.setName("morpheus");

        UserCreationResponse responseModel = given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201) // Created
                .extract().as(UserCreationResponse.class);

        assertThat(responseModel.getJob()).isEqualTo("leader");
        assertThat(responseModel.getName()).isEqualTo("morpheus");
    }


}
