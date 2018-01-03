package beans;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.*;

@Named
@SessionScoped
public class SubmitJokeBean implements Serializable{

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
}
