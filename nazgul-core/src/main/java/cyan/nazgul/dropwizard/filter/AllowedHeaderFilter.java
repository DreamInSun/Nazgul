package cyan.nazgul.dropwizard.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by DreamInSun on 2017/11/30.
 */
@Provider
public class AllowedHeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Headers", "*");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
