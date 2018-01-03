package se.nicand;

import se.nicand.entities.Category;
import se.nicand.entities.Joke;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "se.nicand.DBManager")
public class DBManager {
    @PersistenceContext(name = "jokedb")
    private EntityManager em;

    public void submitJoke(String jokeText,int categoryId){
       Category category = em.find(Category.class,Long.valueOf(categoryId));
       System.out.println(category.getDescription());
       Joke joke1 = new Joke();
       joke1.setCategory(category);
       joke1.setJokeText(jokeText);
       em.persist(joke1);

    }
}
