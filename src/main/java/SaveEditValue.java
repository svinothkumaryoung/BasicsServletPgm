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

@WebServlet(urlPatterns = "/SaveEditValue")
public class SaveEditValue extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        String sno1=request.getParameter("sno");
        String name=request.getParameter("user");
        String password=request.getParameter("passw");
        String emailid=request.getParameter("email");
        String mobileNo=request.getParameter("phone");
        //pw.println("Sno "+sno1+"\n Name "+name+"\n password "+password+"\n  Emailid "+emailid+" \n Mobile Number "+mobileNo);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/iisn","root","12345678");
            PreparedStatement preparedStatement=connection.prepareStatement("update registeration set name=?,password=?,emailid=?,phoneNo=? where sno=? ");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,emailid);
            preparedStatement.setInt(4,Integer.parseInt(mobileNo));
            preparedStatement.setInt(5,Integer.parseInt(sno1));

            int i=preparedStatement.executeUpdate();
            if(i>0)
            {
                pw.println("<h2>Updated  Successfully");
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
