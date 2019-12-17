package servlets;

import entities.GenericEntity;
import entities.simple.Subject;

import javax.servlet.annotation.WebServlet;

@WebServlet("/subjects")
public class XmlSubjectsShowServlet extends XmlShowServlet {
  @Override
  public Class<? extends GenericEntity> getInternalClass() {
    return Subject.class;
  }
}
