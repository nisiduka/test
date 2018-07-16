package controller;
import dto.*;
import dao.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;


import javax.servlet.http.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
/*入力チェック*/
		HttpSession session=request.getSession();
		if (!validate(request)){
			String errMessage=(String)session.getAttribute("errMessage");
			response.getWriter().println("<script>alert (<%=errMessage%>);window.location.href='Login.html'</script>");
			return;
		}
	    
		Customer customer =(Customer)session.getAttribute("cusdto");
	             
	    if(customer==null) {
			    customer=new Customer();
			    customer.setCusId(request.getParameter("cusId"));
	            customer.setCusPassword(request.getParameter("cusPassword"));
		        Customer cusdto=CustomerDAO.find(customer.getCusId());
		       if(cusdto==null) {
		    	   response.getWriter().println("<script>alert('顧客IDが存在しません');window.location.href='Login.html'</script>");
		    	   
		        }else if(!customer.getCusPassword().equals(cusdto.getCusPassword())){
	    	
		        	response.getWriter().println("<script>alert('ログインパスワードが間違いました');window.location.href='Login.html'</script>");
	                    }else {
	                    	RequestDispatcher rd=request.getRequestDispatcher("Toppage2.jsp");
			              session.setAttribute("cusdto", cusdto);
			              session.setMaxInactiveInterval(600);
	    	              rd.forward(request,response);
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
        String cusId=request.getParameter("cusId");
		String cusPassword=request.getParameter("cusPassword");
	    String errMessage;
		if(cusId.isEmpty()||cusId.length()!=8||!isNumeric(cusId)) {
			errMessage="顧客IDに八桁の半角数字を入力してください";
			session.setAttribute("errMessage", errMessage);
		    return false;
		}else if(cusPassword.isEmpty()||cusPassword.length()<8||cusPassword.length()>16||!isNumericorLetter(cusPassword)) {
			errMessage="顧客パスワードに八桁から十六桁までの半角英数字を入力してください";
			session.setAttribute("errMessage", errMessage);
			return false;
		}else {return true;}
	}

	public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumericorLetter(String str) {
        for (int i = str.length(); --i >= 0;) {
            if ((!Character.isDigit(str.charAt(i)))&&(!Character.isLetter(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

}
