package com.annimon.scheduler.data;

import java.util.Date;

/**
 * Группы.
 * @author aNNiMON
 */
public class Groups extends Entity {

    private Integer id;
    private String name;
    private Date formationYear;
    private short strength;
    private EducationForms educationForm;
    private Specialities speciality;
    private Departments department;
    
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

    public Date getFormationYear() {
        return formationYear;
    }

    public void setFormationYear(Date formationYear) {
        this.formationYear = formationYear;
    }

    public short getStrength() {
        return strength;
    }

    public void setStrength(short strength) {
        this.strength = strength;
    }

    public EducationForms getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(EducationForms educationForm) {
        this.educationForm = educationForm;
    }

    public Specialities getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Specialities speciality) {
        this.speciality = speciality;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }
}
