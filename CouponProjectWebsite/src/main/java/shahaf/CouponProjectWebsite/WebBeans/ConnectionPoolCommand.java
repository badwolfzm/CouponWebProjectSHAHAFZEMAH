package shahaf.CouponProjectWebsite.WebBeans;

import java.io.Serializable;

public class ConnectionPoolCommand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String ammount;
	public ConnectionPoolCommand() {
		super();
	}
	public ConnectionPoolCommand(String url, String ammount) {
		super();
		this.url = url;
		this.ammount = ammount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getAmmountInt() {
		return Integer.parseInt(ammount, 10);
	}
	@Override
	public String toString() {
		return "ConnectionPoolCommand [url=" + url + ", ammount=" + ammount + "]";
	}
	public String getAmmount() {
		return ammount;
	}
	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

}
