package com.annimon.scheduler.data;

/**
 * Абстрактное представление сущности.
 * @author aNNiMON
 */
public abstract class Entity {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        
        if (!(obj instanceof Entity)) {
            return false;
        }
        return true;
    }

}
