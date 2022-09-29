package datafacades;
import entities.*;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class HobbyFacadeTest {

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
            Set<Person> h1Set = new HashSet<>();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            Person person1 = new Person("anders@meinicke.dk","Anders","Meinicke",null,null,null);
            Person person2 = new Person("emil@meinicke.dk","Emil","Meinicke",null,null,null);
            h1Set.add(person1);
            h1Set.add(person2);
            Set<Person> h2Set = new HashSet<>();
            Person person3 = new Person("christopher@ottesen.dk","Christopher","Ottesen",null,null,null);
            Person person4 = new Person("someone@iknow.dk","Someone","Iknow",null,null,null);
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
