package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Address;
import entities.Cityinfo;
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
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class RestAssurered {


    @Test
    public void testServerIsUp() {
        System.out.println("testing is server up(Anders test)");
        Response response = RestAssured.get("http://localhost:8080/ca1_application_war_exploded/api/person");
                System.out.println(response);
        int statusCode = response.getStatusCode();


        assertEquals(200,statusCode);
    }

    //TODO: remember to change the expected result after what is in your database
    @Test
    public void AndersTestCheckoutPersonNameByID() {
      System.out.println("we're checking if we can take the firstname from localhost");
        RestAssured.get("http://localhost:8080/ca1_application_war_exploded/api/person/1").then().statusCode(200).assertThat()
                .body("firstName", equalTo("Anders"));
    }
    @Test
    public void TestGetAllPeople(){
        System.out.println("testing getting all person");
        List<PersonDTO> personDTOs;

        personDTOs = given()
                .contentType("application/json")
                .when()
                .get("/person")
                .then()
                .extract().body().jsonPath().getList("", PersonDTO.class);
        Address address = new Address("nisseland","jul",new Cityinfo(4000,1000,"Kbh"));
        pf.create(new Person("anders@meinicke.dk","Anders","Meinicke",address));
        pf.create(new Person("emil@meinicke.dk","emil","Meinicke",address));
        MovieDTO m2DTO = new MovieDTO(m2);
        assertThat(movieDTOs, containsInAnyOrder(m1DTO, m2DTO));
    }
}
