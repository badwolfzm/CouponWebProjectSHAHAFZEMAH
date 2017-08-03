package shahaf.CouponProjectWebsite.WebBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java_beans.Coupon;
import java_beans.CouponType;

public class WebCoupon implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String startDate;
	private String endDate;
	private String amount;
	private String type;
	private String message;
	private String price;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		WebCoupon other = (WebCoupon) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	private String image;
	
	public WebCoupon()
	{
		
	}
	
	public WebCoupon(Coupon coupon) {
		super();
		this.id = String.valueOf(coupon.getId());
		this.title = coupon.getTitle();
		this.startDate = String.valueOf(coupon.getStartDate().getTime());
		this.endDate = String.valueOf(coupon.getEndDate().getTime());
		this.amount = String.valueOf(coupon.getAmount());
		this.type = coupon.getType().toString();
		this.message = coupon.getMessage();
		this.price = String.valueOf(coupon.getPrice());
		this.image = coupon.getImage();
	}
	
	public long getIdLong() {
		try
		{
			return Long.parseLong(id, 10) ;
		}
		catch(Exception e)
		{
			return -1L ;
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getStartDateLong() {
		return Long.parseLong(startDate,10);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public long getEndDateLong() {
		return Long.parseLong(endDate,10);
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAmountInt() {
		return Integer.parseInt(amount);
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPriceDouble() {
		return Double.parseDouble(price);
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Coupon convertToCoupon() {
		Coupon c = new Coupon(this.getIdLong(), this.getTitle(), new Date(this.getStartDateLong()), new Date(this.getEndDateLong()), this.getAmountInt(), CouponType.valueOf(type), message, this.getPriceDouble(), image);
		return c;
		
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getAmount() {
		return amount;
	}

	public String getPrice() {
		return price;
	}

	public static Collection<Coupon> convertToCoupons(Collection<WebCoupon> list)
	{
		Collection<Coupon> result = new ArrayList<>();
		for(WebCoupon c : list)
		{
			result.add( c.convertToCoupon() );
		}
		return result;
	}
	
	public static Collection<WebCoupon> convertToWebCoupons(Collection<Coupon> list)
	{
		Collection<WebCoupon> result = new ArrayList<>();
		for(Coupon c : list)
		{
			result.add( new WebCoupon( c ) );
		}
		return result;
	}
	
	
}
