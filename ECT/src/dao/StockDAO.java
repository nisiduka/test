package dao;
import dto.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class StockDAO {
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

/*銘柄コードから銘柄を検索する*/
	public static Stock findcode(String stockCode) {
		Stock stock=null;
		String sql="select * from tstock where stockCode=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, stockCode);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				stock=new Stock();
				stock.setStockCode(stockCode);
				stock.setStockName(rs.getString("stockName"));
				stock.setStockMarket(rs.getString("stockMarket"));
				stock.setStockCate(rs.getString("stockCate"));
				stock.setStockUnit(rs.getInt("stockUnit"));
				stock.setStockClass(rs.getString("stockClass"));
				}
			
	    }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return stock;
	}
/*銘柄名から銘柄を検索する*/
	public static List<Stock> findname(String stockName) {
		stockName="%"+stockName+"%";
		Stock stock=null;
		List<Stock> stock_list=new ArrayList<>();
		
		String sql="select * from tstock where stockName like ?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, stockName);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				stock=new Stock();
				stock.setStockCode(rs.getString("stockCode"));
				stock.setStockName(rs.getString("stockName"));
				stock.setStockMarket(rs.getString("stockMarket"));
				stock.setStockCate(rs.getString("stockCate"));
				stock.setStockUnit(rs.getInt("stockUnit"));
				stock.setStockClass(rs.getString("stockClass"));
				stock_list.add(stock);
			}
			
	    }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return stock_list;
		
	}

}
