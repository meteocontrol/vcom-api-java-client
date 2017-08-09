package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseModel {

    @Override
    public int hashCode() {
        return Objects.hash(this.getPropertyValues());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        return obj.hashCode() == this.hashCode();
    }

    private List getPropertyValues() {
        List<Object> values = new ArrayList<>();
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ModelProperty.class)) {
                try {
                    values.add(method.invoke(this));
                } catch (IllegalAccessException | InvocationTargetException ignore) {
                }
            }
        }
        return values;
    }
}
