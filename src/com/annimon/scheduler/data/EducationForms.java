package com.annimon.scheduler.data;

/**
 * Формы обучения.
 * @author aNNiMON
 */
public class EducationForms extends Entity {

    private String type;

    public Short get_Id() {
        return super.getId().shortValue();
    }

    public void set_Id(Short id) {
        super.setId(id.intValue());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
