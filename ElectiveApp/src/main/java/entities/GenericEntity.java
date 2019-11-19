package entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * Parent class for entities
 *
 * @param <K> id type
 * @param <T> entity class
 */
public abstract class GenericEntity<K extends Serializable,
    T extends GenericEntity>
    implements Identifiable<K>, Comparable<T> {
  @JacksonXmlProperty(isAttribute = true)
  private K id;
  
  {
    id = null;
  }
  
  /**
   * Creates object with the same type and id as target object's id and default 
   * fields
   *
   * @return created object
   */
  public abstract T createNewInstance();
  
  @Override
  public int compareTo(T obj) {
    return (getClass().getSimpleName() + id.toString()).compareTo(
        obj.getClass().getSimpleName() + obj.getId().toString());
  }
  
  /**
   * @param spaceCount number of spaces
   * @return String with spaceCount spaces
   */
  protected String getSpaceString(int spaceCount) {
    StringBuilder spaces = new StringBuilder();
    for (int i = 0; i < spaceCount; i++) {
      spaces.append(" ");
    }
    return spaces.toString();
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    if (id != null) {
      str.append(" id: ").append(id).append("\n");
    }
    return str.toString();
  }
  
  public String toString(int identSpacesCount) {
    String spaces = getSpaceString(identSpacesCount);
    StringBuilder str = new StringBuilder();
    if (id != null) {
      str.append(spaces).append(" id: ").append(id).append("\n");
    }
    return str.toString();
  }
  
  @Override
  public K getId() {
    return id;
  }
  
  @Override
  public void setId(K id) {
    this.id = id;
  }
}
