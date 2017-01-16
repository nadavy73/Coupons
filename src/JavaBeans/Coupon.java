package JavaBeans;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Coupon {
	
	//
	//Attributes
	//
	
	@XmlElement private long id;
	@XmlElement private String title;
	@XmlElement @XmlJavaTypeAdapter(value = LocalDateAdapter.class) private LocalDate startDate;
	@XmlElement @XmlJavaTypeAdapter(value = LocalDateAdapter.class) private LocalDate endDate;
	@XmlElement private int amount;
	@XmlElement private CouponType type;
	@XmlElement private String message;
	@XmlElement private double price;
	@XmlElement private String image;
	
	
	//
	//Constructors
	//
	public Coupon()
	{
		
	}
	public Coupon(String title)
	{
		this.title=title;

	}
	
	public Coupon(long id, String title)
	{
		this.id=id;
		this.title=title;
	}
	
	public Coupon(long id, String title, LocalDate startDate, LocalDate endDate, int amount, CouponType type, 
			String message, double price, String image) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	
	public Coupon(String title, LocalDate startDate, LocalDate endDate, int amount, CouponType type,
			String message, double price, String image) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		
	}

	
	//
	//Getters & Setters
	//
		
	//
	//Functions
	//
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "\nCoupon \nId=" + id + ", \nTitle=" + title + ", \nStart Date=" + startDate + ""
				+ ", \nEnd Date=" + endDate + ", \nAmount=" + amount + ", \nType=" + type + ""
				+ ", \nMessage=" + message + ", \nPrice=" + price + ", \nImage="
				+ image;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (amount != other.amount)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
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
		else if (price != other.price)
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
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	
}
