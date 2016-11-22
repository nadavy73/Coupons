package JavaBeans;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	
	
	//
	//Attributes
	//
	
	@XmlElement private long custId;
	@XmlElement private String custName;
	@XmlElement private String custPassword;
	@XmlElement private Collection <Coupon> custCoupons;
	
	
	//
	//Constructors
	//
	
	public Customer()
	{
		
	}
	
	public Customer(long custId)
	{
		this.custId = custId;
	}
	
	public Customer(long custId,Collection<Coupon> coupons)
	{
		super();
		this.custId = custId;
		this.custCoupons = coupons;
	}
	public Customer(String custName)
	{
		this.custName = custName;
	}
	public Customer(long custId, String custName, String custPassWord, Collection<Coupon> coupons) 
	{
		super();
		this.custId = custId;
		this.custName = custName;
		this.custPassword = custPassWord;
		this.custCoupons = coupons;
	}
	
	public Customer(String custName, String custPassword) 
	{
		super();
		this.custName = custName;
		this.custPassword= custPassword;
	}
	
	public Customer(long custId, String custName, String custPassword)
	{
		super();
		this.custId = custId;
		this.custName = custName;
		this.custPassword = custPassword;
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

	public void setId(long custId) {
		this.custId = custId;
	}
	
	public long getId() {
		return custId;
	}

	public String getPassWord() {
		return custPassword;
	}
	
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public Collection<Coupon> getCoupons() {
		return custCoupons;
	}

	public Collection<Coupon> getCoupons (long custId){
		return custCoupons;
	}
	
	//
	//Functions
	//
	
	@Override
	public String toString() {
		return "\nCustomer: \nCustomer ID= " + custId + " \nCustomer Name= " +
				custName + " \nCustomer password= " + custPassword + 
				" \nCustomer coupons= " + custCoupons + "\n*************";
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custCoupons == null) ? 0 : custCoupons.hashCode());
		result = prime * result + (int) (custId ^ (custId >>> 32));
		result = prime * result + ((custName == null) ? 0 : custName.hashCode());
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
		if (custCoupons == null) {
			if (other.custCoupons != null)
				return false;
		} else if (!custCoupons.equals(other.custCoupons))
			return false;
		if (custId != other.custId)
			return false;
		if (custName == null) {
			if (other.custName != null)
				return false;
		} else if (!custName.equals(other.custName))
			return false;
		if (custPassword == null) {
			if (other.custPassword != null)
				return false;
		} else if (!custPassword.equals(other.custPassword))
			return false;
		return true;
	}

	

	
	
	
	
	
	
	
	
}
