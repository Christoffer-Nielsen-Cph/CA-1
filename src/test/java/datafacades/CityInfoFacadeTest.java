package datafacades;

import entities.Address;
import entities.Cityinfo;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CityInfoFacadeTest {

    private static EntityManagerFactory emf;
    private static IDataFacade<Cityinfo> facade;

    Cityinfo c1,c2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CityInfoFacade.getCityInfoFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test

    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();


        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cityinfo.deleteAllRows").executeUpdate();
            c1 = new Cityinfo(2670,"greve");
            c2 = new Cityinfo(2750,"ballerup");

            em.persist(c1);
            em.persist(c2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }



    @Test
    void getById() throws EntityNotFoundException {
        System.out.println("Testing getbyid(id)");
        Cityinfo expected = c1;
        Cityinfo actual = facade.getById(c1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        System.out.println("Testing getAll()");
        int expected = 2;
        int actual = facade.getAll().size();
        assertEquals(expected,actual);
    }

}