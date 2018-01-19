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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * Bean belongs to insultjoke.xhtml
 */
@RequestScoped
@Named
public class VoteInsultBean {
    private ArrayList<Joke> jokes;
    private Joke selectedJoke;



    @EJB
    private DBManager dbManager;
    /**
     * Initializes bean with values that is needed
     */
    @PostConstruct
    public void init(){
        refreshList();
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

    /**
     * Method to report a joke  and calls the dbmanager to persist it.
     * shows a pop-up in frontend and refresh joke list
     */
    public void report(){
        if(selectedJoke != null && !selectedJoke.getReportReason().equalsIgnoreCase("")){
            dbManager.reportJoke(selectedJoke.getId(), selectedJoke.getReportReason());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you!", "Your report was submitted!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            refreshList();
        }
    }
    /**
     * Method to vote on a joke and calls the dbmanager to persist it
     * shows a pop-up in frontend and refresh joke list
     */
    public void vote(){
        if(selectedJoke != null && selectedJoke.getRatingValue()>0){
            dbManager.voteForJoke(selectedJoke.getId(), selectedJoke.getRatingValue());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you!", "You rated:" + selectedJoke.getRatingValue());
            FacesContext.getCurrentInstance().addMessage(null, message);
            refreshList();
        }
    }
    /**
     * Calls dbmanager to get jokes and sorts joke on average rating
     */
    public void refreshList(){
        this.jokes = dbManager.getJokesByCatId(5);
        this.jokes.sort(Comparator.comparing(Joke::getAvarageRating).reversed());
    }
}
