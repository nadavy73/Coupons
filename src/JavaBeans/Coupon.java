package JavaBeans;

import java.time.LocalDate;

public class Coupon {
	
	//
	//Attributes
	//
	
	private long id;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private CouponType type;
	private String message;
	private Double price;
	private String image;
	
	
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
	
	public Coupon(long id, String title, LocalDate startDate, LocalDate endDate, int amount, CouponType type, String message,
			Double price, String image) {
		super();
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

	
	
	//
	//Getters & Setters
	//
	
	
	public Coupon(String title, LocalDate i, LocalDate j, int amount, CouponType type,
			String message, double price, String image) {
		super();
		this.title = title;
		this.startDate = i;
		this.endDate = j;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		
	}

		
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	
}
