package main.java.xmlentitylists;

import main.java.entities.ComplexEntity;

import java.util.List;

/**
 * Parent class for XmlEntityList with complex entities
 *
 * @param <T> entity class
 */
public abstract class XmlComplexEntityList<T extends ComplexEntity>
    extends XmlGenericEntityList<T> {
  @Override
  public List<T> getEntities() {
    return super.getEntities();
  }
}
