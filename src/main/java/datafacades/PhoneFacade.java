package datafacades;

import dtos.PersonDTO;
import dtos.PhoneDTO;
import entities.Person;
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
public class PhoneFacade {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PhoneFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PhoneFacade getPhoneFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public Phone create(Phone phone){
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return phone;
    }


    public PhoneDTO getPhoneById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try{

            TypedQuery findPhone = em.createQuery("SELECT p FROM Phone p WHERE p.id =:phone_id", Phone.class);
            findPhone.setParameter("phone_id",id);
            Phone phoneFound = (Phone) findPhone.getSingleResult();
            return new PhoneDTO(phoneFound);

        } finally {
            em.close();
        }
    }


    public List<PhoneDTO> getAllPhones(){
        EntityManager em = getEntityManager();

        try{

            TypedQuery findAll = em.createQuery("SELECT p FROM Phone p", Phone.class);
            List<Phone> phones = findAll.getResultList();
            return PhoneDTO.getDTOs(phones);

        }finally {
            em.close();
        }
    }


    public Phone update(Phone phone) throws EntityNotFoundException {
        if (phone.getId() == 0)
            throw new IllegalArgumentException("No Phone can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Phone p = em.merge(phone);
        em.getTransaction().commit();
        return p;
    }


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

}
