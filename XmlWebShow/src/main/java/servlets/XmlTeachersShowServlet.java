package servlets;

import entities.GenericEntity;
import entities.complex.Teacher;

import javax.servlet.annotation.WebServlet;

@WebServlet("/teachers")
public class XmlTeachersShowServlet extends XmlShowServlet {
  @Override
  public Class<? extends GenericEntity> getInternalClass() {
    return Teacher.class;
  }
}
