package com.annimon.scheduler.data;

/**
 * Группы.
 * @author aNNiMON
 */
public class Groups extends Entity {

    private String name;
    private Short formationYear;
    private short strength;
    private Short educationFormId;
    private Integer specialityId;
    private Integer departmentId;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getFormationYear() {
        return formationYear;
    }

    public void setFormationYear(Short formationYear) {
        this.formationYear = formationYear;
    }

    public short getStrength() {
        return strength;
    }

    public void setStrength(short strength) {
        this.strength = strength;
    }

    public Short getEducationFormId() {
        return educationFormId;
    }
    
    public void setEducationFormId(Short educationFormId) {
        this.educationFormId = educationFormId;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }
    
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    
    @Override
    public String toString() {
        return "Groups{" + "name=" + name + ", formationYear=" + formationYear + ", strength=" + strength + ", educationFormId=" + educationFormId + ", specialityId=" + specialityId + ", departmentId=" + departmentId + '}';
    }
    
}
