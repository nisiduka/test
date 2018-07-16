package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PriceDAO;
import dao.StockDAO;
import dto.Customer;
import dto.Price;
import dto.Stock;

/**
 * Servlet implementation class SearchNServlet
 */
@WebServlet("/SearchNServlet")
public class SearchStockNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStockNServlet() {
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
			response.getWriter().println("<script>alert (<%=errMessage%>);window.location.href='SearchStockN.html'</script>");
			return;
		}
	    
		Customer customer =(Customer)session.getAttribute("cusdto");
	             
	    if(customer==null) {
	    	 response.getWriter().println("<script>alert('セッションタイム切れました、再ログインしてください');window.location.href='Toppage1.html'</script>");
	    }else {
			    
	    	    List<Stock> stockdto=StockDAO.findname(request.getParameter("stockName"));
	            session.setAttribute("stockdto", stockdto);
	            RequestDispatcher rd=request.getRequestDispatcher("SearchResultN.jsp");
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
        String stockName=request.getParameter("stockName");
	    String errMessage;
		if(stockName.isEmpty()||stockName.length()>50) {
			errMessage="銘柄名に５０桁以内を入力してください";
			session.setAttribute("errMessage", errMessage);
		    return false;
		}else {
			return true;
			}
	}

	/*public static boolean isRight(String str) {
        for (int i = str.length(); --i >= 0;) {
            if ((!Character.isDigit(str.charAt(i))and(!)and(!)) {
                return false;
            }
        }
        return true;
    }*/

}
