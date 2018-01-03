package se.nicand.beans;

import se.nicand.DBManager;

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
    @EJB
    private DBManager dbManager;


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
            dbManager.submitJoke(jokeText,categoeryId);
            jokeText = "success";
        }else{
            jokeText = "failed";

        }

    }
}
