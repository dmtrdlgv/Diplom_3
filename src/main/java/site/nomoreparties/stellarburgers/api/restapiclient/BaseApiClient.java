package site.nomoreparties.stellarburgers.api.restapiclient;

import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.assist.Url.BASE_URL;

public class BaseApiClient {

    private final String JSON = "application/json";
    //config основного клиента
    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true));

    //универсальный метод Get без параметров и хедеров
    protected Response doGetRequest(String endPoint) {
        return given()
                .log().uri()
                .config(config)
                .header("Content-Type", JSON)
                .get(BASE_URL + endPoint);
    }

    //универсальный метод Get с Хедерами но без парамтров
    protected Response doGetRequestWithHeaders(String endPoint, HashMap headers) {
        return given()
                .log().uri()
                .config(config)
                .headers(headers)
                .get(BASE_URL + endPoint);
    }

    //универсальный метод Get с параметрами
    protected Response doGetRequestWithParams(String endPoint, HashMap params) {
        return given()
                .log().all()
                .config(config)
                .header("Content-Type", JSON)
                .queryParams(params)
                .get(BASE_URL + endPoint);
    }

    //универсальный метод Post с Сериализацией (body as POJO)
    protected Response doPostRequest(String endPoint, Object body) {
        return given()
                .log().uri()
                .and()
                .log().body()
                .config(config)
                .header("Content-Type", JSON)
                .body(body)
                .post(BASE_URL + endPoint);
    }

    //универсальный метод Post со строкой в теле (body as POJO)
    protected Response doPostRequest(String endPoint, String body) {
        return given()
                .log().uri()
                .and()
                .log().body()
                .config(config)
                .header("Content-Type", JSON)
                .body(body)
                .post(BASE_URL + endPoint);
    }

    //универсальный метод Post с хедером и с Сериализацией (body as POJO)
    protected Response doPostRequestWithHeaders(String endPoint, HashMap headers, Object body) {
        return given()
                .log().uri()
                .and()
                .log().body()
                .config(config)
                .headers(headers)
                .body(body)
                .post(BASE_URL + endPoint);
    }

    //Универсальный метод Patch
    protected Response doPatchRequest(String endPoint, HashMap headers, Object body) {
        return given()
                .log().uri()
                .and()
                .log().body()
                .config(config)
                .headers(headers)
                .body(body)
                .patch(BASE_URL + endPoint);
    }

    //Универсальный метод Delete с хедерами
    protected Response doDeleteRequestWithHeaders(String endPoint, HashMap headers) {
        return given()
                .log().uri()
                .and()
                .log().body()
                .config(config)
                .headers(headers)
                .delete(BASE_URL + endPoint);
    }
}
