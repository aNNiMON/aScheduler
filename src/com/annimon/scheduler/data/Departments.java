package com.annimon.scheduler.data;

/**
 * Кафедры.
 * @author aNNiMON
 */
public class Departments extends Entity {
    
    private String name;
    private Integer facultyId;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
    
    @Override
    public String toString() {
        return "Departments{" + "name=" + name + ", facultyId=" + facultyId + '}';
    }

}
