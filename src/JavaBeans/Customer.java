package JavaBeans;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	
	
	//
	//Attributes
	//
	
	@XmlElement private long id;
	@XmlElement private String custName;
	@XmlElement private String password;
	@XmlElement private Collection <Coupon> coupons;
	
	
	//
	//Constructors
	//
	
	public Customer()
	{
		
	}
	
	public Customer(long id)
	{
		this.id = id;
	}
	
	public Customer(long id,Collection<Coupon> coupons)
	{
		super();
		this.id = id;
		this.coupons = coupons;
	}
	public Customer(String custName)
	{
		this.custName = custName;
	}
	public Customer(long id, String custName, String password, Collection<Coupon> coupons) 
	{
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}
	
	public Customer(String custName, String password) 
	{
		super();
		this.custName = custName;
		this.password= password;
	}
	
	public Customer(long id, String custName, String password)
	{
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	//
	//Getters & Setters
	//
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getPassWord() {
		return password;
	}
	
	public void setpassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public Collection<Coupon> getCoupons (long id){
		return coupons;
	}
	
	//
	//Functions
	//
	
	@Override
	public String toString() {
		return "\nCustomer: \nCustomer ID= " + id + " \nCustomer Name= " +
				custName + " \nCustomer password= " + password + 
				" \nCustomer coupons= " + coupons + "\n*************";
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((custName == null) ? 0 : custName.hashCode());
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
		Customer other = (Customer) obj;
		if (coupons == null) {
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		if (id != other.id)
			return false;
		if (custName == null) {
			if (other.custName != null)
				return false;
		} else if (!custName.equals(other.custName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	

	
	
	
	
	
	
	
	
}
