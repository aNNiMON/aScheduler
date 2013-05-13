package com.annimon.scheduler.data;

/**
 * Аудитории.
 * @author aNNiMON
 */
public class Audiences extends Entity {
    
    private Integer id;
    private short number;
    private short type;
    private Short housing;
    private Short capacity;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public Short getHousing() {
        return housing;
    }

    public void setHousing(Short housing) {
        this.housing = housing;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }
}