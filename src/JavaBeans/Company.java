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
	@XmlElement private String name;
	@XmlElement private String password;
	@XmlElement private String email;
	@XmlElement private Collection <Coupon> coupons= new HashSet<>();
	
	
	//
	//Constructors
	//
	
	public Company()
	{
		
	}
	
	public Company(String name,String password,String email, Collection <Coupon> coupons)
	{
		
		this.name=name;
		this.password=password;
		this.email=email;
		this.coupons=coupons;
	}
	public Company(String name,String password,String email)
	{
		
		this.name=name;
		this.password=password;
		this.email=email;
	}
	
	
	public Company(long id,String name,String password,String email)
	{
		
		this.id=id;
		this.name=name;
		this.password=password;
		this.email=email;
	}
	public Company(long id, String name,String password,String email, Collection <Coupon> coupons) {
		
		this.id=id;
		this.name=name;
		this.password=password;
		this.email=email;
		this.coupons=coupons;
		
	}


	public Company(String name, String password) {
		this.name = name;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		return "\nCompany: \nCompany ID=" + id + "\nCompany Name=" + name + "\nPassword=" + password 
				+ "\nEmail=" + email + "\nCoupons=" + coupons+ "\n***********" ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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