package servlets;

import entities.GenericEntity;
import entities.simple.Student;

import javax.servlet.annotation.WebServlet;

@WebServlet("/students")
public class XmlStudentsShowServlet extends XmlShowServlet {
  @Override
  public Class<? extends GenericEntity> getInternalClass() {
    return Student.class;
  }
}
