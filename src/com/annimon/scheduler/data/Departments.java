package com.annimon.scheduler.data;

/**
 * Кафедры.
 * @author aNNiMON
 */
public class Departments extends Entity {
    
    private String name;
    private Integer facultyId;
    private Faculties faculty;
    
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
    
    /*public Faculties getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }*/

}
