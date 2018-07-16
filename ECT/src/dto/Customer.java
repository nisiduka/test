package dto;
import java.io.Serializable;
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L; 
	private String cusId;
	private String accId;
	private String cusPassword;
	private String cusName;
	private String cusGender;
	private String cusBirth;
	private String cusEmail;
	private String cusPhone;
	private String cusAddress;
	
	
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public String getCusPassword() {
		return cusPassword;
	}
	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusGender() {
		return cusGender;
	}
	public void setCusGender(String cusGender) {
		this.cusGender = cusGender;
	}
	public String getCusBirth() {
		return cusBirth;
	}
	public void setCusBirth(String cusBirth) {
		this.cusBirth = cusBirth;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	
    
}
