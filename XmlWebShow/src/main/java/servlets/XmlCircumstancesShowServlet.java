package servlets;

import entities.simple.Circumstance;
import parsers.XmlParser;
import parsers.domparsers.XmlCircumstancesDomParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/circumstances")
public class XmlCircumstancesShowServlet extends HttpServlet {
  private final String dbCircumstancePath =
      "C:/Users/KainWhite/Documents/University/WT/ElectiveApp/src"
      + "/main/resources/dbCircumstances/dbCircumstances.xml";
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    XmlParser parser = new XmlCircumstancesDomParser();
    List<Circumstance> circumstances = parser.getEntityListFromFile(
        dbCircumstancePath);
    request.setAttribute("circumstances", circumstances);
    request.getRequestDispatcher("/WEB-INF/pages/circumstances.jsp").forward(
        request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
