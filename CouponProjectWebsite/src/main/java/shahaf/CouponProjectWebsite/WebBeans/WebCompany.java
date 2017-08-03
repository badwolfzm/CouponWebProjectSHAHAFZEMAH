package shahaf.CouponProjectWebsite.WebBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java_beans.Company;
import java_beans.Coupon;

public class WebCompany implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String compName;
	private String password;
	private String email;
	private Collection<WebCoupon> WebCoupons;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WebCompany [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", WebCoupons=" + WebCoupons + "]";
	}

	
	
	public WebCompany()
	{
		
	}
	
	public static WebCompany convertToWebCompany(Company company)
	{
		return new WebCompany(company);
	}
	
	public WebCompany(Company company)
	{
		this.compName = company.getCompName();
		this.id = Long.toString(company.getId());;
		this.password = company.getPassword();
		this.email = company.getEmail();
		this.WebCoupons = new ArrayList();
		System.out.println(company.getCoupons());
		for(Coupon coupon : company.getCoupons())
		{
			System.out.println(coupon);
			if(coupon != null)
			{
				this.WebCoupons.add(new WebCoupon(coupon));

			}
		}
	}
	
	public Company convertToCompany()
	{
		Company company = new Company();
		try
		{
			company.setId(Long.parseLong(id, 10) );
		}
		catch(Exception e)
		{
			company.setId(-1L );
		}
		company.setCompName(compName);
		company.setPassword(password);
		company.setEmail(email);
		if(WebCoupons == null)
		{
			company.setCoupons(new ArrayList<>());
		}
		else
		{
			company.setCoupons(WebCoupon.convertToCoupons(WebCoupons));
		}
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((WebCoupons == null) ? 0 : WebCoupons.hashCode());
		result = prime * result + ((compName == null) ? 0 : compName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		WebCompany other = (WebCompany) obj;
		if (WebCoupons == null) {
			if (other.WebCoupons != null)
				return false;
		} else if (!WebCoupons.equals(other.WebCoupons))
			return false;
		if (compName == null) {
			if (other.compName != null)
				return false;
		} else if (!compName.equals(other.compName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<WebCoupon> getWebCoupons() {
		return WebCoupons;
	}

	public void setWebCoupons(Collection<WebCoupon> webCoupons) {
		WebCoupons = webCoupons;
	}
}
