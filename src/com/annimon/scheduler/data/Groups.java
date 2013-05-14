package com.annimon.scheduler.data;

import java.util.Date;

/**
 * Группы.
 * @author aNNiMON
 */
public class Groups extends Entity {

    private String name;
    private Date formationYear;
    private short strength;
    private Integer educationFormId;
    //private EducationForms educationForm;
    private Integer specialityId;
    //private Specialities speciality;
    private Integer departmentId;
    //private Departments department;
    
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

    public Integer getEducationFormId() {
        return educationFormId;
    }
    
    public void setEducationFormId(Integer educationFormId) {
        this.educationFormId = educationFormId;
    }
    /*public EducationForms getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(EducationForms educationForm) {
        this.educationForm = educationForm;
    }*/

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }
    
    /*public Specialities getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Specialities speciality) {
        this.speciality = speciality;
    }*/
    
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /*public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }*/
}
