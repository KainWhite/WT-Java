# Elective App
## Project structure
### ElectiveApp:
```
src
  main
  | java - source java files
  | | entities - classes for entities
  | | mvc - app logic
  | | | controller - controller class with some useful stuff for it
  | | | dao - data access object implementation
  | | | | daoentities - GenericDao and dao classes for each entity, that implement all data-oriented logic(like db transactions, internal db updates, etc.)
  | | | | DaoFactory.java - factory for DaoEntities
  | | | | DaoFactoryInteface.java - interface for DaoFactory with DaoCreatorInterface(interface with DaoInterface create method) and DaoInterface getDao method
  | | | model - model class, implements app internal logic, calls DAO
  | | | view - view class, implements some printing operations
  | | | EntityListOperationEnum.java - enum for different operations with entityListFields
  | | xmlentitylists - classes to wrap entity lists for serialization
  | | Main.java - app entry point, gets all input and redirect it to Controller
  | resources - database xmls
```
## Usage
Just type any command:\
`create\c EntityClassName entityId` - create object of 'EntityClassName' with `id` = 'entityId'\
`read\r entityId` - read object with `id` = 'entityId'\
`update\u entityId` and then follow instructions - update object with `id` = 'entityId'\
`delete\d entityId` - delete object with `id` = 'entityId'\
`exit\quit\q` - close app