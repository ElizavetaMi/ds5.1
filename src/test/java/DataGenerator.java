import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.filter.log.LogDetail;

public class DataGenerator {
    private static Faker faker = new Faker();

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    // Метод для создания пользователя
    public static RegistrationDto createUser(String status) {
        String login = faker.name().username();
        String password = faker.internet().password();
        RegistrationDto user = new RegistrationDto(login, password, status);

        // Отправка POST запроса для создания пользователя
        Response response = given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200)  // Ожидаем успешный статус код
                .extract().response();


        return user;
    }
}
