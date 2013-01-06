package org.credmp.fromnothing.model;

/**
 * Movie credits combines the {@link Actor} with the {@link Movie}. 
 *
 * Each actor is placed at a certain point in the credits, the higher you are 
 * put the more influential your role was.
 *
 * @author Arjen Wiersma <core@credmp.org>
 * @see Actor
 * @see Movie
 */
public class MovieCredits {
    private long id;
    private Actor actor;
    private Movie movie;

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }
    
    public void setActor(Actor actor) {
        this.actor = actor;
    }
        
    public Movie getMovie() {
        return movie;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
