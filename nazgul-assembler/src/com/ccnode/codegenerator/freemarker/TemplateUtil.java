package com.ccnode.codegenerator.freemarker;

import com.ccnode.codegenerator.log.Log;
import com.ccnode.codegenerator.log.LogFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.Map;

public class TemplateUtil
{
    private static Log log = LogFactory.getLogger(TemplateUtil.class);
    private static Configuration configuration = new Configuration(Configuration.getVersion());

    static
    {
        try
        {
            configuration.setDirectoryForTemplateLoading(new File(new URI(TemplateUtil.class.getClassLoader().getResource("templates").toString()).getPath()));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
    }

    public static String processToString(String templateName, Map<String, Object> root)
    {
        try
        {
            Template template = configuration.getTemplate(templateName);
            StringWriter out = new StringWriter();
            template.process(root, out);
            return out.toString();
        }
        catch (Exception e)
        {
            log.error("template process catch exception", e);
            throw new RuntimeException("process freemarker template catch exception", e);
        }
    }
}
