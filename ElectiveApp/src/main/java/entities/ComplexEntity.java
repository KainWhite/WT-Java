package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exceptions.NotFoundException;
import xmlentitylists.XmlGenericEntityList;

import java.io.Serializable;
import java.util.List;

/**
 * Parent class for complex entities
 *
 * @param <K> id type
 * @param <T> entity class
 */
public abstract class ComplexEntity<K extends Serializable,
    T extends GenericEntity>
    extends GenericEntity<K, T> {
  /**
   * Sets new XmlEntityList to internal list with name
   * <i>internalEntityListName</i>
   *
   * @param internalEntityListName name of list to reset
   */
  public abstract void resetInternalEntityList(
      String internalEntityListName) throws NotFoundException;
  
  @JsonIgnore
  public abstract XmlGenericEntityList getInternalXmlList(
      String internalEntityListName) throws NotFoundException;
  
  @JsonIgnore
  public abstract List<List<GenericEntity>> getInternalEntityLists();
  
  @JsonIgnore
  public abstract List<GenericEntity> getInternalEntities();
  
  @JsonIgnore
  public abstract void setInternalEntities(List<GenericEntity> internalEntities);
}
