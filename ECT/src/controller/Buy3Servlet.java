package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.OrderDAO;
import dao.PriceDAO;
import dto.Customer;
import dto.Order;

/**
 * Servlet implementation class Buy3Servlet
 */
@WebServlet("/Buy3Servlet")
public class Buy3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy3Servlet() {
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
		
		
		Customer customer =(Customer)session.getAttribute("cusdto");
	             
	    if(customer==null) {
	    	 response.getWriter().println("<script>alert('セッションタイム切れました、再ログインしてください');window.location.href='Toppage1.html'</script>");
	    }else {
			    Order orderdto=(Order)session.getAttribute("orderdto");
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
	    	           }else if(AccountDAO.find(customer.getAccId()).getAccBalance()<orderdto.getOrderAmount()) {
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
				       
				              if(OrderDAO.insert(orderdto)) {
	    	                   session.setAttribute("orderdto", orderdto);
	                           RequestDispatcher rd=request.getRequestDispatcher("Buy3.jsp");
			                   rd.forward(request,response);
			                   session.removeAttribute("orderdto");
				              }else {
				            	  session.removeAttribute("orderdto");
				            	  response.getWriter().println("<script>alert('注文失敗しました');window.location.href='Mypage.html'</script>");
				              }
	                   }
	         }
	    }
    }
	

}
