package serialization;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import postData.Posts;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class POSTSerializationTest {

    @Test
    public void createNewPost() {

        // Serialization: POJO -> JSON
        // POJO = Plain Old Java Object
        Posts post = new Posts();
        post.setUserId(1);
        post.setTitle("Test title");
        post.setBody("Test body");

        Response response = given()
                .contentType("application/json")
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(post.getTitle(), json.get("title"));
    }

    @Test
    public void readPost() {
        Posts post = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .as(Posts.class);

        assertEquals(post.getTitle(), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }
}
