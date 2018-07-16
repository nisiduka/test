package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import dto.*;


/**
 * Servlet implementation class Buy2Servlet
 */
@WebServlet("/Buy2Servlet")
public class Buy2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
/*入力チェック*/
		HttpSession session=request.getSession();
		
		if (!validate(request)){
			String errMessage=(String)session.getAttribute("errMessage");
			response.getWriter().println("<script>alert (<%=errMessage%>);window.location.href='Buy1.jsp'</script>");
			return;
		}
	    
		Customer customer =(Customer)session.getAttribute("cusdto");
	             
	    if(customer==null) {
	    	 response.getWriter().println("<script>alert('セッションタイム切れました、再ログインしてください');window.location.href='Toppage1.html'</script>");
	    }else {
			    Order orderdto=(Order)session.getAttribute("orderdto");
	    	    int orderNumber=0;
	    	    float orderPrice=PriceDAO.find(orderdto.getStockCode()).getPriceCurrent();
	    	    if(orderPrice!=orderdto.getOrderPrice()) {
	    	    	Order orderdto1=new Order();
				    
				    orderdto1.setCusId(customer.getCusId());
				    orderdto1.setStockCode(orderdto.getStockCode());
				    orderdto1.setStockName(orderdto.getStockName());
				    orderdto1.setStockUnit(orderdto.getStockUnit());
				    orderdto1.setOrderType("B");
	    	    	orderdto1.setOrderPrice(orderPrice); 
	    	    	session.setAttribute("orderdto", orderdto1);
	    	    	response.getWriter().println("<script>alert('当時単価変わりました、注文情報を再入力してください');window.location.href='Buy1.jsp'</script>");
	    	    }else {
	    	           try {
				             orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
				       } catch (NumberFormatException e) {
				             e.printStackTrace();
				       }
				       
	    	           if(request.getParameter("orderPtype").equals("Y")) {
	    	             try {
					         orderPrice = Float.parseFloat(request.getParameter("orderPriceN"));
					         orderdto.setOrderPrice(orderPrice);
				         } catch (NumberFormatException e) {
				               e.printStackTrace();
				         }
				       }
				       
	    	         
	    	           SimpleDateFormat date=new SimpleDateFormat("yyyyMMdd");
	    	           String orderDate=date.format(date);
				       float orderAmount=orderdto.getOrderPrice()*orderNumber;
	    	           if(AccountDAO.find(customer.getAccId()).getAccDtotalbuy()>30000000) {
	    	        	   Order orderdto1=new Order();
	   				       orderdto1.setCusId(customer.getCusId());
	   				       orderdto1.setStockCode(orderdto.getStockCode());
	   				       orderdto1.setStockName(orderdto.getStockName());
	   				       orderdto1.setStockUnit(orderdto.getStockUnit());
	   				       orderdto1.setOrderType("B");
	   	    	    	   orderdto1.setOrderPrice(PriceDAO.find(orderdto1.getStockCode()).getPriceCurrent()); 
	   	    	    	   session.setAttribute("orderdto", orderdto1);
	    	        	   response.getWriter().println("<script>alert('当日購入累計金額が三千万円超えました');window.location.href='Buy1.jsp'</script>");
	    	           }else if(AccountDAO.find(customer.getAccId()).getAccBalance()<orderAmount) {
	    	        	   
	    	        	   
	    	        	   Order orderdto1=new Order();
	   				       orderdto1.setCusId(customer.getCusId());
	   				       orderdto1.setStockCode(orderdto.getStockCode());
	   				       orderdto1.setStockName(orderdto.getStockName());
	   				       orderdto1.setStockUnit(orderdto.getStockUnit());
	   				       orderdto1.setOrderType("B");
	   	    	    	   orderdto1.setOrderPrice(PriceDAO.find(orderdto1.getStockCode()).getPriceCurrent()); 
	   	    	    	   session.setAttribute("orderdto", orderdto1);
	    	        	   response.getWriter().println("<script>alert('残高不足です,注文情報を再入力してください');window.location.href='Buy1.jsp'</script>");
	    	           }else {
				       
				              
	    	                   orderdto.setOrderCondition(request.getParameter("orderCondition"));
	                           orderdto.setOrderPtype(request.getParameter("orderPtype"));
	                           orderdto.setOrderNumber(orderNumber);
	                           orderdto.setOrderAmount(orderAmount);
	                           orderdto.setOrderDate(orderDate);
	            
	                           session.setAttribute("orderdto", orderdto);
	                           RequestDispatcher rd=request.getRequestDispatcher("Buy3.jsp");
			                   rd.forward(request,response);
	                   }
	         }
	    }
    }
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*入力チェックメソット*/	
	private boolean validate(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		session.removeAttribute("errMessage");
        String orderNumber=request.getParameter("orderNumber");
        String orderPriceN=request.getParameter("orderPriceN");
        String orderPtype=request.getParameter("orderPtype");
	    String errMessage;
	    int orderNumberN=0;
	    float orderPriceNF=0;
		if(orderNumber.isEmpty()||!isNumeric(orderNumber)) {
			errMessage="注文数量に半角整数を入力してください";
			session.setAttribute("errMessage", errMessage);
		    return false;
		}else if((orderPriceN.isEmpty()||!isFloat(orderPriceN))&&(orderPtype.equals("Y"))){
			errMessage="指定価格に半角数字を入力してください";
			session.setAttribute("errMessage", errMessage);
		    return false;
			}else {
				try {
				    orderNumberN = Integer.parseInt(orderNumber);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				try {
					orderPriceNF = Float.parseFloat(orderPriceN);
				} catch (NumberFormatException e) {
				    e.printStackTrace();
				}
				if(orderNumberN>30000000) {
					errMessage="注文数量に30000000以内の整数を入力してください";
					session.setAttribute("errMessage", errMessage);
				    return false;
				}else if(orderPriceNF>30000000) {
					errMessage="指定価格に30000000以内の数字を入力してください";
					session.setAttribute("errMessage", errMessage);
				    return false;
				}
			}
		return true;
	}

	public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	public static boolean isFloat(String str) {
        boolean x=false;
		for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                if((!x)||(str.charAt(i)=='.')){
            	  x=true;
            	  }else {
            		  return false;
            	  }
            }
            
       }
	   return true;
   }
}
