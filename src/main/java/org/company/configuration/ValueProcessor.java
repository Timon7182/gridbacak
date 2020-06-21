package org.company.configuration;

import org.apache.commons.configuration2.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface ValueProcessor {

    default void processAnnotations() throws IllegalAccessException {
        Class<?> clazz = getClass();
        List<Field> fields= new ArrayList<>();
        Collections.addAll(fields, clazz.getDeclaredFields());
        addField(clazz, fields);
        Configuration configuration = AppConfig.getConfig();
        for (Field field:fields){
            if (!field.isAnnotationPresent(Value.class))
                continue;

            Value value= field.getAnnotation(Value.class);
            String def = value.defaultValue();

            field.setAccessible(true);

            if (field.getType()== String.class){
                field.set(this, configuration.getString(value.name(), (!def.isEmpty())? def : null));
                continue;
            }
            if (field.getType()== Integer.class || field.getType()==int.class){
                field.set(this, configuration.getInt(value.name(), (!def.isEmpty())? Integer.parseInt(def):0));
                continue;
            }
            if (field.getType()== Float.class){
                field.set(this, configuration.getFloat(value.name(),(!def.isEmpty())? Float.parseFloat(def):0));
                continue;
            }
            if(field.getType()== Long.class){
                field.set(this, configuration.getLong(value.name(),(!def.isEmpty())? Long.parseLong(def):0));
                continue;

            }
            if (field.getType()== Double.class){
                field.set(this, configuration.getDouble(value.name(),(!def.isEmpty())? Double.parseDouble(def):0));
                continue;
            }
            if (field.getType()==char.class){
                field.set(this, configuration.getByte(value.name(),(!def.isEmpty())? (byte) def.charAt(0):0));
                continue;
            }
            if (field.getType()== Short.class){
                field.set(this, configuration.getShort(value.name(),(!def.isEmpty())? Short.parseShort(def):0));
                continue;
            }
        }


    }

    static void addField(Class<?> clazz, List<Field> fields) {

    }
}
