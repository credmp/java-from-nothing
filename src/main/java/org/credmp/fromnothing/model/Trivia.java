package org.credmp.fromnothing.model;

/**
 * Trivia is a remark an {@link Actor} made or a piece of knowledge about them.
 *
 * @author Arjen Wiersma <core@credmp.org>
 */
public class Trivia {
    private long id;
    private Actor actor;
    private String text;
        
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

    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
