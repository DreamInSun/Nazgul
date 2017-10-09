package cyan.nazgul.dropwizard.resources;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 * Created by DreamInSun on 2016/7/27.
 */
public interface IResource<TConfig extends Configuration> {

    /**
     * 获取应用配置
     *
     * @return
     */
    TConfig getConfig();

    /**
     * 构造完成，基础资源载入完成后的初始化工作。
     *
     * @param config
     * @param env
     * @return
     */
    int initialize(TConfig config, Environment env);
}
