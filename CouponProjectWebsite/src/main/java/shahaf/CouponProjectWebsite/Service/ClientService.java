package shahaf.CouponProjectWebsite.Service;

import java.io.PrintWriter;
import java.util.Collection;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import Facade.AdminFacade;
import Facade.Client;
import Facade.ClientType;
import Facade.CouponSystem;
import shahaf.CouponProjectWebsite.WebBeans.ConnectionPoolCommand;
import shahaf.CouponProjectWebsite.WebBeans.LoginInfo;
import shahaf.CouponProjectWebsite.WebBeans.WebCompany;
import shahaf.CouponProjectWebsite.WebBeans.WebCoupon;
import shahaf.CouponProjectWebsite.WebBeans.WebCustomer;

@Path("Client")
public class ClientService {

	@POST
	@Path("/get/AllCouponByPriceOfCustId")
	@Produces(MediaType.APPLICATION_JSON)
	
	/**
	 * looks for all the coupons that belongs to a specific Customer that their
	 * cost is below the given price and returns a collection of coupons
	 * 
	 * @param price
	 *            is the max price of coupon to search
	 * @param id
	 *            is the customer id that purchased the coupons
	 * @return a collection of coupons below a specific price and belongs to a
	 *         specific Customer
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException - you need to login as Admin in order to get all coupon by price of customer
	 */
	public javax.ws.rs.core.Response getAllCouponByPriceOfCustId(@QueryParam("price") String price,@QueryParam("id") String id)
	{
	
		//TODO this function for this company
		
		try {
			System.out.println(price);
			System.out.println(Double.parseDouble(price));
			System.out.println(Long.parseLong(id));
			GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(new Client().getAllCouponByPriceOfCustId(Double.parseDouble(price),Long.parseLong(id)))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println("printing stack Trace");
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok("Internal Erorr"+e.toString()).status(500).build();
		} 
	}
	

	/**
	 * looks for all the coupons that their
	 * cost is below the given price and returns a collection of coupons
	 * 
	 * @param price
	 *            is the max price of coupon to search
	 * @param id
	 *            is the customer id that purchased the coupons
	 * @return a collection of coupons below a specific price and belongs to a
	 *         specific Customer
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * 
	 */
	@POST
	@Path("/get/AllCouponByPrice")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponByPrice(@QueryParam("price") String price)
	{
	
		
		try {
			
			GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(new Client().getAllCouponByPrice(Double.parseDouble(price),0))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * looks for all the coupons that belongs to a this company that their
	 * cost is below the given price and returns a collection of coupons
	 * 
	 * @param price
	 *            is the max price of coupon to search
	 * @param id
	 *            is the customer id that purchased the coupons
	 * @return a collection of coupons below a specific price and belongs to a
	 *         specific Customer
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException - you need to login as Admin in order to get all coupon by price of customer
	 */
	@POST
	@Path("/get/AllCouponByPriceOfCompId")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponByPriceOfCompId(@QueryParam("price") String price,@QueryParam("id") String id)
	{
	
		//TODO this function for this company
		
		try {
			
			GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(new Client().getAllCouponByPriceOfCompId(Double.parseDouble(price),Long.parseLong(id)))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method looks for all the coupons from a specific type and returns a
	 * collection of coupons of that Type
	 * 
	 * @param couponType
	 *            is the type of coupon to get
	 * @return a collection of coupons of the given Type
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 */
	@POST
	@Path("/get/AllCouponsByType")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponsByType(@QueryParam("CouponType") String CouponType)
	{
	
		
		try {
			
			GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(new Client().getAllCouponsByType(java_beans.CouponType.valueOf(CouponType)))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method looks for all the coupons that belongs to a specific Company
	 * (by Company id) and returns that Company collection of coupons
	 * 
	 * @param id
	 *            is the company id
	 * @return a collection of coupons that belongs the that company
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 */
	@POST
	@Path("/get/AllCouponsOfCompany")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponsOfCompany(@QueryParam("id") String id)
	{
	
		
		try {
			
			GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(new Client().getAllCouponsOfCompany(Long.parseLong(id, 10) ))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method looks for a coupon that has this title and returns the Object
	 * Coupon with that title
	 * 
	 * @param Title
	 *            is the title/name of the coupon
	 * @return the Object Coupon that has this title
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 */
	@POST
	@Path("/get/CouponByTitle")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCouponByTitle(@QueryParam("title") String Title)
	{
	
		
		try {
			
			GenericEntity<WebCoupon> genericEntity = new GenericEntity<WebCoupon>(new WebCoupon(new Client().getCouponByTitle(Title))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method returns the Object Coupon that has this Coupon ID
	 * 
	 * @param id
	 *            is the Coupon id to provide
	 * @return a Coupon Object
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 */
	@POST
	@Path("/get/CouponById")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCouponById(@QueryParam("id") String id)
	{
	
		
		try {
			
			GenericEntity<WebCoupon> genericEntity = new GenericEntity<WebCoupon>(new WebCoupon(new Client().getCouponById(Long.parseLong(id, 10) ))){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method returns a collection of coupons that exist in DB
	 * 
	 * @return a collection of coupons that exist in DB
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 */
	@POST
	@Path("/get/AllCoupons")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCoupons()
	{
	
		
		try {
			System.out.println();
			GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(new Client().getAllCoupons())){};
			Response r = Response.ok(genericEntity)
	                .header("200", "ok")
	                .build();
			return r;
				
			
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method checks what type of client has logged in and returns a
	 * 'LogedIn' flag
	 * 
	 * @param clientType
	 *            is the type of client that perform the login- a customer, a
	 *            company or an admin
	 * @return a 'LogedIn' flag
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorrect
	 */
	@POST
	@Path("/ClientService/IsloginAs")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response IsloginAs(@QueryParam("ClientType")String ClientType)
	{
	
		
		
		try {
			return javax.ws.rs.core.Response.ok(((ClientType)request.getSession().getAttribute("loginAs")).toString().equals(ClientType)).status(200).build();
			
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Error, not loged in").status(500).build();
		} 
		
		//return javax.ws.rs.core.Response.ok().status(400).build();
		
	}
	
	/**
	 * this method checks if the Object Company is valid, it will check for the
	 * following- Company parameters are not null, Company name length between
	 * 3-50 characters, Company email validity, Company password length between
	 * 4-50 characters
	 * 
	 * @param company
	 *            is the Object Company to check its validity
	 * @return true for valid Company and throws exceptions for invalid Company
	 * @throws FacadeException
	 *             - one of your Company parameters is null-please check it
	 * @throws FacadeException
	 *             - your Company name length is invalid-name must be 3-50
	 *             characters
	 * @throws FacadeException
	 *             - your Company email is not valid
	 * @throws FacadeException
	 *             - throw exception invalid password
	 */
	//TODO This.company, This.Customer
	@POST
	@Path("/ClientService/isValidCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response isValidCompany(WebCompany company)
	{
	
		
		
		try {
			return javax.ws.rs.core.Response.ok(company.toString()+", "+(new Client().isValidCompany(company.convertToCompany()))+" valid").status(200).build();
			
		} catch ( FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		//return javax.ws.rs.core.Response.ok().status(400).build();
		
	}
	
	/**
	 * this method checks if the Object Customer is valid, it will check for the
	 * following- Customer parameters are not null, Customer name length between
	 * 3-50 characters, Customer password length between 4-50 characters
	 * 
	 * @param customer
	 *            is the Object Customer to check its validity
	 * @return true for valid Customer and throws exceptions for invalid
	 *         Customer
	 * @throws FacadeException
	 *             - one of your customer parameters is null-please check it
	 * @throws FacadeException
	 *             - your Customer name length is invalid-name must be 3-50
	 *             characters
	 * @throws FacadeException
	 *             - your Customer password length is invalid-password must be
	 *             4-50 characters
	 */
	@POST
	@Path("/ClientService/isValidCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response isValidCustomer(WebCustomer customer)
	{
	
		
		
		try {
			return javax.ws.rs.core.Response.ok(customer.toString()+", "+(new Client().isValidCustomer(customer.ConvertToCustomer(customer)))+" valid").status(200).build();
			
		} catch ( FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		//return javax.ws.rs.core.Response.ok().status(400).build();
		
	}

	/**
	 * this method checks if the Object Coupon is valid, it will check for the
	 * following- coupon parameters are not null,Coupon title length between
	 * 3-100 characters,end date cannot be expired,Start date can not be after
	 * end date,Coupon Amount is over 0,Coupon Price is over 0,Coupon type name
	 * length under 50 characters, Coupon message length under 150 characters,
	 * Coupon image URL length under 250 characters
	 * 
	 * @param coupon
	 *            is the Object Coupon to check its validity
	 * @return true for valid coupon and throws exceptions for invalid Coupon
	 * @throws FacadeException
	 *             - one of your coupon parameters is null-please check it
	 * @throws FacadeException
	 *             - your Coupon title length is invalid-title must be 3-100
	 *             characters
	 * @throws FacadeException
	 *             - Coupon End date is not valid-date had expired
	 * @throws FacadeException
	 *             - Coupon Start date can not be after end date
	 * @throws FacadeException
	 *             - Coupon Amount is invalid-must be over 0
	 * @throws FacadeException
	 *             - Coupon Price is invalid-must be over 0
	 * @throws FacadeException
	 *             - invalid Coupon type name length- max length is 50
	 *             characters
	 * @throws FacadeException
	 *             - invalid Coupon message length- max length is 150 characters
	 * @throws FacadeException
	 *             - invalid Coupon image URL length- max length is 250
	 *             characters
	 */
	@POST
	@Path("/ClientService/isValidCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response isValidCoupon(WebCoupon coupon)
	{
	
		
		
		try {
			return javax.ws.rs.core.Response.ok(coupon.toString()+", "+(new Client().isValidCoupon(coupon.convertToCoupon()))+" valid").status(200).build();
			
		} catch ( FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		//return javax.ws.rs.core.Response.ok().status(400).build();
		
	}
	
	/**
	 * this method checks if the correct user name,password is entered for the
	 * correct client type and returns a 'LogedIn' flag when credentials are
	 * correct
	 * 
	 * @param userName
	 *            is the user name to enter
	 * @param password
	 *            is the password to enter
	 * @param clientType
	 *            is the type of client that perform the login- a customer, a
	 *            company or an admin
	 * @return 'LogedIn' flag when the correct user name,password is entered for
	 *         the correct client type
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorrect
	 */
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	@POST
	@Path("/ClientService/Login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response Login(LoginInfo login)
	{
		System.out.println(login);
		//System.out.println("************************************************************************");
		/*
		GenericEntity<ConnectionPoolCommand> genericEntity = new GenericEntity<ConnectionPoolCommand>(new ConnectionPoolCommand("jdbc:derby://localhost:1527/coupondb", "10")){};
		Response r = Response.ok(genericEntity)
                .header("200", "ok")
                .build();
		return r;
		/*
		*/
		CouponSystem Csys = CouponSystem.getInstance();
		try {
			Client client  =  Csys.login(login.getUserNameWithException(), login.getPasswordWithException(), login.getClientTypeAsClientType());
			System.out.println(client);
			System.out.println();
			request.getSession().setAttribute("facade",client);
			request.getSession().setAttribute("loginAs",login.getClientTypeAsClientType());
			return javax.ws.rs.core.Response.ok(login.getUserNameWithException()+" was succsesfully logged in as "+login.getClientTypeAsClientType().toString()+".").status(200).build();
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(400).build();
		} catch (ConnectionPoolException | DAOException | FacadeException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection error" + e.toString());
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println("internal error" + e.toString());
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		//return javax.ws.rs.core.Response.ok().status(400).build();
		
	}
}
