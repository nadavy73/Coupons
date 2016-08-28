package JavaBeans;

import java.util.*;

import Facades.CompanyFacade;

public class Company {

	
	//
	//Attributes
	//
	private long ID;
	private String compName;
	private String passWord;
	private String eMail;
	private Collection <Coupon> coupons= new HashSet<>();
	
	
	//
	//Constructors
	//
	
	public Company()
	{
		super();
	}
	
	public Company(String compName,String passWord,String eMail, Collection <Coupon> coupons)
	{

		this.compName=compName;
		this.passWord=passWord;
		this.eMail=eMail;
		this.coupons=coupons;
	}
	public Company(String compName,String passWord,String eMail)
	{

		this.compName=compName;
		this.passWord=passWord;
		this.eMail=eMail;
	}
	
	public Company(long ID, String compName,String passWord,String eMail, Collection <Coupon> coupons) {
		this.ID=ID;
		this.compName=compName;
		this.passWord=passWord;
		this.eMail=eMail;
		this.coupons=coupons;
		
	}


	public Company(String compName, String passWord) {
		this.compName = compName;
		this.passWord = passWord;
	}
	//
	//Getters & Setters
	//
	
	

	public long getId() {
		return ID;
	}
	
	public void setId(long Id) {
		ID = Id;
	}
	
	public String getCompName() {
		return compName;
	}
	
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String geteMail() {
		return eMail;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
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
		return "\nCompany: \nCompany ID=" + ID + "\nCompany Name=" + compName + "\nPassword=" + passWord 
				+ "\nEmail=" + eMail + "\nCoupons=" + coupons+ "\n***********" ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((compName == null) ? 0 : compName.hashCode());
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
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
		if (ID != other.ID)
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
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		return true;
	}


	

	
	
	
	
	
}
