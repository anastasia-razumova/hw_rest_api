import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.UserCreationModel;
import models.lombok.UserCreationResponse;
import models.pojo.CreateUserModel;
import models.pojo.CreateUserResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static helpers.CustomApiListener.withCustomTemplates;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import static org.hamcrest.Matchers.*;

import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponseSpec;
import static specs.GetUserSpec.singleUserRequestSpec;
import static specs.GetUserSpec.singleUserResponseSpec;
import static specs.UsersListSpec.getUsersListRequestSpec;
import static specs.UsersListSpec.getUsersListResponseSpec;
import static specs.DeleteUserSpec.deleteUserRequestSpec;
import static specs.DeleteUserSpec.deleteUserResponseSpec;
import static specs.NotFoundUserSpecs.notFoundUserRequestSpec;
import static specs.NotFoundUserSpecs.notFoundUserResponseSpec;

public class TestsAPI {

    @BeforeAll
    static void configure() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @DisplayName("Создание пользователя с Pojo")
    @Test
    void createUserPojoTest() {
        // String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        CreateUserModel userModel = new CreateUserModel();
        userModel.setJob("leader");
        userModel.setName("morpheus");

        CreateUserResponse responseModel = given()
                .log().all()
                .contentType(JSON)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThat(responseModel.getJob()).isEqualTo("leader");
        assertThat(responseModel.getName()).isEqualTo("morpheus");
    }

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
                    .statusCode(201) // Created
                    .extract().as(UserCreationResponse.class);

            assertThat(responseModel.getJob()).isEqualTo("leader");
            assertThat(responseModel.getName()).isEqualTo("morpheus");
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

    @DisplayName("Создание пользователя с Specs")
    @Test
    void createUserWithSpecsTest() {
        UserCreationModel userModel = new UserCreationModel();
        userModel.setJob("leader");
        userModel.setName("morpheus");

        UserCreationResponse responseModel = given()
                //given(createUserRequestSpec)
                .spec(createUserRequestSpec)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .spec(createUserResponseSpec)
                .extract().as(UserCreationResponse.class);

        assertThat(responseModel.getJob()).isEqualTo("leader");
        assertThat(responseModel.getName()).isEqualTo("morpheus");
    }


    @DisplayName("Запрос данных о конкретном пользователе")
    @Test
    void getSingleUser() {
        given()
                .spec(singleUserRequestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(singleUserResponseSpec)
                .body("data.id", is(2));
    }

    @DisplayName("Запрос данных о списке пользователей")
    @Test
    void getUsersList() {
        given()
                .spec(getUsersListRequestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(getUsersListResponseSpec)
                .body(containsString("id"));

    }

    @DisplayName("Удаление пользователя")
    @Test
    void deleteUser() {
        given()
                .spec(deleteUserRequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(deleteUserResponseSpec);
    }

    @DisplayName("Негативный тест на поиск несуществующего пользователя")
    @Test
    void notFoundUserTest() {
        given()
                .spec(notFoundUserRequestSpec)
                .when()
                .get("/api/users/23")
                .then()
                .spec(notFoundUserResponseSpec);
    }



}
