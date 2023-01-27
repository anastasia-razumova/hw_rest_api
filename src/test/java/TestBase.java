import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void configure() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

}
