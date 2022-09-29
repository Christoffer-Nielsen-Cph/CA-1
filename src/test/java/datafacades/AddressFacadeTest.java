package datafacades;

import entities.Address;
import entities.Cityinfo;
import entities.Movie;
import entities.Person;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddressFacadeTest {

    private static EntityManagerFactory emf;
    private static IDataFacade<Address> facade;

    Address a1,a2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AddressFacade.getAddressFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test

    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Cityinfo cityInfoOne = new Cityinfo(1,2670,"greve");
        Cityinfo cityinfoTwo = new Cityinfo(1,2750,"ballerup");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            a1 = new Address("knøsen 52","info", cityInfoOne);
            a2 = new Address("sankt jacobsvej","2.tv",cityinfoTwo);

            em.persist(a1);
            em.persist(a2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }


    @Test
    void create() {
        System.out.println("Testing create(Address a)");
        Address a = new Address("Istergade 13","No info",new Cityinfo(44,1000,"Kbh"));
        a.setId(3);
        Address expected = a;
        Address actual   = facade.create(a);
        assertEquals(expected, actual);
    }

/*
    @Test
    void getById() throws EntityNotFoundException {
        System.out.println("Testing getbyid(id)");
        Movie expected = m1;
        Movie actual = facade.getById(m1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        System.out.println("Testing getAll()");
        int expected = 2;
        int actual = facade.getAll().size();
        assertEquals(expected,actual);
    }

    @Test
    void update() throws EntityNotFoundException {
        System.out.println("Testing Update(Movie m)");
        m2.setYear(1965);
        Movie expected = m2;
        Movie actual = facade.update(m2);
        assertEquals(expected,actual);
    }


    @Test
    void delete() throws EntityNotFoundException {
        System.out.println("Testing delete(id)");
        Movie m = facade.delete(m1.getId());
        int expected = 1;
        int actual = facade.getAll().size();
        assertEquals(expected, actual);
        assertEquals(m,m1);
    }

 */
}