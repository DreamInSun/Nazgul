package cyan.nazgul.dropwizard.resources;

import io.dropwizard.Configuration;

/**
 * Created by DreamInSun on 2016/7/27.
 */
public interface IResource<TConfig extends Configuration> {

    /**
     *  获取应用配置
     * @return
     */
    TConfig getConfig();
}
