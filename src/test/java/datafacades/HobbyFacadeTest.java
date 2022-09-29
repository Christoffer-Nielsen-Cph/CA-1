package datafacades;
import entities.Hobby;
import entities.Movie;
import entities.Person;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
class HobbyFacadeTest {

    private static EntityManagerFactory emf;
    private static IDataFacade<Hobby> facade;

    Hobby h1,h2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = HobbyFacade.getHobbyFacade(emf);

    }
    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();

            Set<Person> h1Set = new HashSet<>();
           // Person person1 = new Person("anders@meinicke","Anders","Meinicke","Robertjacobsens vej 14p",);
            Person person2 = new Person();
           // h1Set.add(person1);
            h1Set.add(person2);
            Set<Person> h2Set = new HashSet<>();
            Person person3 = new Person();
            Person person4 = new Person();
            h2Set.add(person3);
            h2Set.add(person4);
            h1 = new Hobby();
            h2 = new Hobby();

            em.persist(h1);
            em.persist(h2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
