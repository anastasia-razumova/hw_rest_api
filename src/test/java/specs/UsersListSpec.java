package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;

public class UsersListSpec {
    // todo move to request & response specs classes
    public static RequestSpecification getUsersListRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all();

    public static ResponseSpecification getUsersListResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("data.first_name", notNullValue())
            .build();
}
