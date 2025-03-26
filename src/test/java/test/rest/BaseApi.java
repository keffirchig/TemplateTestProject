package test.rest;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.config.JsonPathConfig;
import keff.example.config.Configuration;
import keff.example.config.ConfigurationManager;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.config;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

public class BaseApi {
    protected static Configuration configuration;

    @BeforeSuite
    public static void beforeAllTests() {
        configuration = ConfigurationManager.getConfiguration();

        baseURI = configuration.baseURI();
        basePath = configuration.basePath();
        port = configuration.port();

        // solve the problem with big decimal assertions
        config = newConfig().
                jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).
                sslConfig(new SSLConfig().allowAllHostnames());

        RestAssured.useRelaxedHTTPSValidation();

        determineLog();
    }

    /*
     * if log.all is true in the api.properties file all the request and response information will be logged
     * otherwise it will log only when the test fails
     */
    private static void determineLog() {
        if (configuration.logAll()) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        } else {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }
    }
}
