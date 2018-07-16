package dto;
import java.io.Serializable;

public class Account implements Serializable {
   private static final long serialVersionUID = 1L; 
   
   private String accId;
   private float  accBalance;
   private float accDtotalbuy;
   private float accDtotalsell; 
   
   public String getAccId() {
	return accId;
   }
   public void setAccId(String accId) {
	 this.accId = accId;
   }
   public float getAccBalance() {
	 return accBalance;
   }
   public void setAccBalance(float accBalance) {
	 this.accBalance = accBalance;
   }
public float getAccDtotalbuy() {
	return accDtotalbuy;
}
public void setAccDtotalbuy(float accDtotalbuy) {
	this.accDtotalbuy = accDtotalbuy;
}
public float getAccDtotalsell() {
	return accDtotalsell;
}
public void setAccDtotalsell(float accDtotalsell) {
	this.accDtotalsell = accDtotalsell;
}
   

}
