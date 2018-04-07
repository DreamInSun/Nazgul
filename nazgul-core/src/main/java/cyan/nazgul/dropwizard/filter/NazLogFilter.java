package cyan.nazgul.dropwizard.filter;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;

@Provider
public class NazLogFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger g_logger = LoggerFactory.getLogger(NazLogFilter.class);
    /*========== Properties ==========*/
    @Context
    private ResourceInfo resourceInfo;

    /*========== Implement ==========*/
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //Note down the start request time...we will use to calculate the total
        //execution time
        MDC.put("start-time", String.valueOf(System.currentTimeMillis()));

        g_logger.debug("Entering in Resource : /{} ", requestContext.getUriInfo().getPath());
        g_logger.debug("Method Name : {} ", resourceInfo.getResourceMethod().getName());
        g_logger.debug("Class : {} ", resourceInfo.getResourceClass().getCanonicalName());
        logQueryParameters(requestContext);
        logMethodAnnotations();
        logRequestHeader(requestContext);
        //g_logger entity stream...
        String entity = readEntityStream(requestContext);
        if (null != entity && entity.trim().length() > 0) {
            g_logger.debug("Entity Stream : {}", entity);
        }
    }

    private void logQueryParameters(ContainerRequestContext requestContext) {
        Iterator<String> iterator = requestContext.getUriInfo().getPathParameters().keySet().iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            List obj = requestContext.getUriInfo().getPathParameters().get(name);
            String value = null;
            if (null != obj && obj.size() > 0) {
                value = (String) obj.get(0);
            }
            g_logger.debug("Query Parameter Name: {}, Value :{}", name, value);
        }
    }

    private void logMethodAnnotations() {
        Annotation[] annotations = resourceInfo.getResourceMethod().getDeclaredAnnotations();
        if (annotations != null && annotations.length > 0) {
            g_logger.debug("----Start Annotations of resource ----");
            for (Annotation annotation : annotations) {
                g_logger.debug(annotation.toString());
            }
            g_logger.debug("----End Annotations of resource----");
        }
    }

    private void logRequestHeader(ContainerRequestContext requestContext) {
        Iterator<String> iterator;
        g_logger.debug("----Start Header Section of request ----");
        g_logger.debug("Method Type : {}", requestContext.getMethod());
        iterator = requestContext.getHeaders().keySet().iterator();
        while (iterator.hasNext()) {
            String headerName = iterator.next();
            String headerValue = requestContext.getHeaderString(headerName);
            g_logger.debug("Header Name: {}, Header Value :{} ", headerName, headerValue);
        }
        g_logger.debug("----End Header Section of request ----");
    }

    private String readEntityStream(ContainerRequestContext requestContext) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        final InputStream inputStream = requestContext.getEntityStream();
        final StringBuilder builder = new StringBuilder();
        try {
            ReaderWriter.writeTo(inputStream, outStream);
            byte[] requestEntity = outStream.toByteArray();
            if (requestEntity.length == 0) {
                builder.append("");
            } else {
                builder.append(new String(requestEntity));
            }
            requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
        } catch (IOException ex) {
            g_logger.debug("----Exception occurred while reading entity stream :{}", ex.getMessage());
        }
        return builder.toString();
    }

    /*==========  ==========*/
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        String stTime = MDC.get("start-time");
        if (null == stTime || stTime.length() == 0) {
            return;
        }
        long executionTime = System.currentTimeMillis() - Long.parseLong(stTime);
        g_logger.debug("Total request execution time : {} milliseconds", executionTime);
        //clear the context on exit
        MDC.clear();
    }
}
