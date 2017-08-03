package shahaf.CouponProjectWebsite.Service;

import java.awt.List;
import java.io.Console;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import Data_Base.Connection_Pool;
import Exceptions.ConnectionPoolException;
import Exceptions.DAOException;
import Exceptions.FacadeException;
import Facade.AdminFacade;
import Facade.ClientType;
import java_beans.Company;
import java_beans.Customer;
import shahaf.CouponProjectWebsite.WebBeans.ConnectionPoolCommand;
import shahaf.CouponProjectWebsite.WebBeans.WebCompany;
import shahaf.CouponProjectWebsite.WebBeans.WebCustomer;

@Path("AdminService")
public class AdminService {

	/**
	 * this method Deletes All Coupons that have expired, only logged in Admin
	 * will be able to use this method
	 * 
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as admin in order to get All Customers
	 */
	@DELETE
	@Path("/delete/AllCouponsOutOfDate")
	public javax.ws.rs.core.Response DeleteAllCouponsOutOfDate()
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				System.out.println("deleting all out of date coupons");
				facad.DeleteAllCouponsOutOfDate();
				System.out.println(" all out of date coupons were deleted");
				return javax.ws.rs.core.Response.ok("All coupons out of date where deleted").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("eror oucured \r\n"+e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}

	/**
	 * this method get java beans all Customers Collection, only logged in Admin
	 * will be able to use this method
	 * 
	 * @return java beans all Customers Collection
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as admin in order to get All Customers
	 */
	@POST
	@Path("/get/AllCustomer")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCustomer()
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				Collection<WebCustomer> webCol = new ArrayList<>() ;
				for(Customer c: facad.getAllCustomer())
				{
					webCol.add(WebCustomer.ConvertToWebCustomer(c));
				}
				GenericEntity<Collection<WebCustomer>> genericEntity = new GenericEntity<Collection<WebCustomer>>(webCol){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method will return the 'Object Customer' of the Customer with the
	 * given name, only logged in Admin will be able to use this method
	 * 
	 * @param name
	 *            is the Customer name to get
	 * @return the 'Object Customer' of the Customer with the given name
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin in order to get customer
	 */
	@POST
	@Path("/get/ThisCustomer")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response ThisCustomer(@QueryParam("name") String name)
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCustomer> genericEntity = new GenericEntity<WebCustomer>(WebCustomer.ConvertToWebCustomer(facad.ThisCustomer(name))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method get java beans Customer object associated with the given id,
	 * only logged in Admin will be able to use this method
	 * 
	 * @param id
	 *            is the Customer id
	 * @return java beans Customer object associated with the given id
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin in order to get customer
	 * @throws FacadeException
	 */
	@POST
	@Path("/get/customer")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCustomer(@QueryParam("id") String id)
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCustomer> genericEntity = new GenericEntity<WebCustomer>(WebCustomer.ConvertToWebCustomer(facad.getCustomer(Long.parseLong(id,10)))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(id);
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method updates Customer, it will first check that Customer with the
	 * same name exist and then update it, only logged in Admin will be able to
	 * use this method
	 * 
	 * @param Customer
	 *            is the Customer Object to update
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin
	 * @throws FacadeException
	 *             - Unable to update Customer, a Customer with that name or ID
	 *             does not exist
	 * @throws FacadeException
	 *             - you need to login as admin in order to update customer
	 * @throws FacadeException
	 *             - one of your customer parameters is null-please check it
	 * @throws FacadeException
	 *             - your Customer name length is invalid-name must be 3-50
	 *             characters
	 * @throws FacadeException
	 *             - your Customer password length is invalid-paswword must be
	 *             4-50 characters
	 */
	@PUT
	@Path("/update/Customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response updateCustomer(WebCustomer customer)
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				facad.updateCustomer(customer.ConvertToCustomer(customer));
				return javax.ws.rs.core.Response.ok("Updated Secssesfully the customer").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}
	
	/**
	 * this method removes/delete Customer, it will first check that Customer
	 * with the same name exist and will delete it, only logged in Admin will be
	 * able to use this method
	 * 
	 * @param Customer
	 *            is the Customer Object to remove
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin
	 * @throws FacadeException
	 *             - Unable to remove Customer, a Customer with that name or ID
	 *             does not exist
	 * @throws FacadeException
	 *             - you need to login as admin in order to remove Customer
	 */
	@PUT
	@Path("/delete/customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response removeCustomer(WebCustomer customer)
	{
	
		//THERE WAS A PROBLAM IN THE FACAD SO I HAD TO DO THE DELITION MANULLY
		System.out.println(customer.ConvertToCustomer(customer));
		System.out.println("entered delition of customer");
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				Customer c = facad.ThisCustomer(customer.getCustName());
				Customer converted = customer.ConvertToCustomer(customer);
				WebCustomer WC = new WebCustomer(c);
				if(WC.equals(customer))
				{
					System.out.println("are Equals");
					try
					{
						new CustomerDBDAO().removeCustomer(c);
						return javax.ws.rs.core.Response.ok("Deleted Secssesfully the customer").status(200).build();
					}
					catch(Exception e)
					{
						return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
					}
					
				}
				else
				{
					return javax.ws.rs.core.Response.ok("not equals").status(500).build();

				}
				/*
				System.out.println("are equals"+customer.ConvertToCustomer(customer).equals(c));
				System.out.println(c);
				System.out.println(customer.ConvertToCustomer(customer));
				System.out.println("start deleting");
				facad.removeCustomer(customer.ConvertToCustomer(customer));
				System.out.println("delition complited");
				return javax.ws.rs.core.Response.ok("Deleted Secssesfully the customer").status(200).build();
				*/
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}
	

	/**
	 * this method create a new Customer, it will first check that Customer with
	 * the same name doesn't already exist, only logged in Admin will be able to
	 * use this method
	 * 
	 * @param Customer
	 *            is the Customer Object to create
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin
	 * @throws FacadeException
	 * @throws FacadeException
	 *             - one of your customer parameters is null-please check it
	 * @throws FacadeException
	 *             - your Customer name length is invalid-name must be 3-50
	 *             characters
	 * @throws FacadeException
	 *             - your Customer password length is invalid-paswword must be
	 *             4-50 characters
	 * @throws FacadeException
	 *             - unable to create customer- customer name already exist in
	 *             DB
	 * @throws FacadeException
	 *             - you need to login as admin in order to create Customer
	 */
	@POST
	@Path("/create/customer")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCustomer(WebCustomer customer)
	{
	
		
		try 
		{
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
		
		
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{

				try {
					facad.createCustomer(customer.ConvertToCustomer(customer));
				} catch (ConnectionPoolException | DAOException | FacadeException e) {
					// TODO Auto-generated catch block
					return javax.ws.rs.core.Response.ok("problames with excecution\r\n"+e.toString()).status(500).build();
				}
			}
		
		} catch (Exception  e1) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin\r\n"+e1.toString()).status(401).build();
		} 
		
		return javax.ws.rs.core.Response.ok(customer.getCustName()+" was created.").status(200).build();

	}
	
	/**
	 * this method get java beans Companies Collection, only logged in Admin
	 * will be able to use this method
	 * 
	 * @return java beans Companies Collection
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as admin in order to get All Companie
	 */
	@POST
	@Path("/get/AllCompanies")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAllCompanies()
	{
		System.out.println("entered GetAll Companies");
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			System.out.println("Got facad");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				System.out.println("not loged in as admin");
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				System.out.println("continue to get companies");
				Collection<WebCompany> webCol = new ArrayList<>() ;
				System.out.println(facad.getAllCompanies());
				for(Company c: facad.getAllCompanies())
				{
					System.out.println(c);
					if(c !=null)
					{
						System.out.println(c);
						webCol.add(WebCompany.convertToWebCompany(c));
					}
				}
				System.out.println(webCol);
				GenericEntity<Collection<WebCompany>> genericEntity = new GenericEntity<Collection<WebCompany>>(webCol){};
				Response r = Response.ok(genericEntity)
		                .header("200", "ok")
		                .build();
				System.out.println("sending");
				return r;
				
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok("Internal Erorr "+e.toString()).status(500).build();
		} 
		
	}
	
	/**
	 * this method will return the 'Object Company' of the Company with the
	 * given id, only logged in Admin will be able to use this method
	 * 
	 * @param id
	 *            is the Company id to get
	 * @return the 'Object Company' of the Company with the given id
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin in order to get company
	 */
	@POST
	@Path("/get/ThisCompany")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response ThisCompany(@QueryParam("name") String name)
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCompany> genericEntity = new GenericEntity<WebCompany>(WebCompany.convertToWebCompany(facad.ThisCompany(name))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method get java beans company object associated with the given id,
	 * only logged in Admin will be able to use this method
	 * 
	 * @param id
	 *            is the Company id
	 * @return java beans company object associated with the given id
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - you need to login as Admin
	 * @throws FacadeException
	 */
	@POST
	@Path("/get/Company")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getCompany(@QueryParam("id") String id)
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				
				GenericEntity<WebCompany> genericEntity = new GenericEntity<WebCompany>(WebCompany.convertToWebCompany(facad.getCompany(Long.parseLong(id,10)))){};
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
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
	}
	
	/**
	 * this method updates company, it will first check that company with the
	 * same name exist and then update it,only logged in Admin will be able to
	 * use this method
	 * 
	 * @param Company
	 *            is the Company Object to update
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - one of your Company parameters is null-please check it
	 * @throws FacadeException
	 *             - your Company name length is invalid-name must be 3-50
	 *             characters
	 * @throws FacadeException
	 *             - your Company email is not valid
	 * @throws FacadeException
	 *             - throw exception invalid password
	 * @throws FacadeException
	 *             - you need to login as admin in order to update company
	 * @throws FacadeException
	 *             - update failed- Company does not exist in DB
	 */
	@PUT
	@Path("/update/Company")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response updateCompany(WebCompany Company)
	{
	
		System.out.println(Company);
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				System.out.println("failed");
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				System.out.println("secssesfull");
				facad.updateCompany(Company.convertToCompany());
				return javax.ws.rs.core.Response.ok("Updated Secssesfully the company").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}
	
	/**
	 * this method removes/delete company, it will first check that company with
	 * the same name exist and will delete it,only logged in Admin will be able
	 * to use this method
	 * 
	 * @param Company
	 *            is the Company Object to remove
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - Unable to remove company, a company with that name or ID
	 *             does not exist
	 * @throws FacadeException
	 *             - you need to login as admin in order to remove company
	 * @throws FacadeException
	 *             - remove failed- Company does not exist in DB
	 */
	@PUT
	@Path("/delete/Company")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response removeCompany(WebCompany Company)
	{
	
		//THERE WAS A PROBLAM IN THE FACAD SO I HAD TO DO THE DELITION MANULLY
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				Company c = facad.ThisCompany(Company.getCompName());
				WebCompany WC = Company.convertToWebCompany(c);
				if(WC.equals(Company))
				{
					System.out.println("are Equals");
					try
					{
						new CompanyDBDAO().removeCompany(c);
						return javax.ws.rs.core.Response.ok("Deleted Secssesfully the company").status(200).build();
					}
					catch(Exception e)
					{
						return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
					}
					
				}
				else
				{
					return javax.ws.rs.core.Response.ok("not equals").status(500).build();

				}
				//return javax.ws.rs.core.Response.ok("Deleted Secssesfully the company").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
		
	}
	
	/**
	 * creates the daily thread
	 * 
	 * 
	 */
	@POST
	@Path("/create/DailyThread")
	//@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createDailyThread()
	{
	
		
		try {
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				System.out.println("creating dailythread");
				Facade.DailyThread DailyThread;
				//TODO change this to the admin username and password
				DailyThread = new Facade.DailyThread("admin", "1234");
				System.out.println("daily thread was created");
				return javax.ws.rs.core.Response.ok("opened the daily thread").status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Internal Erorr").status(500).build();
		} 
		
	}
	
	/**
	 * this method uses the CreateDBandConnections method and does it exactly
	 * the same. Creates the DB(if not already created) and 2 sets of
	 * Connections, for closed and open connections, it also gives a limited
	 * number of connections available
	 * 
	 * @param url
	 *            is the DB location
	 * @param numOfConn
	 *            is the limited number of connections available
	 * @throws ConnectionPoolException - Unable to connect to the database, please check your url path
	 */
	//@Context HttpServletRequest request;
	//@Context private HttpServletResponse response;
	@POST
	@Path("/create/connections")
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createConnections(ConnectionPoolCommand cmd)
	{
	
		
		try {
			Enumeration<Driver> en = DriverManager.getDrivers();

			// print drivers
			while (en.hasMoreElements()) {
				System.out.println(en.nextElement().getClass().getName());
			}
			String driverName = "org.apache.derby.jdbc.ClientDriver";

			try {
				Class.forName(driverName);
				System.out.println(driverName + " loaded successfuly");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			System.out.println("Creating new Connections"+cmd.toString());
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
			if(!facad.IsloginAs(ClientType.Admin))
			{
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				Connection_Pool.getInstance().CreateDBandConnections(cmd.getUrl(), cmd.getAmmountInt());
				return javax.ws.rs.core.Response.ok(cmd.getAmmount()+" was created at url: "+cmd.getUrl()).status(200).build();
			}
			
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return javax.ws.rs.core.Response.ok(e.toString()).status(500).build();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin \r\n"+e.toString()).status(401).build();
		} /*catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return javax.ws.rs.core.Response.ok("Internal Erorr "+e.getMessage()).status(500).build();
		} */
		
	}
	
	/**
	 * this method create a new company, it will first check that company with
	 * the same name doesn't already exist,only logged in Admin will be able to
	 * use this method
	 * 
	 * @param Company
	 *            is the Company Object to create
	 * @throws ConnectionPoolException
	 *             - Connection Pool has been interrupted
	 * @throws DAOException
	 *             -you might have a connection or Query problem, please check
	 *             your url path and your Query
	 * @throws FacadeException
	 *             - one of your Company parameters is null-please check it
	 * @throws FacadeException
	 *             - your Company name length is invalid-name must be 3-50
	 *             characters
	 * @throws FacadeException
	 *             - your Company email is not valid
	 * @throws FacadeException
	 *             - throw exception invalid password
	 * @throws FacadeException
	 *             - unable to create Company- Company name already exist in DB
	 * @throws FacadeException
	 *             - you need to login as admin in order to create new company
	 */
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	@POST
	@Path("/create/company")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response createCompany(WebCompany Company)
	{
	
		try 
		{
			AdminFacade facad = (AdminFacade) request.getSession().getAttribute("facade");
		
			System.out.println(Company);
			if(!facad.IsloginAs(ClientType.Admin))
			{
				System.out.println("not loged in");
				return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin").status(401).build();
			}
			else
			{
				try {
					facad.createCompany(Company.convertToCompany());
				} catch (ConnectionPoolException | DAOException | FacadeException e) {
					// TODO Auto-generated catch block
					return javax.ws.rs.core.Response.ok("problames with excecution\r\n"+e.toString()).status(500).build();
				}
			}
			
		} catch (Exception  e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return javax.ws.rs.core.Response.ok("Unauthorized not loged in as an admin\r\n"+e1.toString()).status(401).build();
		} 
		
		return javax.ws.rs.core.Response.ok(Company.getCompName()+" was created.").status(200).build();

	}
}
