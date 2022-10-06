package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datafacades.HobbyFacade;
import datafacades.PersonFacade;
import dtos.PersonDTO;
import entities.Address;
import entities.Cityinfo;
import entities.Hobby;
import entities.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.*;


public class HobbyResourceTest {

   private Person p1, p2;

    private static final int SERVER_PORT = 7777;
    //private static final String SERVER_URL = "http://localhost:8080/ca1_application_war_exploded/api";
    private static final String SERVER_URL = "http://localhost/api";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;



    static HttpServer startServer() {
            ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
            return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
}

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        HobbyFacade hf = HobbyFacade.getHobbyFacade(emf);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            p1 = new Person ("test@test.com", "Hans", "Hansen",new Address("street", "city",new Cityinfo(2800,"Lyngby")));
            p2 = new Person ("hans@hansen.com", "Hans", "Hansen",new Address("road", "town",new Cityinfo(3370,"Melby")));
            Hobby h1 = hf.create(new Hobby("Fodbold"));
            em.persist(p1);
            em.persist(p2);
            p1.addHobby(h1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Test
    void getByHobbytest(){

        System.out.println("testing get person by hobby from api");
        List<PersonDTO> personDTOs;

      personDTOs = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/hobby/fodbold")
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
                    .get("/hobby/amount/fodbold")
                    .then()
                    .body(equalTo("1"));

    }











}