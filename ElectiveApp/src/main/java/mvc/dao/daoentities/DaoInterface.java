package mvc.dao.daoentities;

import entities.GenericEntity;

import java.io.Serializable;

/**
 * Interface for Dao classes
 *
 * @param <K> id type
 * @param <T> entity class
 */
public interface DaoInterface<K extends Serializable, T extends GenericEntity> {
  /**
   * Creates object of class T with id = key
   *
   * @param key object id
   * @return created object
   */
  T create(K key);
  
  /**
   * Gets object of class T with id = key
   *
   * @param key object id
   * @return required object
   */
  T read(K key);
  
  /**
   * Replaces object with id = obj.getId() with obj
   *
   * @param obj new object
   */
  void update(T obj);
  
  /**
   * Deletes object with id = obj.getId()
   *
   * @param obj object to delete
   */
  void delete(T obj);
}
