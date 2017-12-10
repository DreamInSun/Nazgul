package cyan.nazgul.dropwizard.filter;

import cyan.nazgul.dropwizard.config.CrossdomainConfig;
import cyan.nazgul.dropwizard.container.GlobalInstance;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DreamInSun on 2017/11/30.
 */
public class CrossDomainFilter implements Filter {

    /*========== Properties ==========*/
    protected CrossdomainConfig m_crossdomainConfig;

    /*========== Constructor ==========*/

    /*========== Handler ==========*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        m_crossdomainConfig = GlobalInstance.getConfiguration().getCrossdomainConfig();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin", m_crossdomainConfig.getAccessControlAllowOrigin());
        res.setHeader("Access-Control-Allow-Headers", m_crossdomainConfig.getAccessControlAllowHeaders());
        res.setHeader("Access-Control-Allow-Credentials", m_crossdomainConfig.getAccessControlAllowCredentials().toString());
        res.setHeader("Access-Control-Allow-Methods", m_crossdomainConfig.getAccessControlAllowMethods());
        res.setHeader("Access-Control-Max-Age", m_crossdomainConfig.getAccessControlMaxAge());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
