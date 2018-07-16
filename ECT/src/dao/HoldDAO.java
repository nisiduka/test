package dao;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import dto.*;

public class HoldDAO {
	/*データベースの連携*/	
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

/*保有商品を検索する*/
	public static List<Hold> findall(String cusId) {
		List<Hold> hold_list=new ArrayList<>();
		Hold hold=null;
		String sql="select * from thold where cusId=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, cusId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				  hold=new Hold();
				  hold.setCusId(cusId);
				  hold.setHoldAmount(rs.getFloat("holdAmount"));
				  hold.setHoldNumber(rs.getInt("holdNumber"));
				  hold.setStockCode(rs.getString("stockCode"));
				  hold.setStockName(rs.getString("stockName"));
				  hold_list.add(hold);
				}
			 }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return hold_list;
	}
	
	/*保有商品を更新する*/
	public static boolean update(Hold hold) {
		final String sql="update thold set holdNumber=?,holdAmount=? where cusId=? and stockCode=?";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql)){
					pstmt.setInt(1, hold.getHoldNumber());
					pstmt.setFloat(2, hold.getHoldAmount());
					pstmt.setString(3, hold.getCusId());
					pstmt.setString(4, hold.getStockCode());
					
		            if(pstmt.executeUpdate()==1) {
			            result=true;
			         }
				}
		        
			}catch(SQLException e) {
			    System.err.println("SQL"+sql);
		    	e.printStackTrace();
			  }
	      return result;
	}
	
	/*保有商品を追加する*/
	public static boolean insert(Hold hold) {
		 
	    
	      final String sql="insert into hold(cusId,stockCode,stockName,holdNumber,holdAmount)"+"values(?,?,?,?,?)";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
					pstmt.setString(1, hold.getCusId());
					pstmt.setString(2, hold.getStockCode());
					pstmt.setString(3, hold.getStockName());
					pstmt.setInt(4, hold.getHoldNumber());
					pstmt.setFloat(5, hold.getHoldAmount());
					
				    if(pstmt.executeUpdate()==1) {
						result=true;
			         }
				}
		        
			}catch(SQLException e) {
			    System.err.println("SQL"+sql);
		    	e.printStackTrace();
			  }
	      return result;
		}

}
