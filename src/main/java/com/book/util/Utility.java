package com.book.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utility 
{
	public static void setUrl(HttpSession ses,HttpServletRequest req)
	{
		if(req.getQueryString()!=null)
			ses.setAttribute("url",req.getRequestURI()+"?"+req.getQueryString());
		else
			ses.setAttribute("url",req.getRequestURI());
	}
}
