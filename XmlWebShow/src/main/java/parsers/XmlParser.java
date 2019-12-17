package parsers;

import entities.GenericEntity;

import java.util.List;

public interface XmlParser<G extends GenericEntity> {
  List<G> getEntityListFromFile(String path);
}
