package dao;
import dto.*;
import java.sql.*;

public class AccountDAO {
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
	
/*ACCOUNTを検索する*/
	public static Account find(String accId) {
		Account account=null;
		String sql="select * from taccount where accId=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, accId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				account=new Account();
				account.setAccId(accId);
				account.setAccBalance(rs.getFloat("accBalance"));
				account.setAccDtotalbuy(rs.getFloat("accDtotalbuy"));
				account.setAccDtotalsell(rs.getFloat("accDtotalsell"));
				}
			
	    }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return account;
	}
	/*取引用口座を更新する*/
	public static boolean update(Account account) {
		final String sql="update taccount set accBalance=?,accDtotalbuy=?,accDtotalsell=? where accId=?";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql)){
					pstmt.setFloat(1, account.getAccBalance());
					pstmt.setFloat(2, account.getAccDtotalbuy());
					pstmt.setFloat(3, account.getAccDtotalsell());
					pstmt.setString(4, account.getAccId());
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
	

