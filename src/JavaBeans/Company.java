package JavaBeans;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Company {

	
	//
	//Attributes
	//
	@XmlElement private long id;
	@XmlElement private String compName;
	@XmlElement private String password;
	@XmlElement private String email;
	@XmlElement private Collection <Coupon> coupons= new HashSet<>();
	
	
	//
	//Constructors
	//
	
	public Company()
	{
		super();
	}
	
	public Company(String compName,String passWord,String email, Collection <Coupon> coupons)
	{
		super();
		this.compName=compName;
		this.password=passWord;
		this.email=email;
		this.coupons=coupons;
	}
	public Company(String compName,String passWord,String email)
	{
		super();
		this.compName=compName;
		this.password=passWord;
		this.email=email;
	}
	
	
	public Company(long id,String compName,String passWord,String email)
	{
		
		this.id=id;
		this.compName=compName;
		this.password=passWord;
		this.email=email;
	}
	public Company(long id, String compName,String passWord,String email, Collection <Coupon> coupons) {
		
		this.id=id;
		this.compName=compName;
		this.password=password;
		this.email=email;
		this.coupons=coupons;
		
	}


	public Company(String compName, String password) {
		this.compName = compName;
		this.password = password;
	}
	//
	//Getters & Setters
	//
	
	

	public long getId() {
		return id;
	}
	
	public void setId(long Id) {
		id = Id;
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
	
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}


	//
	//Functions
	//
	
	@Override

	public String toString() {
		return "\nCompany: \nCompany ID=" + id + "\nCompany Name=" + compName + "\nPassword=" + password 
				+ "\nEmail=" + email + "\nCoupons=" + coupons+ "\n***********" ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((compName == null) ? 0 : compName.hashCode());
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (compName == null) {
			if (other.compName != null)
				return false;
		} else if (!compName.equals(other.compName))
			return false;
		if (coupons == null) {
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	

	
	
	
	
	
}