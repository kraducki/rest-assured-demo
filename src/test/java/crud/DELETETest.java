package crud;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DELETETest {

    @Test
    public void deletePost() {
        Response response = given()
                .when()
                .delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
