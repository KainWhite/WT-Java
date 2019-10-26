package main.java.mvc.dao.daoentities;

import main.java.entities.GenericEntity;
import main.java.mvc.dao.DaoInterface;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.TreeSet;

public abstract class GenericDao<
                                  K extends Serializable,
                                  T extends GenericEntity
                                >
    implements DaoInterface<K, T> {
    
  // create, that implemented in inheritors, calls generalCreate(new T())
  T generalCreate(T obj) {
    XMLDecoder decoder = null;
    try {
      decoder = new XMLDecoder(new BufferedInputStream(
          new FileInputStream("src/main/resources/db.xml")
      ));
    } catch (FileNotFoundException ex) {
      System.out.println("File src/main/resources/db.xml not found");
      return null;
    }
    TreeSet<GenericEntity> identifiedObjects = null;
    try {
      identifiedObjects = (TreeSet<GenericEntity>)decoder.readObject();
    } catch (ArrayIndexOutOfBoundsException ex) {
      // e.g. file was empty
    }
    decoder.close();
    if(identifiedObjects == null) {
      identifiedObjects = new TreeSet<>();
    }
    if(identifiedObjects.contains(obj)) {
      return null;
    }
    identifiedObjects.add(obj);
    XMLEncoder encoder = null;
    try {
      encoder = new XMLEncoder(new BufferedOutputStream(
          new FileOutputStream("src/main/resources/db.xml")
      ));
    } catch (FileNotFoundException ex) {
      System.out.println("File src/main/resources/db.xml not found");
      return null;
    }
    encoder.writeObject(identifiedObjects);
    encoder.close();
    return obj;
  }

  @Override
  public T read(K key) {
    return null;
  }

  @Override
  public void update(T obj) {

  }

  @Override
  public void delete(T obj) {

  }
}
