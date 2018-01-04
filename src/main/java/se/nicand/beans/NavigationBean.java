package se.nicand.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationBean implements Serializable{

    private String page ="menubar.xhtml";

    @PostConstruct
    public void init(){
        page="menubar.xhtml";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
