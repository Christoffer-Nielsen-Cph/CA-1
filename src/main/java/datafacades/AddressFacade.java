package datafacades;

import entities.Address;
import entities.Movie;
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
public class AddressFacade implements IDataFacade<Address> {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AddressFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Address> getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Address create(Address a){
        EntityManager em = getEntityManager();
        Address address = new Address(a.getAddress(),a.getAdditionalInfo(),a.getCityinfo(),a.getPeople());
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return address;
    }

    @Override
    public Address getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Address a = em.find(Address.class, id);
        if (a == null)
            throw new EntityNotFoundException("The Address entity with ID: "+id+" Was not found");
        return a;
    }

    @Override
    public List<Address> getAll(){
        EntityManager em = getEntityManager();
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a", Address.class);
        List<Address> addresses = query.getResultList();
        return addresses;
    }

    @Override
    public Address update(Address address) throws EntityNotFoundException {
        if (address.getId() == 0)
            throw new IllegalArgumentException("No Address can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Address a = em.merge(address);
        em.getTransaction().commit();
        return a;
    }

    @Override
    public Address delete(int id) throws EntityNotFoundException{
        EntityManager em = getEntityManager();
        Address a = em.find(Address.class, id);
        if (a == null)
            throw new EntityNotFoundException("Could not remove Address with id: "+id);
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
        return a;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade af = getAddressFacade(emf);
        af.getAll().forEach(dto->System.out.println(dto));
    }
}
