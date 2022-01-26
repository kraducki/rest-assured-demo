package crud;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PUTPATCHTest {
    private static Faker faker;
    private String fakeTitle;
    private String fakeBody;

    @BeforeAll
    public static void runOnce() {
        faker = new Faker();
    }

    @BeforeEach
    public void runBeforeEveryTest() {
        fakeTitle = faker.harryPotter().book();
        fakeBody = faker.gameOfThrones().quote();
    }

    @Test
    public void updatePostwithPUT() {
        JSONObject post = new JSONObject();
        post.put("userId", 202);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        System.out.println(post.toString());

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("202", json.get("userId").toString());
        assertEquals(fakeTitle, json.get("title"));
    }

    @Test
    public void updatePostwithPATCH() {
        JSONObject post = new JSONObject();
        post.put("title", fakeTitle);

        System.out.println(post.toString());

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .patch("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
    }


}
