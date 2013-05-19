package com.annimon.scheduler.dao;

/**
 * Хранитель IDAO объектов.
 * @author aNNiMON
 */
public class DAOKeeper {

    private static AudienceDAO audienceDAO;
    private static DepartmentDAO departmentDAO;
    private static EducationFormDAO educationFormDAO;
    private static FacultyDAO facultyDAO;
    private static GroupDAO groupDAO;
    private static PairDAO pairDAO;
    private static ProfessorDAO professorDAO;
    private static SpecialityDAO specialityDAO;
    private static SubjectDAO subjectDAO;

    public static synchronized AudienceDAO getAudienceDAO() {
        if (audienceDAO == null) {
            audienceDAO = new AudienceDAO();
        }
        return audienceDAO;
    }

    public static synchronized DepartmentDAO getDepartmentDAO() {
        if (departmentDAO == null) {
            departmentDAO = new DepartmentDAO();
        }
        return departmentDAO;
    }

    public static synchronized EducationFormDAO getEducationFormDAO() {
        if (educationFormDAO == null) {
            educationFormDAO = new EducationFormDAO();
        }
        return educationFormDAO;
    }

    public static synchronized FacultyDAO getFacultyDAO() {
        if (facultyDAO == null) {
            facultyDAO = new FacultyDAO();
        }
        return facultyDAO;
    }

    public static synchronized GroupDAO getGroupDAO() {
        if (groupDAO == null) {
            groupDAO = new GroupDAO();
        }
        return groupDAO;
    }

    public static synchronized PairDAO getPairDAO() {
        if (pairDAO == null) {
            pairDAO = new PairDAO();
        }
        return pairDAO;
    }

    public static synchronized ProfessorDAO getProfessorDAO() {
        if (professorDAO == null) {
            professorDAO = new ProfessorDAO();
        }
        return professorDAO;
    }

    public static synchronized SpecialityDAO getSpecialityDAO() {
        if (specialityDAO == null) {
            specialityDAO = new SpecialityDAO();
        }
        return specialityDAO;
    }

    public static synchronized SubjectDAO getSubjectDAO() {
        if (subjectDAO == null) {
            subjectDAO = new SubjectDAO();
        }
        return subjectDAO;
    }
    
}
