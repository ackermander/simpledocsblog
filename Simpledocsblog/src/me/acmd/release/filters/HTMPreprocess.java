package me.acmd.release.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class HTMPreprocess
 */
@WebFilter("*.htm")
public class HTMPreprocess implements Filter {

	  private static final String DIV_PREPROCESS = "<script type=\"text/javascript\">window.onload = function(){var divd = document.getElementsByTagName('div')[0];divd.removeAttribute('style');}</script>";
    /**
     * Default constructor. 
     */
    public HTMPreprocess() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
	    response.getWriter().append("<script type=\"text/javascript\">window.onload = function(){var divd = document.getElementsByTagName('div')[0];divd.removeAttribute('style');}</script>");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
