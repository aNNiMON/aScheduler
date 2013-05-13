package com.annimon.scheduler.data;

/**
 * Кафедры.
 * @author aNNiMON
 */
public class Departments extends Entity {
    
    private Integer id;
    private String name;
    private Faculties faculty;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculties getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculties faculty) {
        this.faculty = faculty;
    }

}
