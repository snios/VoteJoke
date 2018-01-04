package se.nicand.beans;

import se.nicand.DBManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SubmitJokeBean implements Serializable{
    @EJB
    private DBManager dbManager;


    private String jokeText = "";
    private int categoryId = 0;

    public SubmitJokeBean() {
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public int getCategoeryId() {
        return categoryId;
    }

    public void setCategoeryId(int categoeryId) {
        this.categoryId = categoeryId;
    }

    public void submitJoke(){
        if(this.categoryId > 0){
            dbManager.submitJoke(jokeText,categoryId);
            jokeText = "success";
        }else{
            jokeText = "failed";

        }

    }
}
