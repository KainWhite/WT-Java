package mvc.dao;

import mvc.dao.daoentities.DaoInterface;

/**
 * Interface for DaoFactory
 */
public interface DaoFactoryInterface {
  /**
   * Gets DaoObject for Tmp class
   *
   * @param Tmp Class for which Dao object is required
   * @return null if there are no Dao class for Tmp, Dao object otherwise
   */
  DaoInterface getDao(Class Tmp);
  
  /**
   * Interface for creating DaoObjects
   */
  interface DaoCreatorInterface {
    /**
     * @return Dao object
     */
    DaoInterface create();
  }
}
