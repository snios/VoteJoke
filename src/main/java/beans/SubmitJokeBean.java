package beans;

import entities.Category;
import entities.Joke;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.*;
import java.util.Locale;

@Named
@SessionScoped
public class SubmitJokeBean implements Serializable{

    @PersistenceContext(name = "jokedb")
    private EntityManager em;

    private String jokeText = "";
    private int categoeryId = 0;

    public SubmitJokeBean() {
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public int getCategoeryId() {
        return categoeryId;
    }

    public void setCategoeryId(int categoeryId) {
        this.categoeryId = categoeryId;
    }

    public void submitJoke(){
        if(this.categoeryId > 0){
            //Joke newJoke =  em.createQuery("select j from Joke j where j.id=1", Joke.class).getSingleResult();
            //this.jokeText = newJoke.getCategory().getDescription();

            //Long id = new Long(this.categoeryId);

            Category cat = em.createQuery("select c from Category c where c.id =" + this.categoeryId, Category.class).getSingleResult();
            this.jokeText = cat.getDescription();
            /*Joke joke = new Joke();
            joke.setJokeText(this.jokeText);
            joke.setCategory(cat);
            em.persist(joke);*/

            //this.jokeText = cat.getDescription();

            //newJoke.setJokeText(this.jokeText);
            //newJoke.setCategory(cat);
            //em.persist(newJoke);*/

            /*CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Category> cq = cb.createQuery(Category.class);
            Root<Category> root = cq.from(Category.class);
            cq.select(root);
            cq.where(cb.equal(root.get(Category.)*/

            this.jokeText = "Success";

        }else{
            this.jokeText = "Pick category";
        }
    }
}
