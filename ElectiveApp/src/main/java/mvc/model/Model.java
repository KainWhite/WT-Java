package main.java.mvc.model;

import main.java.entities.ComplexEntity;
import main.java.entities.GenericEntity;
import main.java.exceptions.NotFoundException;
import main.java.mvc.EntityListOperationEnum;
import main.java.mvc.dao.DaoFactory;
import main.java.mvc.dao.daoentities.DaoInterface;
import main.java.mvc.view.View;
import main.java.xmlentitylists.XmlGenericEntityList;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Program logic class
 */
public class Model {
  private View view;
  private DaoFactory daoFactory;
  /** latest received <b>Dao</b> */
  private DaoInterface recentDao;
  /** latest received <b>Entity</b> */
  private GenericEntity recentEntity;
  /** latest received <b>Entity</b> field array */
  private Field[] recentEntityFields;
  
  {
    view = new View();
    daoFactory = new DaoFactory();
  }
  
  public Model() {
    
  }
  
  // TODO: 13.11.2019 remove reflection 
  
  /**
   * Gets object from <b>Dao</b>, sets <b>recentDao</b> and <b>recentEntity</b>
   *
   * @param objId <b>id</b> of object to get
   * @return received object or null, if there are no object with such <b>id</b>
   */
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
  
  /**
   * Gets number of fields of object with <b>id</b> = <i>objId</i>, sets
   * <b>recentEntityFields</b>
   *
   * @param objId <b>id</b> of object to get fields from
   * @return number of fields of object with <b>id</b> = <i>objId</i>
   */
  public int getEntityFieldCount(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      return 0;
    }
    recentEntityFields = obj.getClass().getDeclaredFields();
    view.displayFields(recentEntityFields);
    return recentEntityFields.length;
  }
  
  /**
   * Gets field from <b>recentEntityFields</b> with specific <i>fieldNumber</i>
   *
   * @param fieldNumber number of field to get
   * @return field from <b>recentEntityFields</b> with specific
   * <i>fieldNumber</i>
   */
  public Field getRecentEntityField(int fieldNumber) {
    return recentEntityFields[fieldNumber];
  }
  
  /**
   * Calls setter of object <i>targetObj</i> for field <i>fieldName</i> with
   * value <i>newValue</i>
   *
   * @param fieldName name of field to call getter for
   * @param targetObj object to call getter from
   * @param newValue  value to call setter with
   */
  private void callSetter(String fieldName, Object targetObj, Object newValue) {
    try {
      new PropertyDescriptor(fieldName, targetObj.getClass()).getWriteMethod()
                                                             .invoke(targetObj,
                                                                     newValue);
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
  
  /**
   * Creates object of class <i>Tmp</i> with <b>id</b> = <i>objId</i>
   *
   * @param Tmp   class of object to create
   * @param objId <b>id</b> of object to create
   */
  public void create(Class Tmp, String objId) {
    DaoInterface daoTmp = daoFactory.getDao(Tmp);
    GenericEntity obj = daoTmp.create(objId);
    if (obj == null) {
      System.out.println("Create failed");
    } else {
      System.out.println("Create succeeded");
    }
  }
  
  /**
   * Prints object with <b>id</b> = <i>objId</i>
   *
   * @param objId <b>id</b> of object to print
   */
  public void read(String objId) {
    GenericEntity obj = getEntityById(objId);
    if (obj == null) {
      System.out.println("Read failed.");
    } else {
      view.displayObject(obj);
    }
    //System.out.println("Read succeeded");
  }
  
  /**
   * Updates primitive type field <i>field</i> of object with <b>id</b> =
   * <i>objId</i> with <i>fieldValue</i>
   *
   * @param objId      <b>id</b> of object to update
   * @param field      field to update
   * @param fieldValue new value for field
   */
  public void updateSimpleField(String objId, Field field, Object fieldValue) {
    GenericEntity obj = getEntityById(objId);
    if (field.getClass().isAssignableFrom(fieldValue.getClass())) {
      System.out.println(
          "Entered value with type (" + fieldValue.getClass() + ") "
          + "can't be assigned to field with type (" + field.getType() + ").\n"
          + "Update failed.");
      return;
    }
    if (obj == null || fieldValue == null) {
      System.out.println("Update failed.");
      return;
    }
    callSetter(field.getName(), obj, fieldValue);
    recentDao.update(recentEntity);
    System.out.println("Update succeeded");
  }
  
  /**
   * Updates Entity field <i>field</i> of object with <b>id</b> =
   * <i>objId</i> with object, which <b>id</b> = <i>internalObjId</i>
   *
   * @param objId         <b>id</b> of object to update
   * @param field         field to update
   * @param internalObjId object <b>id</b> to update field with
   */
  public void updateEntityField(String objId,
                                Field field,
                                String internalObjId) {
    GenericEntity obj = getEntityById(objId);
    GenericEntity internalObj = getEntityById(internalObjId);
    if (internalObj.getClass() != field.getType()) {
      System.out.println(
          "Entered object type differs from field type.\n"
          + "Update failed.");
      return;
    }
    if (obj == null || internalObj == null) {
      System.out.println("Update failed.");
      return;
    }
    callSetter(field.getName(), obj, internalObj);
    recentDao.update(recentEntity);
    System.out.println("Update succeeded");
  }
  
  /**
   * Updates <b>XmlGenericEntityList</b> field <i>field</i> of object with
   * <b>id</b> = <i>objId</i> according to operation <i>op</i>
   *
   * @param objId         <b>id</b> of object to update
   * @param field         field to update
   * @param op            <b>EntityListOperationEnum</b> value describing what
   *                      operation to perform
   * @param internalObjId object <b>id</b> to update
   *                      <b>XmlGenericEntityList</b> with
   */
  public void updateEntityListField(String objId,
                                    Field field,
                                    EntityListOperationEnum op,
                                    String internalObjId) {
    ComplexEntity obj = (ComplexEntity) getEntityById(objId);
    if (obj == null) {
      System.out.println("Update failed.");
      return;
    }
    XmlGenericEntityList entityList = null;
    try {
      entityList = obj.getInternalXmlList(field.getName());
    } catch (NotFoundException e) {
      System.out.println(e.getMessage() + "\n" 
                         + "Update failed.");
      return;
    }
    if (entityList == null) {
      try {
        obj.resetInternalEntityList(field.getName());
        entityList = obj.getInternalXmlList(field.getName());
      } catch (NotFoundException e) {
        System.out.println(e.getMessage() + "\n"
                           + "Update failed.");
        return;
      }
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
  
  /**
   * Deletes object with <b>id</b> = <i>objId</i>
   *
   * @param objId <b>id</b> of object to delete
   */
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
