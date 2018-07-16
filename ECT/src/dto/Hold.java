package dto;
import java.io.Serializable;;
public class Hold implements Serializable {
	private static final long serialVersionUID = 1L; 
    
	private String cusId;
	private String stockCode;
	private String stockName;
	private int holdNumber;
	private float holdAmount;
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public int getHoldNumber() {
		return holdNumber;
	}
	public void setHoldNumber(int holdNumber) {
		this.holdNumber = holdNumber;
	}
	public float getHoldAmount() {
		return holdAmount;
	}
	public void setHoldAmount(float holdAmount) {
		this.holdAmount = holdAmount;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	
	
}
