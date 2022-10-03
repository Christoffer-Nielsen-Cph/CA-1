/* package businessfacades;

import datafacades.IDataFacade;
import datafacades.PhoneFacade;
import entities.Phone;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import java.util.List;

public class PhoneDTOFacade implements IDataFacade<PhoneDTO> {

    private static IDataFacade<PhoneDTO> instance;
    private static IDataFacade<Phone> phoneFacade;

    //Private Constructor to ensure Singleton
    private PhoneDTOFacade() {}

    public static IDataFacade<PhoneDTO> getFacade() {
        if (instance == null) {
            phoneFacade = PhoneFacade.getPhoneFacade(EMF_Creator.createEntityManagerFactory());
            instance = new PhoneDTOFacade();
        }
        return instance;
    }
//Fix dette
    @Override
    public PhoneDTO create(PhoneDTO phoneDTO) {
        Phone p = phoneDTO.getEntity();
        p = phoneFacade.create(p);
        return new PhoneDTO(p);
    }


    @Override
    public PhoneDTO getById(int id) throws EntityNotFoundException {
        return new PhoneDTO(phoneFacade.getById(id));
    }

    @Override
    public List<PhoneDTO> getAll() {
        return PhoneDTO.toList(phoneFacade.getAll());
    }



    @Override
    public PhoneDTO update(PhoneDTO phoneDTO) throws EntityNotFoundException {
        Phone p = phoneFacade.update(phoneDTO.getEntity());
        return new PhoneDTO(p);
    }

    @Override
    public PhoneDTO delete(int id) throws EntityNotFoundException {
        return new PhoneDTO(phoneFacade.delete(id));
    }

}

 */
