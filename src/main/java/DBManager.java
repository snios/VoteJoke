import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "DBManager")
public class DBManager {
    @PersistenceContext(name = "jokedb")
    private EntityManager em;
}
