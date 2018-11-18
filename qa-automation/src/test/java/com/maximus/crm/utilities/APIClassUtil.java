package com.maximus.crm.utilities;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.*;


public class APIClassUtil {

    public String baseUri;
    public String endPoint;
    public String request;
    public Object response;

    public int statusCode;
    public ResponseBody responseBody;
    public String responseString;
    public JsonPath jsonPathEvaluator;
    public JsonObject apiJsonObject;
    public Map apiJsonObjectMap;
    JsonParser parser = new JsonParser();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout",600000)
                    .setParam("http.socket.timeout",600000)
                    .setParam("http.connection-manager.timeout",600000));

    public APIClassUtil  setbaseUri(String base_uri) {
        if (base_uri == null || base_uri == ""){
            throw new NullPointerException("Base URI cannot be empty.");
        }
        if (base_uri.substring(base_uri.length() - 1) == "/"){
                this.baseUri = base_uri.replace(base_uri.substring(base_uri.length() - 1), "");
        }   else {
                this.baseUri = base_uri;
            }
            this.baseUri = this.baseUri.replace("\"", "");
        return this;
    }

    public APIClassUtil setEndPoint(String end_point)
        {
            if (end_point == null || end_point == ""){
                throw new NullPointerException("End point cannot be empty.");
            }
            if (end_point.charAt(0) == '/') {
                this.endPoint = end_point;
            } else {
                this.endPoint = "/" + end_point;
            }
        return this;
        }

        private APIClassUtil SetAndPrintTestInfo(){
            //System.out.println("URL: "+ this.baseUri + this.endPoint);
            RestAssured.baseURI = this.baseUri;
            RestAssured.useRelaxedHTTPSValidation();
            return this;
        }

    //reusable method
    public APIClassUtil getAPI() {
        this.SetAndPrintTestInfo();
        RequestSpecification request = RestAssured.given();
        Object response = request
                .header("domain", "la")
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .get(this.endPoint);
        this.response = ((Response) response).toString();
        this.statusCode = ((Response) response).statusCode();
        this.responseBody = ((Response) response).getBody();
        this.responseString = this.responseBody.asString();
        this.jsonPathEvaluator = ((Response) response).jsonPath();
        this.apiJsonObject = parser.parse(this.responseString).getAsJsonObject();
        this.apiJsonObjectMap = ((Response) response).body().as(Map.class);
        return this;
    }

    //reusable method
    public APIClassUtil GetAPIWithParameter(JsonObject jsonObject) {
        this.SetAndPrintTestInfo();
        RequestSpecification request = RestAssured.given();
        Object response = request
                .header("domain", "la")
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .log().all()
                .when()
                .get(this.endPoint);
        this.response = ((Response) response).toString();
        this.statusCode = ((Response) response).statusCode();
        this.responseBody = ((Response) response).getBody();
        this.responseString = this.responseBody.asString();
        this.jsonPathEvaluator = ((Response) response).jsonPath();
        JsonParser parser = new JsonParser();
        try {
            this.apiJsonObject = parser.parse(this.responseBody.asString()).getAsJsonObject();
            } catch (Exception e){
            this.apiJsonObject = null;
        }
        try {
            this.apiJsonObjectMap = ((Response) response).body().as(Map.class);
        } catch (Exception e){
            this.apiJsonObjectMap = null;
        }
        return this;
    }

    //reusable method
    public APIClassUtil GetAPIWithParameterAndQuery(JsonObject jsonObject, Map<String, ?> query_parameters) {
        this.SetAndPrintTestInfo();
        RequestSpecification request = RestAssured.given();
        Object response = request
                .header("domain", "la")
                .header("Content-Type", "application/json")
                .queryParams(query_parameters)
                .body(jsonObject.toString())
                .log().all()
                .when()
                .get(this.endPoint);
        this.response = ((Response) response).toString();
        this.statusCode = ((Response) response).statusCode();
        this.responseBody = ((Response) response).getBody();
        this.responseString = this.responseBody.asString();
        this.jsonPathEvaluator = ((Response) response).jsonPath();
        this.apiJsonObject = parser.parse(this.responseString).getAsJsonObject();
        this.apiJsonObjectMap = ((Response) response).body().as(Map.class);
        return this;
    }

    //reusable method
    public APIClassUtil PostAPIWithParameterAndQuery(JsonObject jsonObject, Map<String, ?> query_parameters) {
        this.SetAndPrintTestInfo();
        RequestSpecification request = RestAssured.given();
        Object response = request
                .header("domain", "la")
                .header("Content-Type", "application/json")
                .queryParams(query_parameters)
                .body(jsonObject.toString())
                .log().all()
                .when()
                .post(this.endPoint);
        this.response = ((Response) response).toString();
        this.statusCode = ((Response) response).statusCode();
        this.responseBody = ((Response) response).getBody();
        this.responseString = this.responseBody.asString();
        this.jsonPathEvaluator = ((Response) response).jsonPath();
        this.apiJsonObject = parser.parse(this.responseString).getAsJsonObject();
        this.apiJsonObjectMap = ((Response) response).body().as(Map.class);
        return this;
    }

    //reusable method
    public APIClassUtil PostAPIWithParameter(JsonObject jsonObject) {
        this.SetAndPrintTestInfo();
        RequestSpecification request = RestAssured.given();
        Object response = request
                .header("domain", "la")
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .log().all()
                .when()
                .post(this.endPoint);
        this.request = ((RequestSpecification) request).log().toString();
        this.response = ((Response) response).toString();
        this.statusCode = ((Response) response).statusCode();
        this.responseBody = ((Response) response).getBody();
        this.responseString = this.responseBody.asString();
        this.jsonPathEvaluator = ((Response) response).jsonPath();
        this.apiJsonObject = parser.parse(this.responseString).getAsJsonObject();
        this.apiJsonObjectMap = ((Response) response).body().as(Map.class);
        return this;
    }

    //reusable method
    public APIClassUtil PutAPI(JsonObject jsonObject) {
        this.SetAndPrintTestInfo();
        RequestSpecification request = RestAssured.given();
        Object response = request
                .config(config)
                .header("domain", "la")
                .header("Content-Type", "application/json")
                .accept("application/json")
                .body(jsonObject.toString())
                .log().all()
                .when()
                .put(this.endPoint);
        this.response = ((Response) response).toString();
        this.statusCode = ((Response) response).statusCode();
        this.responseBody = ((Response) response).getBody();
        this.responseString = this.responseBody.asString();
        this.jsonPathEvaluator = ((Response) response).jsonPath();
        this.apiJsonObject = parser.parse(this.responseString).getAsJsonObject();
        this.apiJsonObjectMap = ((Response) response).body().as(Map.class);
        return this;
    }
}
