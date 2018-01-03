package se.nicand;

import se.nicand.entities.Joke;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "se.nicand.DBManager")
public class DBManager {
    @PersistenceContext(name = "jokedb")
    private EntityManager em;

    public void submitJoke(Joke joke){
        em.persist(joke);
    }
}
