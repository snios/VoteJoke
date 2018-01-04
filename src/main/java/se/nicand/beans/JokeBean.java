package se.nicand.beans;

import se.nicand.DBManager;
import se.nicand.entities.Joke;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class JokeBean implements Serializable {

    @EJB
    DBManager dbManager;

    private List<Joke> jokes;
    private String  snipp = "snippa";

    @PostConstruct
    public void init(){
        this.jokes = dbManager.getAllJokes();
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Joke> getJokes() {
        this.jokes = dbManager.getAllJokes();
        return this.jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public String getSnipp() {
        return snipp;
    }

    public void setSnipp(String snipp) {
        this.snipp = snipp;
    }
}
