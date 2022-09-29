package datafacades;

import entities.Hobby;
import entities.Movie;
import errorhandling.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.eclipse.persistence.jpa.JpaHelper.getEntityManager;

public class HobbyFacade implements IDataFacade {
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
    public Object create(Object o) {
        return null;
    }

    @Override
    public Object getById(int id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Hobby> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        List<Hobby> HobbyList = query.getResultList();
        return HobbyList;
    }

    @Override
    public Object update(Object o) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Object delete(int id) throws EntityNotFoundException {
        return null;
    }
}

