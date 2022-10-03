package businessfacades;

import datafacades.IDataFacade;
import datafacades.HobbyFacade;
import dtos.HobbyDTO;
import entities.Hobby;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

import java.util.List;

public class HobbyDTOFacade implements IDataFacade<HobbyDTO> {
    private static IDataFacade<HobbyDTO> instance;
    private static IDataFacade<Hobby> hobbyFacade;

    //Private Constructor to ensure Singleton
    private HobbyDTOFacade() {}

    public static IDataFacade<HobbyDTO> getFacade() {
        if (instance == null) {
            hobbyFacade = HobbyFacade.getHobbyFacade(EMF_Creator.createEntityManagerFactory());
            instance = new HobbyDTOFacade();
        }
        return instance;
    }

    @Override
    public HobbyDTO create(HobbyDTO HobbyDTO) {
        Hobby m = HobbyDTO.getEntity();
        m = hobbyFacade.create(m);
        return new HobbyDTO(m);
    }

    @Override
    public HobbyDTO getById(int id) throws EntityNotFoundException {
        return new HobbyDTO(hobbyFacade.getById(id));
    }

    @Override
    public List<HobbyDTO> getAll() {
        return HobbyDTO.toList(hobbyFacade.getAll());
    }

    @Override
    public HobbyDTO update(HobbyDTO HobbyDTO) throws EntityNotFoundException {
        Hobby m = hobbyFacade.update(HobbyDTO.getEntity());
        return new HobbyDTO(m);
    }

    @Override
    public HobbyDTO delete(int id) throws EntityNotFoundException {
        return new HobbyDTO(hobbyFacade.delete(id));
    }

}

