/**
 * Andre Olofsson och Nicklas Öst
 * Course: Java EE
 * Date: 2018-01-19
 */
package se.nicand.beans;

import org.primefaces.event.RateEvent;
import se.nicand.DBManager;
import se.nicand.entities.Joke;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Named
@SessionScoped
public class JokeBean implements Serializable {

    @EJB
    DBManager dbManager;

    private Joke selectedJoke;
    private List<Joke> jokes;
    private Joke ranJoke;
    private int rating = 4;

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getAllJokes();
        getRandomJoke();

    }

    public List<Joke> getJokes() {
        this.jokes = dbManager.getAllJokes();
        return this.jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Joke getSelectedJoke() {
        return selectedJoke;
    }

    public void setSelectedJoke(Joke selectedJoke) {
        this.selectedJoke = selectedJoke;
    }

    public void setRanJoke(Joke randomJoke) {
        this.ranJoke = randomJoke;
    }

    public Joke getRanJoke() {
        return ranJoke;
    }

    public void getRandomJoke(){
        this.jokes = dbManager.getAllJokes();
        int i = jokes.size();
        int randomValue = ThreadLocalRandom.current().nextInt(0,i);
        Joke oldJoke = this.ranJoke;
        this.ranJoke = this.jokes.get(randomValue);
        if(this.ranJoke == oldJoke){
            while(this.ranJoke == oldJoke){
                randomValue = ThreadLocalRandom.current().nextInt(0,i);
                this.ranJoke = this.jokes.get(randomValue);
            }
        }
    }

}
