package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterConnexion
 */
@WebFilter(
				urlPatterns="/restreint/*",
				dispatcherTypes=  {
								DispatcherType.REQUEST,
								DispatcherType.INCLUDE,
								DispatcherType.FORWARD,
								DispatcherType.ERROR
						
						
				}
		
		
						)
public class FilterConnexion implements Filter {

    /**
     * Default constructor. 
     */
    public FilterConnexion() {
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
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		if (session.getAttribute("sessionUtilisateur") == null) {
			System.out.println("ne laisse pas passer");
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			
			// pass the request along the filter chain
			
			
		} else {
			System.out.println("laisse passer");
			chain.doFilter(request, response);
			
			
		}

		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
