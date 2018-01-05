package se.nicand.beans;

import se.nicand.DBManager;
import se.nicand.entities.Joke;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class VoteBean implements Serializable {

    private List<Joke> jokes;

    @EJB
    private DBManager dbManager;

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getAllJokes();
    }

    public List<Joke> getJokes() {
        this.jokes = dbManager.getAllJokes();
        return this.jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }
}
