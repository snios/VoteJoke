package se.nicand;

import se.nicand.entities.Category;
import se.nicand.entities.Joke;
import se.nicand.entities.Joke_;
import se.nicand.entities.Vote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<Joke> getAllJokes(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Joke>  cq = cb.createQuery(Joke.class);
        List<Joke> jokes = em.createQuery(cq).getResultList();
        return jokes;
    }

    public void getJokesByCategory(){

    }

    public List<Category> getCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        List<Category> result = em.createQuery(cq).getResultList();
        return  result;

    }

    public void voteForJoke(Joke joke, int rating){
        Vote vote = new Vote();
        vote.setJoke(joke);
        vote.setValue(rating);
        em.persist(vote);

    }
}
