package servlet.filter;

import lombok.extern.log4j.Log4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@WebFilter({ "/login", "/logout", "/registration" })
public class CommonFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        log.info("New incoming request to " + ((HttpServletRequest) servletRequest).getServletPath());

        ((HttpServletResponse) servletResponse).addHeader("StackId", "product-app");

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("Request to " + ((HttpServletRequest) servletRequest).getServletPath() + " was successfully processed.");
    }

    @Override
    public void destroy()
    {

    }
}
