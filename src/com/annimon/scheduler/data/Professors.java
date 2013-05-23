package com.annimon.scheduler.data;

/**
 * Преподаватели.
 * @author aNNiMON
 */
public class Professors extends Entity {

    private String lastname;
    private String firstname;
    private String middlename;
    
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
    
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append(lastname).append(' ');
        sb.append(firstname.charAt(0)).append('.');
        sb.append(middlename.charAt(0)).append('.');
        return sb.toString();
    }

    @Override
    public String toString() {
        return lastname + ' ' + firstname + ' ' + middlename;
    }
    
}
