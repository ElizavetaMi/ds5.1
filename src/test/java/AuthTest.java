import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {

    private static RequestSpecification requestSpec;

    @BeforeAll
    static void setUpAll() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    @Test
    void createUserTest() {
        given()
                .spec(requestSpec)
                .body(new RegistrationDto("vasya", "password", "active"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void createUserWithBlockedStatus() {
        given()
                .spec(requestSpec)
                .body(new RegistrationDto("ivan", "password123", "blocked"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void createExistingUserTest() {
        given()
                .spec(requestSpec)
                .body(new RegistrationDto("vasya", "password", "active"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);

        given()
                .spec(requestSpec)
                .body(new RegistrationDto("vasya", "password", "active"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }


    @Test
    void createUserWithInvalidPasswordTest() {
        given()
                .spec(requestSpec)
                .body(new RegistrationDto("newuser", "", "active"))  // Пустой пароль
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200); // Сервер может возвращать 200, если пользователь был перезаписан, даже с пустым паролем.
    }
}