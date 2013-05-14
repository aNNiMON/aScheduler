package com.annimon.scheduler.data;

/**
 * Абстрактное представление сущности.
 * @author aNNiMON
 */
public abstract class Entity {
    
    private Integer id;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        
        if (!(obj instanceof Entity)) {
            return false;
        }
        return true;
    }

}
