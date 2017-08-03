package shahaf.CouponProjectWebsite.Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.coyote.Response;

import Facade.ClientType;
import shahaf.CouponProjectWebsite.Service.ClientService;
import shahaf.CouponProjectWebsite.WebBeans.LoginInfo;
import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class Login
 */
@Path("Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loginAs") == null)
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Loginsrc/Login.html"));
		}
		else if(((ClientType)request.getSession().getAttribute("loginAs")).equals(ClientType.Admin))
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Admin/admin.html"));
		}
		else if(((ClientType)request.getSession().getAttribute("loginAs")).equals(ClientType.Company))
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Company/company.html"));
		}
		else if(((ClientType)request.getSession().getAttribute("loginAs")).equals(ClientType.Customer))
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Customer/customer.html"));
		}
		else
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Loginsrc/Login.html"));
		}
		
		
		/*
		PrintWriter w =response.getWriter();
		w.append("<html>");
			w.append("<head>");
				w.append("<title>");
					w.append("Login");
				w.append("</title>");
			w.append("</head>");
			w.append("<body>");
				w.append("");
			w.append("</body>");
		w.append("</html>");
		*/
	}
     
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//System.out.println(request.getAttribute("userName").toString()+request.getAttribute("userName").toString()+request.getAttribute("userName").toString());
		if(request.getSession().getAttribute("loginAs") == null)
		{
			RequestDispatcher d = request.getRequestDispatcher("/webapi/Client/ClientService/Login");
			d.forward(request, response);
			
		}
		if(((ClientType)request.getSession().getAttribute("loginAs")).equals(ClientType.Admin))
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Admin/admin.html"));
		}
		else if(((ClientType)request.getSession().getAttribute("loginAs")).equals(ClientType.Company))
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Company/company.html"));
		}
		else if(((ClientType)request.getSession().getAttribute("loginAs")).equals(ClientType.Customer))
		{
			response.sendRedirect(response.encodeRedirectURL("/CouponProjectWebsite/Customer/customer.html"));
		}
			
	}	
	
		

}
