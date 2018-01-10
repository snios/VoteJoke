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
public class VoteDarkBean {
    private ArrayList<Joke> jokes;
    private Joke selectedJoke;



    @EJB
    private DBManager dbManager;

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getJokesByCatId(2);
    }

    public ArrayList<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }

    public Joke getSelectedJoke() {
        return selectedJoke;
    }

    public void setSelectedJoke(Joke selectedJoke) {
        this.selectedJoke = selectedJoke;
    }


    public void report(){
        if(selectedJoke != null && !selectedJoke.getReportReason().equalsIgnoreCase("")){
            dbManager.reportJoke(selectedJoke.getId(), selectedJoke.getReportReason());
        }
    }

    public void vote(){
        if(selectedJoke != null && selectedJoke.getRatingValue()>0){
            dbManager.voteForJoke(selectedJoke.getId(), selectedJoke.getRatingValue());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you!", "You rated:" + selectedJoke.getRatingValue());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
