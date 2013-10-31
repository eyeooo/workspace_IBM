package com.boss208.cgs;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CgsExam extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		java.io.PrintWriter out = resp.getWriter();

		if(req.getSession(false)==null){
			out.println("<html><body>no the session Q.Q</body></html>");
			return;
		}
/*		Cookie[] cookies = req.getCookies();
		if (cookies==null) {
			out.println("<html><body>null cookie =.=</body></html>");
			return;
		}
		
		
		boolean cookieFound = false;
		int cookieOrder = 0;
		for(int i=0; i < cookies.length; i++) {
		  Cookie c = cookies[i];
		  if (c.getName().equals("ChocolateChip")) {
			  cookieOrder = Integer.parseInt(c.getValue());
			  System.out.println("cookieOrder: "+cookieOrder);
		    cookieFound = true;
		    break;
		  }
		}

		if (!cookieFound) {
			out.println("<html><body>no match cookie>.<</body></html>");
			return;
		}*/
		
		Enumeration e = req.getHeaderNames();
	    out.println("<html><head><title>Request Header View</title></head><body>");
	    out.println("<h2>Request Headers</h2>");
	    String header = null;

	    while (e.hasMoreElements()) {
	      header = (String) e.nextElement();
	      out.println("<strong>" + header + "</strong>" + ": "
	          + req.getHeader(header) + "<br>");
	      System.out.println(header+": "+ req.getHeader(header));
	    }
	    
	    out.println("</body></html>");
	    
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}
