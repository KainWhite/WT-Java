package main.java.mvc.model;

import main.java.entities.GenericEntity;
import main.java.mvc.EntityListOperationEnum;
import main.java.mvc.dao.DaoFactory;
import main.java.mvc.dao.daoentities.DaoInterface;
import main.java.mvc.view.View;
import main.java.xmlentitylists.XmlGenericEntityList;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Model {
  private View view;
  private DaoFactory daoFactory;
  private DaoInterface recentDao;
  private GenericEntity recentEntity;
  private Field[] recentEntityFields;
  
  {
    view = new View();
    daoFactory = new DaoFactory();
  }
  
  public Model() {
    
  }
  
  // TODO: 13.11.2019 remove reflection 
  private GenericEntity getEntityById(String objId) {
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
  
  public int getEntityFieldCount(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      return 0;
    }
    recentEntityFields = obj.getClass().getDeclaredFields();
    view.displayFields(recentEntityFields);
    return recentEntityFields.length;
  }
  
  public Field getRecentEntityField(int fieldNumber) {
    return recentEntityFields[fieldNumber];
  }
  
  private Object callGetter(String fieldName,
                            Object targetObj,
                            String introspectionExceptionAdditionalMessage,
                            String illegalAccessExceptionAdditionalMessage,
                            String invocationTargetExceptionAdditionalMessage) {
    try {
      return new PropertyDescriptor(fieldName, targetObj.getClass())
          .getReadMethod().invoke(targetObj);
    } catch (IntrospectionException ex) {
//      System.out.println(introspectionExceptionAdditionalMessage
//                         + ex.getMessage());
    } catch (IllegalAccessException ex) {
//      System.out.println(illegalAccessExceptionAdditionalMessage
//                         + ex.getMessage());
    } catch (InvocationTargetException ex) {
//      System.out.println(invocationTargetExceptionAdditionalMessage
//                         + ex.getMessage());
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
//      System.out.println(introspectionExceptionAdditionalMessage
//                         + ex.getMessage());
    } catch (IllegalAccessException ex) {
//      System.out.println(illegalAccessExceptionAdditionalMessage
//                         + ex.getMessage());
    } catch (InvocationTargetException ex) {
//      System.out.println(invocationTargetExceptionAdditionalMessage
//                         + ex.getMessage());
    }
  }
  
  public void create(Class Tmp, String objId) {
    DaoInterface daoTmp = daoFactory.getDao(Tmp);
    GenericEntity obj = daoTmp.create(objId);
    if (obj == null) {
      System.out.println("Create failed");
    } else {
      System.out.println("Create succeeded");
    }
  }
  
  public void read(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      System.out.println("Read failed.");
    } else {
      view.displayObject(obj);
    }
    //System.out.println("Read succeeded");
  }
  
  public void updateSimpleField(String objId, Field field, Object fieldValue) {
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
  
  public void updateEntityField(String objId,
                                Field field,
                                String internalObjId) {
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
  
  public void updateEntityListField(String objId,
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
        if (internalObj.getClass() != entityList.getListType()) {
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
        if (internalObj.getClass() != entityList.getListType()) {
          System.out.println("Entered object type differs from list type.\n"
                             + "Update failed.");
          return;
        }
        if (!entityList.contains(internalObj)) {
          System.out.println("No such entity in " + field.getName() + ".\n"
                             + "Update failed.");
          return;
        }
        entityList.remove(internalObj);
        break;
      case CLEAR:
        entityList.clear();
        break;
    }
    recentDao.update(recentEntity);
    System.out.println("Update succeeded");
  }
  
  public void delete(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      System.out.println("Delete failed.");
    } else {
      recentDao.delete(obj);
      System.out.println("Delete succeeded.");
    }
  }
}
