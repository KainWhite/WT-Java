#Elective App
##Project structure:
```
src
  main
  | java - source java files
  | | entities - classes for entities
  | | entityinterfaces - interfaces for entities
  | | mvc - app logic
  | | | dao - data access object implementation
  | | | | daoentities - classes for each entity, that implement all data-oriented logic(like db transactions, internal db updates, etc.)
  | | | | DaoFactory.java - factory for DaoEntities
  | | | | DaoFactoryInteface - interface for DaoFactory with DaoCreatorInterface(interface with DaoInterface create method) and DaoInterface getDao method
  | | | | DaoInterface - interface for DaoEntities with crud methods
  | | | Controller.java - controller class, processes input and calls Model
  | | | EntityListOperationEnum.java - enum for different operations with entityListFields
  | | | IntegerInputConditionInterface - interface with method, that requests integer input and checks it with given condition
  | | | Model.java - model class, implements app internal logic, calls DAO and View
  | | | View.java - view class, implements some printing operations
  | | xmlentitylists - classes to wrap entity lists for serialization
  | | Main.java - app entry point, gets all input and redirect it to Controller
  | resources - database xmls
```
##Usage
Just type any command:\
`create\c EntityClassName entityId` - create object of 'EntityClassName' with `id` = 'entityId'\
`read\r entityId` - read object with `id` = 'entityId'\
`update\u entityId` and then follow instructions - update object with `id` = 'entityId'\
`delete\d entityId` - delete object with `id` = 'entityId'\
`exit\quit\q` - close app