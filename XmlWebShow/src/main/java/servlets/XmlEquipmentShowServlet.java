package servlets;

import entities.GenericEntity;
import entities.simple.Equipment;

import javax.servlet.annotation.WebServlet;

@WebServlet("/equipment")
public class XmlEquipmentShowServlet extends XmlShowServlet {
  @Override
  public Class<? extends GenericEntity> getInternalClass() {
    return Equipment.class;
  }
}
