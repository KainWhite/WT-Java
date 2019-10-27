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
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDao<
                                  K extends Serializable,
                                  T extends GenericEntity
                                >
    implements DaoInterface<K, T> {
    
  // create, that implemented in inheritors, calls generalCreate(new T())
  T generalCreate(T obj) {
    Map<Serializable, GenericEntity> identifiedObjects = readMapFromXML();
    if(identifiedObjects == null) {
      identifiedObjects = new HashMap<>();
    }
    if(identifiedObjects.get(obj.getId()) != null) {
      return null;
    }
    identifiedObjects.put(obj.getId(), obj);
    writeMapToXML(identifiedObjects);
    return obj;
  }

  // read implemented in inheritors

  @Override
  public void update(T obj) {

  }

  @Override
  public void delete(T obj) {

  }
  
  Map<Serializable, GenericEntity> readMapFromXML() {
    XMLDecoder decoder = null;
    try {
      decoder = new XMLDecoder(new BufferedInputStream(
          new FileInputStream("src/main/resources/db.xml")
      ));
    } catch (FileNotFoundException ex) {
      System.out.println("File src/main/resources/db.xml not found");
      return null;
    }
    Map<Serializable, GenericEntity> identifiedObjects = null;
    try {
      identifiedObjects = (HashMap<Serializable, GenericEntity>)decoder.readObject();
    } catch (ArrayIndexOutOfBoundsException ex) {
      // e.g. file was empty
    }
    decoder.close();
    return identifiedObjects;
  }
  
  void writeMapToXML(Map<Serializable, GenericEntity> identifiedObjects) {
    XMLEncoder encoder = null;
    try {
      encoder = new XMLEncoder(new BufferedOutputStream(
          new FileOutputStream("src/main/resources/db.xml")
      ));
    } catch (FileNotFoundException ex) {
      System.out.println("File src/main/resources/db.xml not found");
      return;
    }
    encoder.writeObject(identifiedObjects);
    encoder.close();
  }
}
