/**
 * Andre Olofsson och Nicklas Öst
 * Course: Java EE
 * Date: 2018-01-19
 */
package se.nicand;

import se.nicand.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "se.nicand.DBManager")
public class DBManager {
    @PersistenceContext(name = "jokedb")
    private EntityManager em;

    /**
     * Submits joke to database
     * @param jokeText
     * @param categoryId
     * @param author
     */
    public void submitJoke(String jokeText, int categoryId, String author){
       Category category = em.find(Category.class,Long.valueOf(categoryId));
       System.out.println(category.getDescription());
       Joke joke1 = new Joke();
       joke1.setCategory(category);
       joke1.setJokeText(jokeText);
       joke1.setAuthor(author);
       em.persist(joke1);

    }

    /**
     * Returns all jokes from database if they are not flagged as disabled
     * @return
     */
    public List<Joke> getAllJokes(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Joke>  cq = cb.createQuery(Joke.class);
        Root<Joke> jokeRoot = cq.from(Joke.class);
        cq.where(cb.equal(jokeRoot.get(Joke_.isDisabled),false));
        List<Joke> jokes = em.createQuery(cq).getResultList();
        return jokes;
    }

    /**
     * Returns all jokes from db for a specific category that are not flagged as disabled
     * @param categoryId
     * @return
     */
    public ArrayList<Joke> getJokesByCatId(int categoryId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Joke>  cq = cb.createQuery(Joke.class);
        List<Joke> jokes = em.createQuery(cq).getResultList();
        ArrayList<Joke> catJoke = new ArrayList<Joke>();
        for(Joke jk : jokes){
            if(jk.getCategory().getId() == categoryId && jk.isDisabled() == false){
                catJoke.add(jk);
            }
        }
        return catJoke;
    }

    /**
     * Returns all categories from db
     * @return
     */
    public List<Category> getCategories() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        List<Category> result = em.createQuery(cq).getResultList();
        return  result;

    }

    /**
     * Persists a new vote for a specific joke
     * @param jokeid
     * @param rating
     */
    public void voteForJoke(long jokeid, int rating){
        Vote vote = new Vote();
        Joke joke = em.find(Joke.class,jokeid);
        vote.setJoke(joke);
        vote.setValue(rating);
        em.persist(vote);
        em.flush();
    }

    /**
     * Persists a new report for a specific joke together with a reason
     * Also flags joke as disabled if it has more than 9 reports
     * @param jokeid
     * @param reason
     */
    public void reportJoke(long jokeid, String reason){
        Report report = new Report();
        Joke joke = em.find(Joke.class,jokeid);
        if (joke.getReports().size() == 9){
            joke.setDisabled(true);
        }
        report.setJoke(joke);
        report.setReason(reason);
        em.persist(report);
        em.flush();
    }


}
