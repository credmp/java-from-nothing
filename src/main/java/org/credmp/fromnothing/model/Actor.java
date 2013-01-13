package org.credmp.fromnothing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * The actor or actress plays some role in a Movie. 
 * 
 * @author Arjen Wiersma <core@credmp.org>
 * @see Movie
 * @see MovieCredits
 */
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=true)
    private String lastName;
    @Column(nullable=true)
    private String nickName;
    @Column(nullable=true)
    private Date birthDate;
    @Column(nullable=true)
    private Date dateOfDeath;
    @Column(nullable=true)
    private String credit;
    @Version
    @Column(columnDefinition="bigint not null default 0")
    private long version;
                
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    /** 
     * @return the first name 
     */
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    /** 
     * @return the last name 
     */
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /** 
     * @return nickname for the actor/actress 
     */
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    /** 
     * @return date of birth for the actor 
     */
    public Date getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /** 
     * @return in case the actor has passed away, the date of this event 
     */
    public Date getDateOfDeath() {
        return dateOfDeath;
    }
    
    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    
    public String getCredit() {
        return credit;
    }
    
    /** 
     * @return credit for the person who collected information on this actor 
     */
    public void setCredit(String credit) {
        this.credit = credit;
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

    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append("id = " + "[" + id + "]" + NEW_LINE);
        result.append("firstName = " + "[" + firstName + "]" + NEW_LINE);
        result.append("lastName = " + "[" + lastName + "]" + NEW_LINE);
        result.append("nickName = " + "[" + nickName + "]" + NEW_LINE);
        result.append("birthDate = " + "[" + birthDate + "]" + NEW_LINE);
        result.append("dateOfDeath = " + "[" + dateOfDeath + "]" + NEW_LINE);
        result.append("credit = " + "[" + credit + "]" + NEW_LINE);
        result.append("version = " + "[" + version + "]" + NEW_LINE);

        result.append("}");
        
        return result.toString();
    }
}
