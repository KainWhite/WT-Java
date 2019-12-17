package servlets;

import entities.GenericEntity;
import entities.complex.Elective;

import javax.servlet.annotation.WebServlet;

@WebServlet("/electives")
public class XmlElectivesShowServlet extends XmlShowServlet {
  @Override
  public Class<? extends GenericEntity> getInternalClass() {
    return Elective.class;
  }
}
