package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import datafacades.HobbyFacade;
import datafacades.PersonFacade;
import dtos.PersonDTO;
import entities.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HobbyResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost:8080/ca1_application_war_exploded/api";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;



    static HttpServer startServer() {
            ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
            return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
}


    @Test
    void getByHobbytest(){


        System.out.println("testing get by person by hobby from api");
        List<PersonDTO> personDTOs;

      personDTOs = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/ca1_application_war_exploded/api/hobby/fodbold")
                .then()
                .extract().body().jsonPath().getList("all", PersonDTO.class);


      assertEquals(1, personDTOs.size());


    }

    @Test
    void getAmountByHobbytest(){

            System.out.println("testing get amount by hobby from api");

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("http://localhost:8080/ca1_application_war_exploded/api/hobby/amount/fodbold")
                    .then()
                    .body(equalTo("1"));

    }











}