package se.nicand.beans;

import org.primefaces.event.RateEvent;
import se.nicand.DBManager;
import se.nicand.entities.Joke;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class JokeBean implements Serializable {

    @EJB
    DBManager dbManager;

    private Joke selectedJoke;
    private List<Joke> jokes;
    private int rating = 4;

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getAllJokes();
    }

    public void onrate(RateEvent rateEvent) {
        UIComponent ratingComponent = rateEvent.getComponent();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you!", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        String jokeid = ratingComponent.getAttributes().get("selectedJoke").toString();
        dbManager.voteForJoke(jokeid,rating);

    }
    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
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
}
