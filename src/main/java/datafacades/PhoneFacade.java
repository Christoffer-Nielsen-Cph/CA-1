package datafacades;

import entities.Movie;
import entities.Phone;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * created by THA
 * Purpose of this facade example is to show a facade used as a DB facade (only operating on entity classes - no DTOs
 * And to show case some different scenarios
 */
public class PhoneFacade implements IDataFacade<Phone> {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PhoneFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Phone> getPhoneFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Phone create(Phone p){
        EntityManager em = getEntityManager();
        Phone phone = new Phone(p.getNumber(),p.getDescription());
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return phone;
    }

    @Override
    public Phone getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Phone p = em.find(Phone.class, id);
        if (p == null)
            throw new EntityNotFoundException("The Phone entity with ID: "+id+" Was not found");
        return p;
    }

    @Override
    public List<Phone> getAll(){
        EntityManager em = getEntityManager();
        TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
        List<Phone> phones = query.getResultList();
        return phones;
    }

    @Override
    public Phone update(Phone phone) throws EntityNotFoundException {
        if (phone.getId() == 0)
            throw new IllegalArgumentException("No Phone can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Phone p = em.merge(phone);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Phone delete(int id) throws EntityNotFoundException{
        EntityManager em = getEntityManager();
        Phone p = em.find(Phone.class, id);
        if (p == null)
            throw new EntityNotFoundException("Could not remove Phone with id: "+id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade fe = getPhoneFacade(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }
}
