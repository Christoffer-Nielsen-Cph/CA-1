package datafacades;

import entities.Cityinfo;
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
public class CityInfoFacade implements IDataFacade<Cityinfo> {

    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityInfoFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static IDataFacade<Cityinfo> getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Cityinfo create(Cityinfo c){
        return c;
    }

    @Override
    public Cityinfo getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Cityinfo c = em.find(Cityinfo.class, id);
        if (c == null)
            throw new EntityNotFoundException("The CityInfo entity with ID: "+id+" Was not found");
        return c;
    }

    @Override
    public List<Cityinfo> getAll(){
        EntityManager em = getEntityManager();
        TypedQuery<Cityinfo> query = em.createQuery("SELECT c FROM Cityinfo c", Cityinfo.class);
        List<Cityinfo> cityinfos = query.getResultList();
        return cityinfos;
    }

    @Override
    public Cityinfo update(Cityinfo cityinfo) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Cityinfo delete(int id) throws EntityNotFoundException {
        return null;
    }


}
