package datafacades;

import dtos.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * created by THA
 * Purpose of this facade example is to show a facade used as a DB facade (only operating on entity classes - no DTOs
 * And to show case some different scenarios
 */
public class PersonFacade implements IDataFacade<Person> {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Person> getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person create(Person person){
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }


    @Override
    public PersonDTO getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw new EntityNotFoundException("The Person entity with ID: "+id+" Was not found");
        return p;
    }

    @Override
    public List<PersonDTO> getAll(){
        EntityManager em = getEntityManager();
        TypedQuery<PersonDTO> query = em.createQuery("SELECT p FROM Person p", PersonDTO.class);
        List<PersonDTO> person = query.getResultList();
        return person;
    }

    @Override
    public Person update(Person person) throws EntityNotFoundException {
        if (person.getId() == 0)
            throw new IllegalArgumentException("No Person can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Person p = em.merge(person);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Person delete(int id) throws EntityNotFoundException{
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if (p == null)
            throw new EntityNotFoundException("Could not remove Person with id: "+id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return p;
    }

    public Person addPhoneToPerson(int phoneID, int personID){
        EntityManager em = emf.createEntityManager();
        Phone phone = em.find(Phone.class, phoneID);
        Person person = em.find(Person.class, personID);
        em.getTransaction().begin();
            phone.setPerson(person);
        em.getTransaction().commit();
        em.close();
        return person;
    }
    public Person addHobbyToPerson(int hobbyID, int personID){
        EntityManager em = emf.createEntityManager();
        Hobby hobby = em.find(Hobby.class, hobbyID);
        Person person = em.find(Person.class, personID);
        em.getTransaction().begin();
        person.addHobby(hobby);
        em.getTransaction().commit();
        em.close();
        return person;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade fe = getPersonFacade(emf);


       /* Address address = new Address("Mariager","9550");
        Set<Phone> phones = new HashSet<>();
        Phone phone = new Phone(888888,"Iphone");
        Set<Hobby> hobbies = new HashSet<>();
        phones.add(phone);
        Person person = new Person("a@a.dk","Oliver","Jensen",address,hobbies,phones);

        System.out.println(person);
        */
    }
}

