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
 * Servlet implementation class ClickOrderIdServlet
 */
@WebServlet("/ClickOrderIdServlet")
public class ClickOrderIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickOrderIdServlet() {
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
			    
			    Order orderdto=OrderDAO.find(request.getParameter("orderId"));
			    session.setAttribute("orderdto",orderdto);
	            RequestDispatcher rd=request.getRequestDispatcher("OrderD.jsp");
			    rd.forward(request,response);
			    session.removeAttribute("orderdto");
	    }
	}

}
