package org.credmp.fromnothing.model;

/**
 * A memorable moment from the movie
 *
 * @author Arjen Wiersma <core@credmp.org>
 */
public class Quote {
    private long id;
    private Movie movie;
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
