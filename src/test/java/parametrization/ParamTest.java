package parametrization;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParamTest {
    private static Stream<Arguments> createData() {

        return Stream.of(
                Arguments.of(101, "title", "body"),
                Arguments.of(101, "", "GDSDA")
        );
    }

    @DisplayName("Create new post with params")
    @ParameterizedTest(name = "UserID: {0}, title: {1}, body: {2}")
    @MethodSource("createData")
    public void createNewPost(int userId, String title, String body) {

        JSONObject post = new JSONObject();
        post.put("userId", userId);
        post.put("title", title);
        post.put("body", body);

        System.out.println(post.toString());

        Response response = given().contentType("application/json").body(post.toString()).when().post("https://jsonplaceholder.typicode.com/posts").then().statusCode(201).extract().response();

        JsonPath json = response.jsonPath();
        assertEquals(title, json.get("title"));
    }
}
