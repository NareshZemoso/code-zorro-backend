package com.zemoso.codezorro.usermanagement.helper;



import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Helper
{

    public static Object getRandVal(Class<?> fieldType) {
        Random random = new Random();
        if (fieldType.equals(String.class)) {
            return UUID.randomUUID().toString();
        } else if (Date.class.isAssignableFrom(fieldType)) {
            return Date.from(Instant.now().plus(Duration.ofDays(1 + new Random().nextInt(364))));
        } else if (fieldType.equals(Integer.TYPE) || fieldType.equals(Integer.class)) {
            return random.nextInt();
        } else if (fieldType.equals(Long.TYPE) || fieldType.equals(Long.class)) {
            return random.nextLong();
        }
        else if(fieldType.equals(Double.TYPE))
        {
            return random.nextDouble();
        }
        else if (Enum.class.isAssignableFrom(fieldType)) {
            Object[] enumValues = fieldType.getEnumConstants();
            return enumValues[random.nextInt(enumValues.length)];
        }
        else if(Set.class.isAssignableFrom(fieldType))
            return new HashSet<>();
        else
            return null;
    }


    public static Object populate(Object obj, Class cls)
    {
        if(cls.getSuperclass()==null)
            return obj;
        obj=populate(obj,cls.getSuperclass());
        Field[] objectFields= cls.getDeclaredFields();
        for(Field field:objectFields)
        {
            try {
                field.setAccessible(true);
                field.set(obj, getRandVal(field.getType()));
            }

            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return obj;
    }



}
