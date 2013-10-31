package com.boss208.cgs;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CgsLogon extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		Cookie myCookie = new Cookie("ChocolateChip", "100");
//		myCookie.setMaxAge(Integer.MAX_VALUE);
//		resp.addCookie(myCookie);
		
		HttpSession session = req.getSession(true);
		session.setAttribute("fuckcgs", "givememoney");
		
		Enumeration e = req.getHeaderNames();
	    resp.setContentType("text/html");
	    java.io.PrintWriter out = resp.getWriter();
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
