package com.annimon.scheduler.data;

/**
 * Формы обучения.
 * @author aNNiMON
 */
public class EducationForms extends Entity {

    private Short id;
    private String type;
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
