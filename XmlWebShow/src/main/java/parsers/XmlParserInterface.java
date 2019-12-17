package parsers;

import entities.GenericEntity;

import java.util.List;

public interface XmlParserInterface<G extends GenericEntity> {
  List<G> getEntityListFromFile(String path);
}
