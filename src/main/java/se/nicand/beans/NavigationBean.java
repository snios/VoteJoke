package se.nicand.beans;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationBean implements Serializable{
    private String page ="jokecarousell.xhtml";


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
