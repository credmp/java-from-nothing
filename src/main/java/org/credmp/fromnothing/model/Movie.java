package org.credmp.fromnothing.model;

/**
 * A Movie 
 *
 * @author Arjen Wiersma <core@credmp.org>
 * @see Actor
 */
public class Movie {
    private long id;
    private Integer year;
    private String title;
    private String subtitle;
    private Double rating;
    
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
}
