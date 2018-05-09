package cyan.nazgul.dropwizard.resources;

import cyan.nazgul.dropwizard.BaseConfiguration;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;

/**
 * Created by DreamInSun on 2016/7/27.
 * <p>
 * 错误页面的处理
 * <p>
 * HTTP 错误 400
 * 400 请求出错
 * 由于语法格式有误，服务器无法理解此请求。不作修改，客户程序就无法重复此请求。
 * <p>
 * HTTP 错误 401
 * 401.1 未授权：登录失败
 * 此错误表明传输给服务器的证书与登录服务器所需的证书不匹配。
 * 请与 Web 服务器的管理员联系，以确认您是否具有访问所请求资源的权限。
 * 401.2 未授权：服务器的配置导致登录失败
 * 此错误表明传输给服务器的证书与登录服务器所需的证书不匹配。此错误通常由未发送正确的 WWW 验证表头字段所致。
 * 请与 Web 服务器的管理员联系，以确认您是否具有访问所请求资源的权限。
 * 401.3 未授权：由于资源中的 ACL 而未授权
 * 此错误表明客户所传输的证书没有对服务器中特定资源的访问权限。此资源可能是客户机中的地址行所列出的网页或文件，也可能是处理客户机中的地址行所列出的文件所需服务器上的其他文件。
 * 请记录试图访问的完整地址，并与 Web 服务器的管理员联系以确认您是否具有访问所请求资源的权限。
 * 401.4 未授权：授权服务被筛选程序拒绝
 * 此错误表明 Web 服务器已经安装了筛选程序，用以验证连接到服务器的用户。此筛选程序拒绝连接到此服务器的真品证书的访问。
 * 请记录试图访问的完整地址，并与 Web 服务器的管理员联系以确认您是否具有访问所请求资源的权限。
 * 401.5 未授权：ISAPI/CGI 应用程序的授权失败
 * 此错误表明试图使用的 Web服务器中的地址已经安装了 ISAPI 或 CGI程序，在继续之前用以验证用户的证书。此程序拒绝用来连接到服务器的真品证书的访问。
 * 请记录试图访问的完整地址，并与 Web服务器的管理员联系以确认您是否具有访问所请求资源的权限
 * <p>
 * HTTP 错误 403
 * 403.1 禁止：禁止执行访问
 * 如果从并不允许执行程序的目录中执行 CGI、ISAPI或其他执行程序就可能引起此错误。
 * 如果问题依然存在，请与 Web 服务器的管理员联系。
 * 403.2 禁止：禁止读取访问
 * 如果没有可用的默认网页或未启用此目录的目录浏览，或者试图显示驻留在只标记为执行或脚本权限的目录中的HTML 页时就会导致此错误。
 * 如果问题依然存在，请与 Web 服务器的管理员联系。
 * 403.3 禁止：禁止写访问
 * 如果试图上载或修改不允许写访问的目录中的文件，就会导致此问题。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 * 403.4 禁止：需要 SSL
 * 此错误表明试图访问的网页受安全套接字层（SSL）的保护。要查看，必须在试图访问的地址前输入https:// 以启用 SSL。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 * 403.5 禁止：需要 SSL 128
 * 此错误消息表明您试图访问的资源受 128位的安全套接字层（SSL）保护。要查看此资源，需要有支持此SSL 层的浏览器。
 * 请确认浏览器是否支持 128 位 SSL安全性。如果支持，就与 Web服务器的管理员联系，并报告问题。
 * 403.6 禁止：拒绝 IP 地址
 * 如果服务器含有不允许访问此站点的 IP地址列表，并且您正使用的 IP地址在此列表中，就会导致此问题。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 * 403.7 禁止：需要用户证书
 * 当试图访问的资源要求浏览器具有服务器可识别的用户安全套接字层（SSL）证书时就会导致此问题。可用来验证您是否为此资源的合法用户。
 * 请与 Web服务器的管理员联系以获取有效的用户证书。
 * 403.8 禁止：禁止站点访问
 * 如果 Web服务器不为请求提供服务，或您没有连接到此站点的权限时，就会导致此问题。
 * 请与 Web 服务器的管理员联系。
 * 403.9 禁止访问：所连接的用户太多
 * 如果 Web太忙并且由于流量过大而无法处理您的请求时就会导致此问题。请稍后再次连接。
 * 如果问题依然存在，请与 Web 服务器的管理员联系。
 * 403.10 禁止访问：配置无效
 * 此时 Web 服务器的配置存在问题。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 * 403.11 禁止访问：密码已更改
 * 在身份验证的过程中如果用户输入错误的密码，就会导致此错误。请刷新网页并重试。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 * 403.12 禁止访问：映射程序拒绝访问
 * 拒绝用户证书试图访问此 Web 站点。
 * 请与站点管理员联系以建立用户证书权限。如果必要，也可以更改用户证书并重试。
 * <p>
 * HTTP 错误 404
 * 404 找不到
 * Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
 * 如果问题依然存在，请与服务器的管理员联系。
 * <p>
 * HTTP 错误 405
 * 405 不允许此方法
 * 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
 * 如果问题依然存在，请与服务器的管理员联系。
 * <p>
 * HTTP 错误 406
 * 406 不可接受
 * 根据此请求中所发送的“接受”标题，此请求所标识的资源只能生成内容特征为“不可接受”的响应实体。
 * 如果问题依然存在，请与服务器的管理员联系。
 * <p>
 * HTTP 错误 407
 * 407 需要代理身份验证
 * 在可为此请求提供服务之前，您必须验证此代理服务器。请登录到代理服务器，然后重试。
 * 如果问题依然存在，请与 Web 服务器的管理员联系。
 * <p>
 * HTTP 错误 412
 * 412 前提条件失败
 * 在服务器上测试前提条件时，部分请求标题字段中所给定的前提条件估计为FALSE。客户机将前提条件放置在当前资源 metainformation（标题字段数据）中，以防止所请求的方法被误用到其他资源。
 * 如果问题依然存在，请与 Web 服务器的管理员联系。
 * <p>
 * HTTP 错误 414
 * 414 Request-URI 太长
 * Request-URL太长，服务器拒绝服务此请求。仅在下列条件下才有可能发生此条件：
 * 客户机错误地将 POST 请求转换为具有较长的查询信息的 GET 请求。
 * 客户机遇到了重定向问题（例如，指向自身的后缀的重定向前缀）。
 * 服务器正遭受试图利用某些服务器（将固定长度的缓冲区用于读取或执行 Request-URI）中的安全性漏洞的客户干扰。
 * 如果问题依然存在，请与 Web 服务器的管理员联系。
 * <p>
 * HTTP 错误 500
 * 500 服务器的内部错误
 * Web 服务器不能执行此请求。请稍后重试此请求。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 * <p>
 * HTTP 错误 501
 * 501 未实现
 * Web 服务器不支持实现此请求所需的功能。请检查URL 中的错误，如果问题依然存在，请与 Web服务器的管理员联系。
 * <p>
 * HTTP 错误 502
 * 502 网关出错
 * 当用作网关或代理时，服务器将从试图实现此请求时所访问的upstream 服务器中接收无效的响应。
 * 如果问题依然存在，请与 Web服务器的管理员联系。
 */
@Api(value = "错误显示页", description = "")
@Path(value = "/error")
@Produces(MediaType.TEXT_HTML)
public class ErrorResource extends BaseResource<BaseConfiguration> {

    /*========== Logger ==========*/

    /*========== Properties ==========*/


    /*========== Constructor ==========*/
    public ErrorResource(BaseConfiguration config, Environment env) {
        super(config, env);
    }

    /*========== Error Handler ==========*/
    @GET
    @Path("/general-error")
    public Response generalError() {
        File file = this.getResource("web/error/general.html");
        if (file != null) {
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            return Response.ok(file, contentType).header("Charset", "UTF-8").build();
        } else {
            return Response.ok("服务内部错误").header("Charset", "UTF-8").build();
        }

    }

    @GET
    @Path("/404")
    public Response error404() {
        File file = this.getResource("web/error/404.html");
        if (file != null) {
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            return Response.ok(file, contentType).header("Charset", "UTF-8").build();
        } else {
            return Response.ok("404-资源未找到").header("Charset", "UTF-8").build();
        }
    }

    @GET
    @Path("/403")
    public Response error403() {
        File file = this.getResource("web/error/403.html");
        if (file != null) {
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            return Response.ok(file, contentType).header("Charset", "UTF-8").build();
        } else {
            return Response.ok("403-资源不可用").build();
        }
    }

    @GET
    @Path("/401")
    public Response error401() {
        //InputStream fileStream = this.getResourceAsStream("web/error/401.html");
        File file = this.getResource("web/error/401.html");
        if (file != null) {
            String contentType = new MimetypesFileTypeMap().getContentType(file);
            return Response.ok(file, contentType).header("Charset", "UTF-8").build();
        } else {
            return Response.ok("401-资源未授权").header("Charset", "UTF-8").build();
        }
    }
}
