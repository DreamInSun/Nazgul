package cyan.nazgul.dropwizard.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DreamInSun on 2018/2/15.
 */
@PreMatching
@Provider
@Priority(Priorities.USER)
public class NazProductLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    public static final Logger g_logger = LoggerFactory.getLogger(NazProductLoggingFilter.class);
    /*========== Properties ==========*/
    @Context
    private HttpServletRequest httpServletRequest;

    @Context
    private HttpServletResponse httpServletResponse;

    /*========== Implement : ContainerRequestFilter==========*/
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        StringBuilder sb = new StringBuilder("\r\n/*==================== Request " + requestContext.hashCode() + " ====================*/\r\n");
        sb.append("[URI]\t\t" + requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri() + "\r\n");
        //sb.append("[Headers]\t" + JSON.toJSONString(requestContext.getHeaders()) + "\r\n");
        sb.append("[MediaType]\t" + requestContext.getMediaType() + "\r\n");
        //sb.append("[Entity]\t" + inputStreamToString(requestContext.getEntityStream()));
        sb.append("\r\n/*==================== End Request ====================*/\r\n\r\n");
        g_logger.info(sb.toString());
    }

    /*========== Implement : ContainerResponseFilter==========*/
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        StringBuilder sb = new StringBuilder("\r\n\r\n/*==================== Response " + requestContext.hashCode() + " ====================*/\r\n");
        sb.append("[URI]\t\t" + requestContext.getUriInfo().getRequestUri() + "\r\n");
        sb.append("[State]\t\t" + responseContext.getStatus() + " " + responseContext.getStatusInfo() + "\r\n");
        sb.append("[MediaType]\t" + requestContext.getMediaType() + "\r\n");
        if (requestContext.getMediaType() != null) {
            switch (requestContext.getMediaType().getType()) {
                case MediaType.APPLICATION_JSON:
                case MediaType.APPLICATION_FORM_URLENCODED:
                case MediaType.APPLICATION_XML:
                case MediaType.APPLICATION_ATOM_XML:
                    sb.append("[Entity]\t" + JSON.toJSONString(responseContext.getEntity()));
                    break;
            }
        }
        sb.append("\r\n/*==================== End Response  ====================*/\r\n");
        g_logger.info(sb.toString());
    }


    public String inputStreamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}