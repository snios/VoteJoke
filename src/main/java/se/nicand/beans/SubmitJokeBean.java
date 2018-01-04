package se.nicand.beans;

import se.nicand.DBManager;
import se.nicand.entities.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class SubmitJokeBean implements Serializable{
    @EJB
    private DBManager dbManager;

    private List<Category> categories;
    private String jokeText = "";
    private int categoryId = 0;

    @PostConstruct
    public void init(){
        categories = dbManager.getCategories();
    }

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

    public List<Category> getCategories() {
        this.categories = dbManager.getCategories();
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String submitJoke(){
        if(this.categoryId > 0){
            dbManager.submitJoke(jokeText,categoryId);
            jokeText = "success";
            return "index.jsf?faces-redirect=true";
        }else{
            jokeText = "failed";

        }

        return null;
    }
}
