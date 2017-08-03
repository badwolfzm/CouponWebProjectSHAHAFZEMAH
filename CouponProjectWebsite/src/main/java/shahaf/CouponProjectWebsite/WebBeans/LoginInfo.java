package shahaf.CouponProjectWebsite.WebBeans;

import java.io.Serializable;

import javax.security.auth.login.LoginException;

import Exceptions.FacadeException;

public class LoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String Password;
	private String ClientType;
	public String getUserNameWithException() throws LoginException {
		if (userName.length() > 50 || userName.length() < 3) {
			LoginException facadeException = new LoginException(
					"your name length is invalid-name must be 3-50 characters");
			throw facadeException;
		}
		return userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswordWithException() throws LoginException {
		

		if (Password.length() < 4 || (Password.length() > 50)) {
			LoginException facadeException = new LoginException(
					"your password length is invalid-paswword must be 4-50 characters");
			throw facadeException;
		}
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Facade.ClientType getClientTypeAsClientType()  {
		System.out.println(this);
		Facade.ClientType c = Facade.ClientType.valueOf(ClientType);
		if(c.toString().equals(ClientType))
		{
			return c;
		}
		else
		{
			return null;
			//throw new LoginException("None Existed ClientType");
		}
	}
	public String getClientType() {
		return ClientType;
	}
	@Override
	public String toString() {
		return "LoginInfo [userName=" + userName + ", Password=" + Password + ", ClientType=" + ClientType + "]";
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
	}
	public LoginInfo(String userName, String password, String clientType) {
		super();
		this.userName = userName;
		Password = password;
		ClientType = clientType;
	}
	public LoginInfo() {
		super();
	}
	
	
}
