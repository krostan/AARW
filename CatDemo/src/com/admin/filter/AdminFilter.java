package com.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
	
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String referer = httpRequest.getHeader("Referer");
		
		if(referer != null && referer.endsWith("/CatDemo/role/admin.jsp")) {
			httpRequest.setAttribute("adminUrl", "/CatDemo/role/admin.jsp");
		}
		
		chain.doFilter(request, response);
	}

}
