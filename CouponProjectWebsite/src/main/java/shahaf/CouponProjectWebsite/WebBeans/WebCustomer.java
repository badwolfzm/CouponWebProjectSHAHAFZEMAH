package shahaf.CouponProjectWebsite.WebBeans;

import java.io.Serializable;
import java.util.Collection;

import java_beans.Coupon;
import java_beans.Customer;

public class WebCustomer implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String custName;
	private String password;
	private Collection<WebCoupon> coupons;
	
	public WebCustomer()
	{
		
	}
	
	public WebCustomer(Customer customer)
	{
		this.setId(String.valueOf(customer.getId()));
		this.setCustName(customer.getCustName());
		this.setPassword(customer.getPassword());
		this.setCoupons(WebCoupon.convertToWebCoupons(customer.getCoupons()));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public static Customer ConvertToCustomer(WebCustomer webCustomer)
	{
		Customer customer = new Customer();
		customer.setId(webCustomer.getIdLong());
		customer.setCustName(webCustomer.getCustName());
		customer.setPassword(webCustomer.getPassword());
		customer.setCoupons(WebCoupon.convertToCoupons(webCustomer.getCoupons()));
		return customer;
	}
	
	public static WebCustomer ConvertToWebCustomer(Customer customer)
	{
		return new WebCustomer(customer);
	}
	public long getIdLong() {
		try
		{
			return Long.parseLong(id,10);
		}
		catch(Exception e)
		{
			return -1L;
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + ((custName == null) ? 0 : custName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebCustomer other = (WebCustomer) obj;
		if (coupons == null) {
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		if (custName == null) {
			if (other.custName != null)
				return false;
		} else if (!custName.equals(other.custName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<WebCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<WebCoupon> coupons) {
		this.coupons = coupons;
	}
}
