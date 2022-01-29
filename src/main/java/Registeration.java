

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Registeration")
public class Registeration extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
       String name=request.getParameter("user");
       String password=request.getParameter("passw");
       String emailid=request.getParameter("email");
       String mobileNo=request.getParameter("phone");
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iisn","root","12345678");
            PreparedStatement preparedStatement=connection.prepareStatement("insert into registeration(name,password,emailid,phoneNo)values(?,?,?,?)");
               preparedStatement.setString(1,name);
               preparedStatement.setString(2,password);
               preparedStatement.setInt(4,Integer.parseInt(mobileNo));
               preparedStatement.setString(3,emailid);
               int i=preparedStatement.executeUpdate();
               if(i>0)
               {
                   pw.println("<h2>Inserted Successfully");
               }
               else
               {
                   pw.println("<h2>Error in Insertion");
               }

           }
       catch(SQLException e1)
       {
           System.out.print("sql Exception");
       }
       catch(Exception e1)
       {
           System.out.print(" Exception");
       }

    pw.close();

    }
}
