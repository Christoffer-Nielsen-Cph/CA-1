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
public class PersonFacade  {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


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


   /* public PersonDTO getPersonByPhone(int phoneNumber){
        EntityManager em = getEntityManager();

        try{
            TypedQuery findPerson = em.createQuery("SELECT p FROM Person p WHERE p.phone.number = :number",Person.class);
            findPerson.setParameter("number",phoneNumber);
            Person personFound = (Person) findPerson.getSingleResult();
            return new PersonDTO(personFound);
        } finally {
            em.close();
        }
    } */


    public PersonDTO getPersonById(int id) throws EntityNotFoundException {

        EntityManager em = getEntityManager();

        try{

            TypedQuery findPerson = em.createQuery("SELECT p FROM Person p WHERE p.id =:person_id",Person.class);
            findPerson.setParameter("person_id",id);
            Person personFound = (Person) findPerson.getSingleResult();
            return new PersonDTO(personFound);

        } finally {
            em.close();
        }
    }


    public List<PersonDTO> getAllPeople(){

        EntityManager em = getEntityManager();

        try{

            TypedQuery findAll = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> people = findAll.getResultList();
            return PersonDTO.getDTOs(people);

        }finally {
            em.close();
        }

    }


    public Person update(Person person) throws EntityNotFoundException {
        if (person.getId() == 0)
            throw new IllegalArgumentException("No Person can be updated when id is missing");
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Person p = em.merge(person);
        em.getTransaction().commit();
        return p;
    }

    public Person update(int id, Person newPerson) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Person oldPerson = em.find(Person.class,getPersonById(id));
        if (oldPerson.getId() == 0) {
            throw new IllegalArgumentException("No Person can be updated when id is missing");
        }
        if (newPerson.getFirstName() != null){
            oldPerson.setFirstName(newPerson.getFirstName());
        }
        if (newPerson.getLastName() != null){
            oldPerson.setLastName(newPerson.getLastName());
        }
        if (newPerson.getEmail() != null){
            oldPerson.setEmail(newPerson.getEmail());
        }
        if (newPerson.getAddress() != null) {
            oldPerson.setAddress(newPerson.getAddress());
        }
        if (newPerson.getPhones() != null){
            oldPerson.setPhones(newPerson.getPhones());
        }
        em.getTransaction().begin();
        oldPerson = em.merge(newPerson);
        em.getTransaction().commit();
        return oldPerson;
    }


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

}

