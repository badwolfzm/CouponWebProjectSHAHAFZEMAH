package shahaf.CouponProjectWebsite.Service;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import Facade.ClientType;
import Facade.CompanyFacade;
import java_beans.Company;
import shahaf.CouponProjectWebsite.WebBeans.WebCompany;
import shahaf.CouponProjectWebsite.WebBeans.WebCoupon;
import shahaf.CouponProjectWebsite.WebBeans.WebCustomer;

@Path("CompanyService")
public class CompanyService {

	/**
	 * this method get java beans Coupons Collection that will expire before the
	 * given date, only logged in Company will be able to use this method
	 * 
	 * @param date
	 *            is the last Date of expiration date of the coupons
	 * @return java beans Coupons Collection that will expire before the given
	 *         date
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
	@Path("/get/AllCouponUntilDate")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponUntilDate(@QueryParam("date") String date)
	{
	
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				//GenericEntity<Collection<WebCoupon> gen = new GenericEntity(WebCoupon.convertToWebCoupons(facad.getAllCouponUntilDate(date))){};
				GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(facad.getAllCouponUntilDate(new java.sql.Date(Long.parseLong(date,10))))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method get java beans Coupons Collection of the given Coupon type,
	 * only logged in Company will be able to use this method
	 * 
	 * @param CouponType
	 *            is the type of coupon to provide
	 * @return java beans Coupons Collection of the given Coupon type
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
	@Path("/get/AllCouponByCouponType")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCouponByCouponType(@QueryParam("CouponType") String CouponType)
	{
	
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				
				GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(facad.getAllCouponByType(java_beans.CouponType.valueOf(CouponType)))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	

	
	/**
	 * this method get java beans Coupons Collection, only logged in Company
	 * will be able to use this method
	 * 
	 * @return java beans Coupons Collection
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
	@Path("/get/AllCoupon")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCoupon()
	{
	
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				
				GenericEntity<Collection<WebCoupon>> genericEntity = new GenericEntity<Collection<WebCoupon>>(WebCoupon.convertToWebCoupons(facad.getAllCoupon())){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	

	/**
	 * this method get java beans Coupon object associated with the given id,
	 * only logged in Company will be able to use this method
	 * 
	 * @param id
	 *            is the Coupon id
	 * @return java beans Coupon object associated with the given id
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - coupon can not be viewed, this coupon does not belong to
	 *             your company
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorect
	 */
	@POST
	@Path("/get/Coupon")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCoupon(@QueryParam("id") String id)
	{
	
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCoupon> genericEntity = new GenericEntity<WebCoupon>(new WebCoupon(facad.getCoupon(Long.parseLong(id,10)))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	
	/**
	 * this method updates Coupon, it will first check that Coupon with the same
	 * title exist and then update it,only logged in Company will be able to use
	 * this method
	 * 
	 * @param Coupon
	 *            is the Coupon Object to update
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - coupon can not be updated, this coupon does not belong to
	 *             your company
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorect
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
	 *             - Coupon Amount is ivalid-must be over 0
	 * @throws FacadeException
	 *             - Coupon Price is ivalid-must be over 0
	 * @throws FacadeException
	 *             - invalid Coupon type name length- max length is 50
	 *             characters
	 * @throws FacadeException
	 *             - invalid Coupon message length- max length is 150 characters
	 * @throws FacadeException
	 *             - invalid Coupon image URL length- max length is 250
	 *             characters
	 * @throws FacadeException
	 *             - update failed- Coupon does not exist in DB
	 */
	@PUT
	@Path("/update/Coupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response updateCoupon(WebCoupon Coupon)
	{
	
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				facad.updateCoupon(Coupon.convertToCoupon());
				return javax.ws.rs.core.Response.ok("Updated Secssesfully the Coupon").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an Company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}
	
	/**
	 * this method removes/delete Coupon, it will first check that Coupon with
	 * the same title exist and will delete it,only logged in company will be
	 * able to use this method
	 * 
	 * @param Coupon
	 *            is the Coupon Object to remove
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - remove failed- Coupon does not exist in DB
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorrect
	 */
	@PUT
	@Path("/delete/Coupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response removeCoupon(WebCoupon Coupon)
	{
	
		System.out.println("started deletion of coupon");
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				facad.removeCoupon(Coupon.convertToCoupon());
				return javax.ws.rs.core.Response.ok("Deleted Secssesfully the coupon").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}
	
	/**
	 * this method will return the 'Object Company' of the Company that
	 * performed login
	 * 
	 * @return the 'Object Company' that performed login
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as company in order to get company
	 */
	@POST
	@Path("/get/ThisCompany")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response ThisCompany()
	{
	
		
		try {
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCompany> genericEntity = new GenericEntity<WebCompany>(WebCompany.convertToWebCompany(facad.ThisCompany())){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method create a new Coupon Object, it will first check that Coupon
	 * with the same title doesn't already exist,only logged in Company will be
	 * able to use this method
	 * 
	 * @param Coupon
	 *            is the Coupon Object to create
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - unable to create Coupon- Coupon name already exist in DB
	 * @throws FacadeException
	 *             - check your login credentials ,your user name, password or
	 *             user role is incorect
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
	 *             - Coupon Amount is ivalid-must be over 0
	 * @throws FacadeException
	 *             - Coupon Price is ivalid-must be over 0
	 * @throws FacadeException
	 *             - invalid Coupon type name length- max length is 50
	 *             characters
	 * @throws FacadeException
	 *             - invalid Coupon message length- max length is 150 characters
	 * @throws FacadeException
	 *             - invalid Coupon image URL length- max length is 250
	 *             characters
	 */
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	@POST
	@Path("/create/Coupon")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCoupon(WebCoupon WebCoupon)
	{
	
		
		try 
		{
			CompanyFacade facad = (CompanyFacade) request.getSession().getAttribute("facade");
			
			System.out.println(facad.IsloginAs(ClientType.Company));
			try {
				facad.createCoupon(WebCoupon.convertToCoupon());
				System.out.println("succesesfully created");
				return javax.ws.rs.core.Response.ok(WebCoupon.getTitle()+" was created.").status(200).build();

			} catch (ConnectionPoolException | DAOException | FacadeException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				return javax.ws.rs.core.Response.ok("problames with excecution\r\n"+e.toString()).status(500).build();
			}
		
			/*
			if(!facad.IsloginAs(ClientType.Company))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company").status(401).build();
			}
			else
			{
				try {
					facad.createCoupon(WebCoupon.convertToCoupon());
				} catch (ConnectionPoolException | DAOException | FacadeException e) {
					// TODO Auto-generated catch block
					return javax.ws.rs.core.Response.ok("problames with excecution\r\n"+e.toString()).status(500).build();
				}
			}
		
		*/	
		} catch (Exception  e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an company\r\n"+e1.toString()).status(401).build();
		
		} 
		

	}
	
}
