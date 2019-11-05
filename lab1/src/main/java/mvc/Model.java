package main.java.mvc;

import main.java.entities.GenericEntity;
import main.java.mvc.dao.DaoFactory;
import main.java.mvc.dao.DaoInterface;
import main.java.xmlentitylists.XmlGenericEntityList;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class Model {
  private View view;
  private DaoFactory daoFactory;
  private DaoInterface recentDao;
  private GenericEntity recentEntity;
  private Field[] recentEntityFields;
  
  {
    view = new View();
    daoFactory = new DaoFactory();
  }
  
  Model() {
    
  }
  
  GenericEntity getEntityById(String objId) {
    Class genericEntityClass;
    try {
      genericEntityClass = Class.forName("main.java.entities.GenericEntity");
    } catch (ClassNotFoundException ex) {
      System.out.printf("Unknown class %s\n", "GenericEntity");
      return null;
    }
    recentDao = daoFactory.getDao(genericEntityClass);
    recentEntity = recentDao.read(objId);
    if (recentEntity == null) {
      System.out.println("No entity with such id.");
    }
    return recentEntity;
  }
  
  // TODO: 04.11.2019 rename this function to smth more appropriate 
  int getEntityFieldCount(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      return 0;
    }
    recentEntityFields = obj.getClass().getDeclaredFields();
    view.displayFields(recentEntityFields);
    return recentEntityFields.length;
  }
  
  Field getRecentEntityField(int fieldNumber) {
    return recentEntityFields[fieldNumber];
  }
  
  private Object callGetter(String fieldName,
                            Object targetObj,
                            String introspectionExceptionAdditionalMessage,
                            String illegalAccessExceptionAdditionalMessage,
                            String invocationTargetExceptionAdditionalMessage) {
    // TODO: 04.11.2019 comment output in catches(mby in callSetter too)
    try {
      return new PropertyDescriptor(fieldName, targetObj.getClass())
          .getReadMethod().invoke(targetObj);
    } catch (IntrospectionException ex) {
      System.out.println(introspectionExceptionAdditionalMessage
                         + ex.getMessage());
    } catch (IllegalAccessException ex) {
      System.out.println(illegalAccessExceptionAdditionalMessage
                         + ex.getMessage());
    } catch (InvocationTargetException ex) {
      System.out.println(invocationTargetExceptionAdditionalMessage
                         + ex.getMessage());
    }
    return null;
  }
  
  private void callSetter(String fieldName,
                          Object targetObj,
                          Object newValue,
                          String introspectionExceptionAdditionalMessage,
                          String illegalAccessExceptionAdditionalMessage,
                          String invocationTargetExceptionAdditionalMessage) {
    try {
      new PropertyDescriptor(fieldName, targetObj.getClass())
          .getWriteMethod().invoke(targetObj, newValue);
    } catch (IntrospectionException ex) {
      System.out.println(introspectionExceptionAdditionalMessage
                         + ex.getMessage());
    } catch (IllegalAccessException ex) {
      System.out.println(illegalAccessExceptionAdditionalMessage
                         + ex.getMessage());
    } catch (InvocationTargetException ex) {
      System.out.println(invocationTargetExceptionAdditionalMessage
                         + ex.getMessage());
    }
  }
  
  void create(Class Tmp, String objId) {
    DaoInterface daoTmp = daoFactory.getDao(Tmp);
    GenericEntity obj = daoTmp.create(objId);
    // TODO: 27.10.2019 post create message in view
    if (obj == null) {
      System.out.println("Create failed");
    } else {
      System.out.println("Create succeeded");
    }
  }
  
  void read(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      System.out.println("Read failed.");
    } else {
      view.displayObject(obj);
    }
    //System.out.println("Read succeeded");
  }
  
  void updateSimpleField(String objId, Field field, Object fieldValue) {
    GenericEntity obj = getEntityById(objId);
    if (fieldValue.getClass() != field.getType()) {
      System.out.println("Entered value type differs from field type.\n"
                         + "Update failed.");
      return;
    }
    if (obj == null || fieldValue == null) {
      System.out.println("Update failed.");
      return;
    }
    callSetter(field.getName(),
               obj,
               fieldValue,
               "IntrospectionException in Model::updateSimpleField, "
               + "PropertyDescriptor()\n",
               "Illegal access in Model::updateSimpleField, "
               + "Method.invoke()\n",
               "Illegal access in Model::updateSimpleField, "
               + "Method.invoke()\n");
    recentDao.update(recentEntity);
    System.out.println("Update succeeded");
  }
  
  void updateEntityField(String objId, Field field, String internalObjId) {
    GenericEntity obj = getEntityById(objId);
    GenericEntity internalObj = getEntityById(internalObjId);
    if (internalObj.getClass() != field.getType()) {
      System.out.println("Entered object type differs from field type.\n"
                         + "Update failed.");
      return;
    }
    if (obj == null || internalObj == null) {
      System.out.println("Update failed.");
      return;
    }
    callSetter(field.getName(),
               obj,
               internalObj,
               "IntrospectionException in Model::updateObjectField, "
               + "PropertyDescriptor()\n",
               "Illegal access in Model::updateObjectField, "
               + "Method.invoke()\n",
               "Illegal access in Model::updateObjectField, "
               + "Method.invoke()\n");
    recentDao.update(recentEntity);
    System.out.println("Update succeeded");
  }
  
  void updateEntityListField(String objId,
                             Field field,
                             EntityListOperationEnum op,
                             String internalObjId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      System.out.println("Update failed.");
      return;
    }
    XmlGenericEntityList entityList = (XmlGenericEntityList) callGetter(
        field.getName(),
        obj,
        "IntrospectionException in Model::updateEntityListField, "
        + "PropertyDescriptor()\n",
        "Illegal access in Model::updateEntityListField, "
        + "Method.invoke()\n",
        "InvocationTargetException in Model::updateEntityListField, "
        + "Method.invoke()\n");
    if (entityList == null) {
      System.out.println("Entity list is null.\n"
                         + "Update failed.");
      return;
    }
    
    GenericEntity internalObj = null;
    switch (op) {
      case ADD:
        internalObj = getEntityById(internalObjId);
        if (internalObj == null) {
          System.out.println("Update failed.");
          return;
        }
        if (internalObj.getClass() != entityList.acquireListType()) {
          System.out.println("Entered object type differs from list type.\n"
                             + "Update failed.");
          return;
        }
        entityList.add(internalObj);
        break;
      case REMOVE:
        internalObj = getEntityById(internalObjId);
        if (internalObj == null) {
          System.out.println("Update failed.");
          return;
        }
        if (internalObj.getClass() != entityList.acquireListType()) {
          System.out.println("Entered object type differs from list type.\n"
                             + "Update failed.");
          return;
        }
        // TODO: 04.11.2019 if there are no such object, print it
        entityList.remove(internalObj);
        break;
      case CLEAR:
        entityList.clear();
        break;
    }
    
    // i know that entityList is that not needed to be set, i just want to 
    // make code look properly
    callSetter(field.getName(),
               obj,
               entityList,
               "IntrospectionException in Model::updateEntityListField, "
               + "PropertyDescriptor()\n",
               "Illegal access in Model::updateEntityListField, "
               + "Method.invoke()\n",
               "Illegal access in Model::updateEntityListField, "
               + "Method.invoke()\n");
    recentDao.update(recentEntity);
    System.out.println("Update succeeded");
  }
  
  void delete(String objId) {
  }
}
