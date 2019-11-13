package main.java.xmlentitylists;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  
  public XmlGenericEntityList() {}
  
  public XmlGenericEntityList(List<T> entities) {
    this.entities = entities;
  }
  
  public List<T> getEntities() {
    return entities;
  }
  
  public void setEntities(List<T> entities) {
    if (entities == null) {
      this.entities = new ArrayList<>();
    } else {
      this.entities = entities;
    }
  }
  
  @JsonIgnore
  public Class getListType() {
    return GenericEntity.class;
  }
  
  public boolean add(T entity) {
    return entities.add(entity);
  }
  
  public boolean addAll(Collection<? extends T> entities) {
    return this.entities.addAll(entities);
  }
  
  public void clear() {
    entities.clear();
  }
  
  public boolean contains(Object o) {
    return entities.contains(o);
  }
  
  public T get(int index) {
    return entities.get(index);
  }
  
  public boolean remove(Object o) {
    return entities.remove(o);
  }
  
  public int size() {
    return entities.size();
  }
  
  public void forEach(Consumer<? super T> action) {
    entities.forEach(action);
  }
}
