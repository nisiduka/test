package controller;

import java.io.IOException;

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
 * Servlet implementation class SearchCServlet
 */
@WebServlet("/SearchCServlet")
public class SearchStockCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStockCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
			response.getWriter().println("<script>alert (<%=errMessage%>);window.location.href='SearchStockC.html'</script>");
			return;
		}
	    
		Customer customer =(Customer)session.getAttribute("cusdto");
	             
	    if(customer==null) {
	    	 response.getWriter().println("<script>alert('セッションタイム切れました、再ログインしてください');window.location.href='Toppage1.html'</script>");
	    }else {
			    
			    Stock stock=new Stock();
			    stock.setStockCode(request.getParameter("stockCode"));
	            Stock stockdto=StockDAO.findcode(stock.getStockCode());
	            if(stockdto!=null) {
	                Price pricedto=PriceDAO.find(stock.getStockCode());
	                session.setAttribute("pricedto",pricedto);
	            }
	            session.setAttribute("stockdto", stockdto);
	            RequestDispatcher rd=request.getRequestDispatcher("SearchResultD.jsp");
			    rd.forward(request,response);
	           }
    }
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*入力チェックメソット*/	
	private boolean validate(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		session.removeAttribute("errMessage");
        String stockCode=request.getParameter("stockCode");
	    String errMessage;
		if(stockCode.isEmpty()||stockCode.length()!=4||!isNumeric(stockCode)) {
			errMessage="銘柄コードに四桁の半角数字を入力してください";
			session.setAttribute("errMessage", errMessage);
		    return false;
		}else {
			return true;
			}
	}

	public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
}


