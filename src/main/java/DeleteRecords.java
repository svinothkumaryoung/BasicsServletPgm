import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/DeleteRecords")
public class DeleteRecords extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        int sno1=Integer.parseInt(request.getParameter("sno"));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iisn","root","12345678");
            PreparedStatement ps1=connection.prepareStatement("delete from registeration where sno=? ");
            ps1.setInt(1,sno1);
            int n=ps1.executeUpdate();
            if(n>0){
                response.sendRedirect("Records");
            }
            else
            {
                pw.println("Error in Deleting Records");
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
