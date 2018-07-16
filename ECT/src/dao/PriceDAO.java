package dao;
import dto.*;
import java.sql.*;
public class PriceDAO {
	static final String JDBC_DRIVER="org.postgresql.Driver";  
	static {
		try {
			Class.forName(JDBC_DRIVER);
		}catch(ClassNotFoundException e) {
			System.err.println("driver lose");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	static final String DB_URL="jdbc:postgresql://localhost:5432/ectdb";
	static final String DB_USER="postgres";
	static final String PASSWORD="password";

/*銘柄コードから株価を検索する*/
	public static Price find(String stockCode) {
		Price price=null;
		String sql="select * from tprice where stockCode=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, stockCode);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				price=new Price();
				price.setStockCode(stockCode);
				price.setPriceCurrent(rs.getFloat("priceCurrent"));
				price.setPriceStart(rs.getFloat("priceStart"));
				price.setPriceHighest(rs.getFloat("priceHighest"));
				price.setPriceLowest(rs.getFloat("priceLowest"));
				price.setPriceSell(rs.getInt("priceSell"));
				price.setPriceBuy(rs.getInt("priceBuy"));
				price.setPriceYhighest(rs.getFloat("priceYhighest"));
				price.setPriceYlowest(rs.getFloat("priceYlowest"));
				price.setPricePast1(rs.getFloat("pricePast1"));
				price.setPricePast2(rs.getFloat("pricePast2"));
				price.setPricePast3(rs.getFloat("pricePast3"));
				price.setPricePast4(rs.getFloat("pricePast4"));
				price.setPricePast5(rs.getFloat("pricePast5"));
				price.setPricePast6(rs.getFloat("pricePast6"));
				price.setPricePast7(rs.getFloat("pricePast7"));
				}
			
	    }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return price;
	}
}
