package site.nomoreparties.stellarburgers.api.restapiclient;

import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.api.model.User;

import java.util.HashMap;

import static site.nomoreparties.stellarburgers.assist.Url.*;

public class UserApiClient extends BaseApiClient {

    private String body;
    private HashMap<String, String> headers;

    public Response registerUser(User user) {
        return doPostRequest(USER_REGISTER_ENDPOINT, user);
    }

    public Response logInUser(User user) {
        return doPostRequest(USER_LOGIN_ENDPOINT, user);
    }

    public Response logOutUser(String refreshToken) {
        body = String.format("{\"token\": \"%s\"}", refreshToken);
        return doPostRequest(USER_LOGOUT_ENDPOINT, body);
    }

    public Response updateUserToken(String token) {
        body = String.format("{\"token\": \"%s\"}", token);
        return doPostRequest(USER_TOKEN_UPDATE_ENDPOINT, body);
    }

    public Response getUserInfo(String token) {
        headers = new HashMap<>();
        headers.put("Authorization", token);
        return doGetRequestWithHeaders(USER_INFO_ENDPOINT, headers);
    }

    public Response updateUserInfo(String token, User user) {
        headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        headers.put("Authorization", token);
        return doPatchRequest(USER_INFO_ENDPOINT, headers, user);
    }

    public Response deleteUser(String token) {
        headers = new HashMap<>();
        headers.put("Authorization", token);
        return doDeleteRequestWithHeaders(USER_INFO_ENDPOINT, headers);
    }
}
