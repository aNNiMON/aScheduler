package com.annimon.scheduler.data;

/**
 * Предметы.
 * @author aNNiMON
 */
public class Subjects extends Entity {

    private String name;
    private String abbreviation;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "Subjects{" + "name=" + name + ", abbreviation=" + abbreviation + '}';
    }
    
}
