import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/EditRecords")
public class EditRecords extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        int sno1 = Integer.parseInt(request.getParameter("sno"));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iisn","root","12345678");
            PreparedStatement ps1=connection.prepareStatement("select * from registeration where sno=? ");
            ps1.setInt(1,sno1);
            ResultSet resultSet=ps1.executeQuery();
                //pw.println("<table><tr><th>sno</th><th>Name</th><th>Email Id</th><th>Mobile No</th><th colspan='2'>Action</th></tr>")
                //pw.println("<tr><td>"+resultSet.getInt(1)+"</td><td>"+resultSet.getString(2)+"</td><td>"+resultSet.getString(4)+"</td><td>"+resultSet.getString(5)+"</td>");
                //pw.println("<td><a href='EditRecords?sno="+resultSet.getInt(1)+"'>Edit</a></td>");
                //pw.println("<td><a href='DeleteRecords?sno="+resultSet.getInt(1)+"'>Delete</a></td>");
                //pw.println("</tr>");
            while(resultSet.next()) {


                pw.println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"design.css\"/>");
                pw.println("<body><form action='SaveEditValue' method='post'>");
                pw.println("<input type=\"hidden\" name=\"sno\" value='" + resultSet.getString(1) + "' >");
                pw.println("<input type=\"text\" name=\"user\" value='" + resultSet.getString(2) + "' placeholder=\"Enter the Username\" id=\"user\">");
                pw.println("<input type=\"text\" name=\"passw\" value='" + resultSet.getString(3) + "' placeholder=\"Enter the Password\" id=\"passw\">");
                pw.println("<input type=\"text\" name=\"email\" value='" + resultSet.getString(4) + "' placeholder=\"Enter the Email\" id=\"email\">");
                pw.println("<input type=\"text\" name=\"phone\" value='" + resultSet.getString(5) + "' placeholder=\"Enter the Mobileno\" id=\"phone\">");
                pw.println("<input type=\"submit\" value=\"Update\">");
                pw.println("</form></body></html>");
            }



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

