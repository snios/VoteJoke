/**
 * Andre Olofsson och Nicklas Öst
 * Course: Java EE
 * Date: 2018-01-19
 */
package se.nicand.beans;

import se.nicand.DBManager;
import se.nicand.entities.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Bean belongs to submitjoke.xhtml
 */
@Named
@SessionScoped
public class SubmitJokeBean implements Serializable{
    @EJB
    private DBManager dbManager;

    private List<Category> categories;
    private String jokeText = "";
    private int categoryId = 0;
    private String statusText = "";
    private String author = "";

    /**
     * Initializes bean with values that is needed
     */
    @PostConstruct
    public void init(){
        categories = dbManager.getCategories();
    }

    public SubmitJokeBean() {
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Submits a joke and persist it to the database by calling dbmanager
     * If author string is empty its value will be "unknown author" instead.
     * Also resets the strings after the method is called
     * If no category is picked it sets a new statustext and returns null
     * @return
     */
    public String submitJoke(){
        if(this.categoryId > 0){
            if(author.equalsIgnoreCase("")){
                author = "Unknown Author";
            }
            dbManager.submitJoke(jokeText,categoryId, author);
            jokeText = "";
            author = "";
            statusText = "Joke was successfully submitted!";
            return "index.jsf?faces-redirect=true";
        }else{
            statusText = "Joke was not submitted, make sure category was picked!";
        }
        return null;
    }
}
