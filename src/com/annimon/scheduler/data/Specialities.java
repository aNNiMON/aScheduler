package com.annimon.scheduler.data;

/**
 * Специальности.
 * @author aNNiMON
 */
public class Specialities extends Entity {

    private Integer id;
    private String code;
    private String name;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
