package main.java;

import main.resources.Identifiable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Collections;

class Model {
  {
    view = new View();
    createdObjects = new HashMap<>();
  }
  
  Model(){}

  int initializeObjects(String name) {
    namedObjects = createdObjects.get(name);
    if(namedObjects == null) {
      System.out.printf("No objects with name %s\n", name);
      return Constants.E_UNKNOWN_NAME;
    }
    if(namedObjects.size() != 1) {
      Object[] objs = namedObjects.toArray();
      // TODO: 20.10.2019 display list of objects
    }
    return Constants.SUCCESS;
  }
  
  int getObjectCount() {
    return namedObjects.size();
  }
  
  int initializeFields(int objNumber) {
    if(namedObjects == null) {
      return Constants.E_OBJECTS_UNINITIALIZED;
    }
    it = namedObjects.iterator();
    for(int i = 0; i < objNumber; i++) {
      neededObj = it.next();
    }
    fields = neededObj.getClass().getDeclaredFields();
    // TODO: 20.10.2019 display fields in view
    return Constants.SUCCESS;
  }

  int getFieldCount() {
    return fields.length;
  }

  int create(Constructor ctor, String name, Object[] parameters) {
    Identifiable obj;
    try {
      obj = (Identifiable)ctor.newInstance(parameters);
    } catch (InstantiationException ex) {
      System.out.printf("Can't instantiate an object.\nMessage: %s\n", ex.getMessage());
      return Constants.E_CAN_NOT_INSTANTIATE;
    } catch (IllegalAccessException ex) {
      System.out.printf("Can't access constructor.\nMessage: %s\n", ex.getMessage());
      return Constants.E_ILLEGAL_ACCESS;
    } catch (InvocationTargetException ex) {
      Throwable cause = ex.getCause();
      if(cause != null) {
        System.out.printf("Invocation of constructor failed because of %s.\nMessage: %s\n",
            cause.getMessage(), ex.getMessage());
      } else {
        System.out.printf("Invocation of constructor failed with no cause.\nMessage: %s\n",
            ex.getMessage());
      }
      return Constants.E_CAN_NOT_INVOKE;
    }
    obj.setId(name);
    TreeSet<Object> namedObjects = createdObjects.get(name);
    if(namedObjects == null) {
      createdObjects.put(name, new TreeSet<>(Collections.singletonList(obj)));
    } else {
      namedObjects.add(obj);
    }
    return 0;
  }
  
  int read(int objNumber) {
    if(namedObjects == null) {
      return Constants.E_OBJECTS_UNINITIALIZED;
    }
    it = namedObjects.iterator();
    if(objNumber > 0) {
      for(int i = 0; i < objNumber - 1; i++) {
        it.next();
      }
      // TODO: 20.10.2019 display it in view
    } else {
      while(it.hasNext()) {
        // TODO: 20.10.2019 display it in view
        it.next();
      }
    }
    return Constants.SUCCESS;
  }

  int update(int fieldNumber, String newValue) {
    if(fields == null) {
      return Constants.E_FIELDS_UNINITIALIZED;
    }
    Class t = fields[fieldNumber].getClass();
    String fieldName = fields[fieldNumber].getName();
    String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    Method setter = null;
    try {
      setter = neededObj.getClass().getDeclaredMethod(setterName);
    } catch (NoSuchMethodException ex) {
      System.out.printf("Can't get setter for %s.\nMessage: %s\n", fieldName, ex.getMessage());
      return Constants.E_NO_SETTER;
    }
    if (t == Integer.class) {
      try {
        setter.invoke(neededObj, Integer.parseInt(newValue));
      } catch (IllegalAccessException ex) {
        System.out.printf("Can't set new string value.\nMessage: %s\n", ex.getMessage());
        return Constants.E_ILLEGAL_ACCESS;
      } catch (NumberFormatException ex) {
        System.out.printf("Can't convert string to int.\nMessage: %s\n", ex.getMessage());
        return Constants.E_STR_TO_INT;
      } catch (InvocationTargetException ex) {
        System.out.printf("Invocation of setter failed.\nMessage: %s\n", ex.getMessage());
        return Constants.E_CAN_NOT_INVOKE;
      }
    } else if (t == String.class) {
      try {
        setter.invoke(neededObj, newValue);
      } catch (IllegalAccessException ex) {
        System.out.printf("Can't set new string value.\nMessage: %s\n", ex.getMessage());
        return Constants.E_ILLEGAL_ACCESS;
      } catch (InvocationTargetException ex) {
        System.out.printf("Invocation of setter failed.\nMessage: %s\n", ex.getMessage());
        return Constants.E_CAN_NOT_INVOKE;
      }
    } else {
      // TODO: 20.10.2019 find appropriate object with name = newValue
      try {
        setter.invoke(neededObj, newValue);
      } catch (IllegalAccessException ex) {
        System.out.printf("Can't set new string value.\nMessage: %s\n", ex.getMessage());
        return Constants.E_ILLEGAL_ACCESS;
      } catch (InvocationTargetException ex) {
        System.out.printf("Invocation of setter failed.\nMessage: %s\n", ex.getMessage());
        return Constants.E_CAN_NOT_INVOKE;
      }
    }
    return Constants.SUCCESS;
  }

  int delete(String name) {
    return 0;
  }

  private View view;
  private Map<String, TreeSet<Object>> createdObjects;
  private TreeSet<Object> namedObjects;
  private Iterator<Object> it;
  Object neededObj;
  private Field[] fields;
}
