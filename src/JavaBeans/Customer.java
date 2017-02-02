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
	@XmlElement private String name;
	@XmlElement private String custPassword;
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
	public Customer(String name)
	{
		this.name = name;
	}
	public Customer(long id, String name, String password, Collection<Coupon> coupons) 
	{
		super();
		this.id = id;
		this.name = name;
		this.custPassword = password;
		this.coupons = coupons;
	}
	
	public Customer(String name, String password) 
	{
		super();
		this.name = name;
		this.custPassword= password;
	}
	
	public Customer(long id, String name, String password)
	{
		super();
		this.id = id;
		this.name = name;
		this.custPassword = password;
	}
	//
	//Getters & Setters
	//
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getPassWord() {
		return custPassword;
	}
	
	public void setpassword(String password) {
		this.custPassword = password;
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
				name + " \nCustomer password= " + custPassword + 
				" \nCustomer coupons= " + coupons + "\n*************";
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((custPassword == null) ? 0 : custPassword.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (custPassword == null) {
			if (other.custPassword != null)
				return false;
		} else if (!custPassword.equals(other.custPassword))
			return false;
		return true;
	}

	

	
	
	
	
	
	
	
	
}
