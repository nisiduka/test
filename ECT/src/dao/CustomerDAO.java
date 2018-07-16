package dao;
import java.sql.*;
import dto.*;

public class CustomerDAO {
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

/*CUSTOMERを検索する*/
	public static Customer find(String cusId) {
		Customer customer=null;
		String sql="select * from tcustomer where cusId=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, cusId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				customer=new Customer();
				customer.setCusId(cusId);
				customer.setCusPassword(rs.getString("cusPassword"));
				customer.setAccId(rs.getString("accId"));
				/*customer.setCusName(rs.getString("cusName"));
				customer.setCusGender(rs.getString("cusGender"));
				customer.setCusBirth(rs.getString("cusBirth"));
				customer.setCusEmail(rs.getString("cusEmail"));
				*/
				}
		     }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return customer;
	}
	/*パスワードを更新する*/
	public static boolean update(Customer customer) {
		final String sql="update tcustomer set cusPassword=? where cusId=?";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql)){
					pstmt.setString(1,customer.getCusPassword());
					pstmt.setString(2,customer.getCusId());
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
