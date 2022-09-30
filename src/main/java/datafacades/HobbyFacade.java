package datafacades;

import entities.Hobby;
import entities.Movie;
import entities.Person;
import entities.Phone;
import errorhandling.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.eclipse.persistence.jpa.JpaHelper.getEntityManager;

public class HobbyFacade implements IDataFacade<Hobby> {
    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {}

    public static IDataFacade<Hobby> getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public Hobby create(Hobby h) {
        EntityManager em = getEntityManager();
        Hobby hobby = new Hobby(h.getDescription(), h.getPeople());
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    @Override
    public Hobby getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
         Hobby h = em.find(Hobby.class, id);
        if (h == null)
            throw new EntityNotFoundException("The hobby entity with ID: "+id+" Was not found");
        return h;
    }

    @Override
    public List<Hobby> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        List<Hobby> HobbyList = query.getResultList();
        return HobbyList;
    }

    @Override
    public Hobby update(Hobby h) throws EntityNotFoundException {
        if (h.getId() == 0)
            throw new IllegalArgumentException("No hobby can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Hobby hobby = em.merge(h);
        em.getTransaction().commit();
        return hobby;
    }

    @Override
    public Hobby delete(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Hobby h = em.find(Hobby.class, id);
        if (h == null)
            throw new EntityNotFoundException("Could not remove Person with id: "+id);
        em.getTransaction().begin();
        em.remove(h);
        em.getTransaction().commit();
        return h;
    }
    public Hobby addHobbyToPerson(int hobbyID, int personID){
        EntityManager em = emf.createEntityManager();
        Hobby hobby = em.find(Hobby.class, hobbyID);
        Person person = em.find(Person.class, personID);
        em.getTransaction().begin();
        person.addHobby(hobby);
        em.getTransaction().commit();
        em.close();
        return hobby;
    }
}

