package main.java.xmlentitylists;

import main.java.entities.GenericEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class XmlGenericEntityList<T extends GenericEntity> {
  private List<T> entities;
  
  {
    entities = new ArrayList<>();
  }
  
  public XmlGenericEntityList() {
  }
  
  public XmlGenericEntityList(List<T> entities) {
    this.entities = entities;
  }
  
  public List<T> getEntities() {
    return entities;
  }
  
  public void setEntities(List<T> entities) {
    this.entities = entities;
  }
  
  public boolean add(T entity) {
    this.entities.add(entity);
    return true;
  }
  
  public boolean addAll(Collection<? extends T> entities) {
    this.entities.addAll(entities);
    return true;
  }
  
  public T get(int index) {
    return entities.get(index);
  }
  
  public int size() {
    return entities.size();
  }
  
  public void forEach(Consumer<? super T> action) {
    entities.forEach(action);
  }
}
