package dto;
import java.io.Serializable;;

public class Price implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String stockCode;
	private float priceCurrent;
	private float priceStart;
	private float priceHighest;
	private float priceLowest;
	private int priceSell;
	private int priceBuy;
	private float priceYhighest;
	private float priceYlowest;
	private float pricePast1;
	private float pricePast2;
	private float pricePast3;
	private float pricePast4;
	private float pricePast5;
	private float pricePast6;
	private float pricePast7;
	
	
	public float getPricePast1() {
		return pricePast1;
	}
	public void setPricePast1(float pricePast1) {
		this.pricePast1 = pricePast1;
	}
	public float getPricePast2() {
		return pricePast2;
	}
	public void setPricePast2(float pricePast2) {
		this.pricePast2 = pricePast2;
	}
	public float getPricePast3() {
		return pricePast3;
	}
	public void setPricePast3(float pricePast3) {
		this.pricePast3 = pricePast3;
	}
	public float getPricePast4() {
		return pricePast4;
	}
	public void setPricePast4(float pricePast4) {
		this.pricePast4 = pricePast4;
	}
	public float getPricePast5() {
		return pricePast5;
	}
	public void setPricePast5(float pricePast5) {
		this.pricePast5 = pricePast5;
	}
	public float getPricePast6() {
		return pricePast6;
	}
	public void setPricePast6(float pricePast6) {
		this.pricePast6 = pricePast6;
	}
	public float getPricePast7() {
		return pricePast7;
	}
	public void setPricePast7(float pricePast7) {
		this.pricePast7 = pricePast7;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public int getPriceBuy() {
		return priceBuy;
	}
	public void setPriceBuy(int priceBuy) {
		this.priceBuy = priceBuy;
	}
	public void setPriceSell(int priceSell) {
		this.priceSell = priceSell;
	}
	public float getPriceCurrent() {
		return priceCurrent;
	}
	public void setPriceCurrent(float priceCurrent) {
		this.priceCurrent = priceCurrent;
	}
	public float getPriceStart() {
		return priceStart;
	}
	public void setPriceStart(float priceStart) {
		this.priceStart = priceStart;
	}
	public float getPriceHighest() {
		return priceHighest;
	}
	public void setPriceHighest(float priceHighest) {
		this.priceHighest = priceHighest;
	}
	public float getPriceLowest() {
		return priceLowest;
	}
	public void setPriceLowest(float priceLowest) {
		this.priceLowest = priceLowest;
	}
	public float getPriceSell() {
		return priceSell;
	}
	
	public float getPriceYhighest() {
		return priceYhighest;
	}
	public void setPriceYhighest(float priceYhighest) {
		this.priceYhighest = priceYhighest;
	}
	public float getPriceYlowest() {
		return priceYlowest;
	}
	public void setPriceYlowest(float priceYlowest) {
		this.priceYlowest = priceYlowest;
	}
	
	

}
