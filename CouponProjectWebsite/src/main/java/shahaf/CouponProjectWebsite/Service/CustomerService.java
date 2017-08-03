package shahaf.CouponProjectWebsite.Service;

import java.util.Collection;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Exceptions.ConnectionPoolException;
import Exceptions.DAOException;
import Exceptions.FacadeException;
import Facade.ClientType;
import Facade.CompanyFacade;
import Facade.CouponSystem;
import Facade.CustomerFacade;
import shahaf.CouponProjectWebsite.WebBeans.LoginInfo;
import shahaf.CouponProjectWebsite.WebBeans.WebCompany;
import shahaf.CouponProjectWebsite.WebBeans.WebCoupon;
import shahaf.CouponProjectWebsite.WebBeans.WebCustomer;

@Path("Customer")
public class CustomerService {


	/**
	 * this method will return the 'Object Customer' of the Customer that
	 * performed login
	 * 
	 * @return the 'Object Customer' of the Customer that performed login
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as customer in order to get customer
	 */
	@POST
	@Path("/get/ThisCustomer")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response ThisCustomer()
	{
	
		
		try {
			CustomerFacade facad = (CustomerFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Customer))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCustomer> genericEntity = new GenericEntity<WebCustomer>(WebCustomer.ConvertToWebCustomer(facad.ThisCustomer())){};
				Response r = Response.ok(genericEntity)
		                .header("200", "ok")
		                .build();
				return r;
				
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	
	/**
	 * this method get java beans Coupons Collection that belong to the logged
	 * in Customer and is from the given Coupon Type, only logged in Customer
	 * will be able to use this method
	 * 
	 * @param type
	 *            is the Coupon Type you wish the get
	 * @return java beans Coupons Collection that belong to the logged in
	 *         Customer and is from the given Coupon Type
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorect
	 */
	@POST
	@Path("/get/AllPurchasedCouponsByType")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllPurchasedCouponsByType(@QueryParam("CouponType") String CouponType)
	{
	
		
		try {
			CustomerFacade facad = (CustomerFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Customer))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer").status(401).build();
			}
			else
			{
				
				GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(facad.getAllPurchasedCouponsByType(java_beans.CouponType.valueOf(CouponType)))){};
				Response r = Response.ok(genericEntity)
		                .header("200", "ok")
		                .build();
				return r;
				
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	
	/**
	 * this method get java beans Coupons Collection that belong to the logged
	 * in Customer, only logged in Customer will be able to use this method
	 * 
	 * @return java beans Coupons Collection that belong to the logged in
	 *         Customer,
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as customer
	 */
	@POST
	@Path("/get/AllPurchasedCoupons")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllPurchasedCoupons()
	{
	
		
		try {
			CustomerFacade facad = (CustomerFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Customer))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer").status(401).build();
			}
			else
			{
				
				GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(facad.getAllPurchasedCoupons())){};
				Response r = Response.ok(genericEntity)
		                .header("200", "ok")
		                .build();
				return r;
				
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	

	/**
	 * this method checks that user is logged in as a Customer and will perform
	 * purchase that will indicate that the coupon now belongs to the Customer,
	 * the purchase will fail in the following cases- Coupon date has expired,
	 * Coupon is out of stock (amount=0), same coupon already belongs to this
	 * Customer
	 * 
	 * @param coupon
	 *            is the specific Coupon to buy
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - purchase failed- a Customer can not buy more than 1 of the
	 *             same coupon
	 * @throws FacadeException
	 *             - purchase failed- a Customer can not buy expired coupon
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorrect
	 * @throws FacadeException
	 *             - purchase failed- Coupon does not exist in DB
	 */
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	@POST
	@Path("/purchaseCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response purchaseCoupon(WebCoupon Coupon)
	{
	
		//System.out.println("************************************************************************");
		/*
		GenericEntity<LoginInfo> genericEntity = new GenericEntity<LoginInfo>(new LoginInfo("admin","1234","Admin")){};
		Response r = Response.ok(genericEntity)
                .header("200", "ok")
                .build();
		return r;
		*/
		try {
			CustomerFacade facad = (CustomerFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Customer))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer").status(401).build();
			}
			else
			{
				facad.purchaseCoupon(Coupon.convertToCoupon());
				return javax.ws.rs.core.Response.ok("Coupon "+Coupon.toString()+ "was purcused by "+ facad.toString()).status(200).build();
				
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Customer \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
}
