package se.nicand.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Joke implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1024)
    private String jokeText;
    private boolean isDisabled;
    private String author;
    @OneToMany(mappedBy = "joke")
    private List<Vote> votes;
    @OneToMany (mappedBy = "joke")
    private List<Report> reports;
    @JoinColumn(name = "CATEGORY_ID",nullable = false)
    @ManyToOne
    private Category category;

    @Transient
    private int ratingValue = 0;
    @Transient
    private String reportReason = "";
    private double avarageRating = 0;

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

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        author = author;
    }

    public double getAvarageRating(){
        double rating = 0;
        int nVotes = 0;
        if(votes.size() > 0){
            for(Vote v: votes){
                rating += v.getValue();
                nVotes++;
            }
            return rating / nVotes;
        }
       return 0;
    }
}
