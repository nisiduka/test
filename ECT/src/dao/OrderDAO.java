package dao;
import dto.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
public class OrderDAO {
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

/*受付番号から注文を検索する*/
	public static Order find(String orderId) {
		Order order=null;
		String sql="select * from torder where orderId=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, orderId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				order=new Order();
				order.setOrderId(orderId);
				order.setCusId(rs.getString("cusId"));
				order.setStockCode(rs.getString("stockCode"));
				order.setStockName(rs.getString("stockName"));
				order.setStockUnit(rs.getInt("stockUnit"));
				order.setOrderNumber(rs.getInt("orderNumber"));
				order.setOrderAmount(rs.getFloat("orderAmount"));
				order.setOrderDate(rs.getString("oderDate"));
				order.setOrderType(rs.getString("orderType"));
				order.setOrderCondition(rs.getString("orderCondition"));
				order.setOrderPrice(rs.getFloat("orderPrice"));
				order.setOrderPtype(rs.getString("orderPtype"));
				order.setOrderState(rs.getString("orderState"));
				}
			
	    }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return order;
	}
/*顧客本人注文一覧を検索する*/
	public static List<Order> findall(String cusId) {
		List<Order> order_list=new ArrayList<>();
		Order order=null;
		String sql="select * from torder where cusId=?";
		try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD);PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, cusId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				order=new Order();
				order.setCusId(cusId);
				order.setOrderId(rs.getString("orderId"));
				order.setStockCode(rs.getString("stockCode"));
				order.setStockName(rs.getString("stockName"));
				order.setStockUnit(rs.getInt("stockUnit"));
				order.setOrderNumber(rs.getInt("orderNumber"));
				order.setOrderAmount(rs.getFloat("orderAmount"));
				order.setOrderDate(rs.getString("oderDate"));
				order.setOrderType(rs.getString("orderType"));
				order.setOrderCondition(rs.getString("orderCondition"));
				order.setOrderPrice(rs.getFloat("orderPrice"));
				order.setOrderPtype(rs.getString("orderPtype"));
				order.setOrderState(rs.getString("orderState"));
				order_list.add(order);
				}
			
	    }catch(SQLException e) {
	    	System.err.println("SQL"+sql);
	    	e.printStackTrace();
	    }
		return order_list;
	}
	
	/*注文状態を更新する*/
	public static boolean updatestate(Order order) {
		final String sql="update torder set orderState=? where orderId=?";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql)){
					pstmt.setString(1,order.getOrderState());
					pstmt.setString(2,order.getOrderId());
					
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
	/*注文単価を更新する*/
	public static boolean updateprice(Order order) {
		final String sql="update torder set orderPrice=? where orderId=?";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql)){
					pstmt.setFloat(1,order.getOrderPrice());
					pstmt.setString(2,order.getOrderId());
					
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
	/*注文番号を更新する*/
	public static boolean updateid(Order order) {
		final String sql="update torder set orderId=? where orderId=?";
		SimpleDateFormat date=new SimpleDateFormat("yyyyMMdd");
        String orderDate=date.format(date);
	    int n=8-order.getOrderId().length();
	    String zero="";
	    for(int i=0;i<n;i++) {
	    	zero=zero+"0";
	    }
        boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql)){
					pstmt.setString(1,order.getOrderId());
					pstmt.setString(2,date+zero+order.getOrderId());
					
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
/*新しい注文を追加して受付番号を発行する*/	
	public static boolean insert(Order order) {
		 
	    
	      final String sql="insert into order(cusId,stockCode,stockName,stockUnit,orderNumber,orderAmount,orderDate,orderType,orderCondition,orderPtype,orderState,orderPrice)"+"values(?,?,?,?,?,?,?,?,?,?,?,?)";
	      boolean result=false;
		  try(Connection con=DriverManager.getConnection(DB_URL,DB_USER,PASSWORD)){
			 
	          try(PreparedStatement pstmt=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
					pstmt.setString(1,order.getCusId());
					pstmt.setString(2,order.getStockCode());
					pstmt.setString(3,order.getStockName());
					pstmt.setFloat(4,order.getStockUnit());
					pstmt.setInt(5,order.getOrderNumber());
					pstmt.setFloat(6, order.getOrderAmount());
					pstmt.setString(7, order.getOrderDate());
					pstmt.setString(8, order.getOrderType());
					pstmt.setString(9, order.getOrderCondition());
					pstmt.setString(10, order.getOrderPtype());
					pstmt.setString(11, order.getOrderState());
					pstmt.setFloat(12, order.getOrderPrice());
					
					
				    if(pstmt.executeUpdate()==1) {
			             ResultSet rs=pstmt.getGeneratedKeys();
			             if(rs.next()) {
			            	 order.setOrderId(rs.getString(1));
			                 if(OrderDAO.updateid(order)) {
			                	 result=true;
			                 }
			             }
						
			         }
				}
		        
			}catch(SQLException e) {
			    System.err.println("SQL"+sql);
		    	e.printStackTrace();
			  }
	      return result;
		}

}
