package com.github.git_leon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 5/19/17.
 */
public class Introspection {
    /**
     * @param object     object whose fields to access
     * @param fieldClass type of field to retrieve
     * @return List of values
     */
    public static <ObjectToInspect, FieldType> List<FieldType> getFieldValues(ObjectToInspect object, Class<FieldType> fieldClass) {
        List<FieldType> fields = new ArrayList<>();
        for (Field f : object.getClass().getDeclaredFields()) {
            if (f.getType().equals(fieldClass)) {
                try {
                    boolean defaultAccess = f.isAccessible();
                    FieldType obj = fieldClass.cast(f.get(object));

                    f.setAccessible(true);
                    fields.add(obj);
                    f.setAccessible(defaultAccess);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new Error(e);
                }
            }
        }
        return fields;
    }
}
