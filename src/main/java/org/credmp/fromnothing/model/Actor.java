package org.credmp.fromnothing.model;

import java.util.Date;

/**
 * The actor or actress plays some role in a Movie. 
 * 
 * @author Arjen Wiersma <core@credmp.org>
 * @see Movie
 * @see MovieCredits
 */
public class Actor {
    private long id;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date birthDate;
    private Date dateOfDeath;
    private String credit;
            
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
}
