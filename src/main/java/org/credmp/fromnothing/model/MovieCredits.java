package org.credmp.fromnothing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

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
@Entity
public class MovieCredits {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Actor actor;
    @OneToOne
    private Movie movie;
    @Column
    private Integer ranking;
    @Version
    @Column(columnDefinition="bigint not null default 0")
    private long version;
    
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

    /**
     * @return the ranking of the {@link Actor} on the credits of the {@link Movie}
     */
    public Integer getRanking() {
        return ranking;
    }
    
    /**
     * @param ranking the ranking of the {@link Actor} on the credits of the {@link Movie}
     */
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    /**
     * @return text
     */
    public long getVersion() {
        return version;
    }
    
    /**
     * @param version
     */
    public void setVersion(long version) {
        this.version = version;
    }
}
