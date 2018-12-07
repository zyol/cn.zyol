package cn.zyol.basic.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * dubbo服务工厂
 */
public class DubboFactory {
    /*
     * 当前应用的信息
     */
    private static ApplicationConfig application = new ApplicationConfig();

    public static <T> T getDubboService(Class<T> className, String applicationName, String zookeeperUrl) {
        application.setName(applicationName);
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(zookeeperUrl);
        ReferenceConfig<T> rc = new ReferenceConfig<T>();
        rc.setApplication(application);
        rc.setInterface(className);
        return rc.get();

    }

}
