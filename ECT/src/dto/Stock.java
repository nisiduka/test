package dto;
import java.io.Serializable;;
public class Stock implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	private String stockCode;
	private String stockName;
	private String stockMarket;
	private String stockCate;
	private int stockUnit;
	private String stockClass;
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockMarket() {
		return stockMarket;
	}
	public void setStockMarket(String stockMarket) {
		this.stockMarket = stockMarket;
	}
	public String getStockCate() {
		return stockCate;
	}
	public void setStockCate(String stockCate) {
		this.stockCate = stockCate;
	}
	public int getStockUnit() {
		return stockUnit;
	}
	public void setStockUnit(int stockUnit) {
		this.stockUnit = stockUnit;
	}
	public String getStockClass() {
		return stockClass;
	}
	public void setStockClass(String stockClass) {
		this.stockClass = stockClass;
	}
	
	

}
