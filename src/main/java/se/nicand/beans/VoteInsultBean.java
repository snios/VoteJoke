package se.nicand.beans;

import org.primefaces.event.RateEvent;
import se.nicand.DBManager;
import se.nicand.entities.Joke;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;

@RequestScoped
@Named
public class VoteInsultBean {



    private ArrayList<Joke> jokes;
    private int rating;
    private Joke selectedJoke;



    @EJB
    private DBManager dbManager;

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getJokesByCatId(5);
    }

    public ArrayList<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<Joke> jokes) {
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

    public void onrate(RateEvent rateEvent) {
        String selectedObjID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedObj");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you!", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        dbManager.voteForJoke(Long.valueOf(selectedObjID), ((Integer) rateEvent.getRating()).intValue());
    }

    public void report(){
        if(selectedJoke != null){
            dbManager.reportJoke(selectedJoke.getId(), "Fult Språk");
        }
    }
}
