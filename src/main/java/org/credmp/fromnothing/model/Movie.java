package org.credmp.fromnothing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * A Movie 
 *
 * @author Arjen Wiersma <core@credmp.org>
 * @see Actor
 */
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private Integer year;
    @Column(nullable=false)
    private String title;
    @Column
    private String subtitle;
    @Column
    private Double rating;
    @Version
    @Column(columnDefinition="bigint not null default 0")
    private long version;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSubtitle() {
        return subtitle;
    }
    
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
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
