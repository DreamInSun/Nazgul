package cyan.svc.exception;

import cyan.svc.output.EntityOutput;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 必须添加该注解使得在程序的任何地方发生运行时异常时，自动的进行异常统一处理
 * 同时该类要能够被Jersey扫描到
 */
@Provider
public class NazExceptionMapper implements ExceptionMapper<NazException> {

    @Override
    public Response toResponse(NazException e) {
        return Response.ok().header("Content-Type", MediaType.APPLICATION_JSON).entity(EntityOutput.getInstance(e)).build();
    }
}