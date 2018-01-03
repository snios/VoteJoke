package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Joke implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String jokeText;
    @OneToMany
    private List<Vote> votes;
    @OneToMany
    private List<Report> reports;
    @JoinColumn(name = "CATEGORY_ID",nullable = false)
    @ManyToOne()
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }
}
