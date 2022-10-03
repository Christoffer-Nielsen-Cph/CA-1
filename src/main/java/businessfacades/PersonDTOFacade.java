package businessfacades;

import datafacades.IDataFacade;
import datafacades.PersonFacade;
import dtos.PersonDTO;
import entities.Person;
import entities.Phone;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonDTOFacade implements IDataFacade<PersonDTO> {
    private static IDataFacade<PersonDTO> instance;
    private static IDataFacade<Person> personFacade;
    private static PersonFacade extraMethods;

    //Private Constructor to ensure Singleton
    private PersonDTOFacade() {}

    public static IDataFacade<PersonDTO> getFacade() {
        if (instance == null) {
            personFacade = PersonFacade.getPersonFacade(EMF_Creator.createEntityManagerFactory());
            instance = new PersonDTOFacade();
        }
        return instance;
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        Person p = personDTO.getEntity();
        p = personFacade.create(p);
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO getById(int id) throws EntityNotFoundException {
        return new PersonDTO(personFacade.getById(id));
    }

    @Override
    public List<PersonDTO> getAll() {
        return PersonDTO.toList(personFacade.getAll());
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) throws EntityNotFoundException {
        Person p = personFacade.update(personDTO.getEntity());
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO delete(int id) throws EntityNotFoundException {
        return new PersonDTO(personFacade.delete(id));
    }

    public PersonDTO addPhoneToPerson(int phoneID, int personID) throws EntityNotFoundException {
        Person p = extraMethods.addPhoneToPerson(phoneID,personID);
        return new PersonDTO(p);

    }

    public PersonDTO addHobbyToPerson(int hobbyID, int personID) throws EntityNotFoundException{
        Person p = extraMethods.addHobbyToPerson(hobbyID,personID);
        return new PersonDTO(p);
    }

}
