

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Records")
public class Records extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        //
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iisn","root","12345678");
            PreparedStatement ps1=connection.prepareStatement("select * from registeration ");
            ResultSet resultSet=ps1.executeQuery();
            pw.println("<table><tr><th>sno</th><th>Name</th><th>Email Id</th><th>Mobile No</th><th colspan='2'>Action</th></tr>");
            while(resultSet.next())
            {
                pw.println("<tr><td>"+resultSet.getInt(1)+"</td><td>"+resultSet.getString(2)+"</td><td>"+resultSet.getString(4)+"</td><td>"+resultSet.getString(5)+"</td>");
                pw.println("<td><a href='EditRecords?sno="+resultSet.getInt(1)+"'>Edit</a></td>");
                pw.println("<td><a href='DeleteRecords?sno="+resultSet.getInt(1)+"'>Delete</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
        }
        catch(SQLException sqlException)
        {
            System.out.println("SQL Error");
        }
        catch(ClassNotFoundException classNotFoundException)
        {
            System.out.println("Class Not Found Exception");
        }

        pw.close();
    }
}
