package com.annimon.scheduler.data;

/**
 * Преподаватели.
 * @author aNNiMON
 */
public class Professors extends Entity {

    private Integer id;
    private String lastname;
    private String firstname;
    private String middlename;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
}
