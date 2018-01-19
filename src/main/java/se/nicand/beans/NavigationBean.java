/**
 * Andre Olofsson och Nicklas Öst
 * Course: Java EE
 * Date: 2018-01-19
 */
package se.nicand.beans;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationBean implements Serializable{
    private String page ="randomjoke.xhtml";
    private String header = "Welcome! Try your best or vote like the rest.";

    @Inject
    private SubmitJokeBean submitJokeBean;



    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        switch (page){
            case "darkjoke.xhtml":
                header = "Dark, dark jokes!";
                break;
            case "index.html":
                    header = "Vote on jokes!";
                    break;
            case "insultjoke.xhtml":
                header = "Insult me, please!";
                break;
            case "miscjoke.xhtml":
                header = "There is no place for these jokes...";
                break;
            case "nsfwjoke.xhtml":
                header = "Hide your bananas!";
                break;
            case "politics.xhtml":
                header = "Politics...";
                break;
            case "submitjoke.xhtml":
                submitJokeBean.setStatusText("");
                submitJokeBean.setCategoeryId(0);
                header = "Think you're funny, huh? Time to prove it!";
                break;
            case "sportjoke.xhtml":
                header = "Sports... How funny can it be?";
                break;
            case "randomjoke.xhtml":
                header = "GL HF!";
                break;
                default:
                    header = "Were are we?";
        }
        this.page = page;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {

        this.header = header;
    }
}
