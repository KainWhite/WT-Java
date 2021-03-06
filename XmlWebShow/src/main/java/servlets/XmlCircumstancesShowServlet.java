package servlets;

import entities.GenericEntity;
import entities.simple.Circumstance;

import javax.servlet.annotation.WebServlet;

@WebServlet("/circumstances")
public class XmlCircumstancesShowServlet extends XmlShowServlet {
  @Override
  public Class<? extends GenericEntity> getInternalClass() {
    return Circumstance.class;
  }
}
