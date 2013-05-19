package com.annimon.scheduler.data;

/**
 * Аудитории.
 * @author aNNiMON
 */
public class Audiences extends Entity {
    
    private short number;
    private short type;
    private Short housing;
    private Short capacity;
   
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
    
    public String getFullNumber() {
        String text = " " + number;
        if ( (housing != null) && (housing != 0) ) {
            text += " " + housing.toString();
        }
        return text;
    }

    @Override
    public String toString() {
        return "Audiences{" + "number=" + number + ", type=" + type + ", housing=" + housing + ", capacity=" + capacity + '}';
    }

}
