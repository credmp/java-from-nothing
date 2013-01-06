package org.credmp.fromnothing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * A memorable moment from the movie
 *
 * @author Arjen Wiersma <core@credmp.org>
 */
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Movie movie;
    @Column
    private String text;
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
}
