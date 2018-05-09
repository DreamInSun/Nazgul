package cyan.nazgul.dropwizard.cli;

/**
 * Created by DreamInSun on 2018/5/8.
 */
public interface DockerCommandHandler<TConfig> {

    /**
     * 为了解决初始化时，配置未载入问题，重写了EnvironmentCommand，在配置载入之后，Bootstrap执行之前，可以运行这个接口，执行一些后初始化工作。
     * @param config
     */
    void processAfterConfigBeforeBootstrap(TConfig config);
}
