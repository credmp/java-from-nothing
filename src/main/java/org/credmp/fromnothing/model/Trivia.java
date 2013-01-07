package org.credmp.fromnothing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * Trivia is a remark an {@link Actor} made or a piece of knowledge about them.
 *
 * @author Arjen Wiersma <core@credmp.org>
 */
@Entity
public class Trivia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Actor actor;
    @Column
    private String text;
    @Version
    @Column(columnDefinition="int8 not null default 0")
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

    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
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
