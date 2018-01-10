package se.nicand.beans;

import org.primefaces.event.RateEvent;
import se.nicand.DBManager;
import se.nicand.entities.Joke;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@RequestScoped
@Named
public class VoteSportBean implements Serializable{

    private ArrayList<Joke> jokes;
    private int rating = 0;
    private Joke selectedJoke;
    private String reportReason = "";
    private String reportReasonTwo = "";
    private Joke selectedJokeTwo;



    @EJB
    private DBManager dbManager;

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getJokesByCatId(3);
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

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public Joke getSelectedJokeTwo() {
        return selectedJokeTwo;
    }

    public void setSelectedJokeTwo(Joke selectedJokeTwo) {
        this.selectedJokeTwo = selectedJokeTwo;
    }

    public String getReportReasonTwo() {
        return reportReasonTwo;
    }

    public void setReportReasonTwo(String reportReasonTwo) {
        this.reportReasonTwo = reportReasonTwo;
    }

    public void onrate(RateEvent rateEvent) {
        String selectedObjID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedObj");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you!", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        dbManager.voteForJoke(Long.valueOf(selectedObjID), ((Integer) rateEvent.getRating()).intValue());
        //Todo: Call dbmanager

    }

    public void report(){
        if(selectedJoke != null && !selectedJoke.getReportReason().equalsIgnoreCase("")){
            dbManager.reportJoke(selectedJoke.getId(), selectedJoke.getReportReason());
            reportReason = "";
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
