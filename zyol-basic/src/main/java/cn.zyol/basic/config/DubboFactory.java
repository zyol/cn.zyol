package cn.zyol.basic.config;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dubbo服务工厂
 */
public class DubboFactory {
    private static Logger logger = LoggerFactory.getLogger(DubboFactory.class);
    /**
     * 当前应用的信息
     */
    private static ApplicationConfig application = new ApplicationConfig();
    /**
     * 注册中心信息缓存
     */
    private static Map<String, RegistryConfig> registryConfigCache = new ConcurrentHashMap<>();

    static {
        application.setName("dubbo-factory");
    }

    /**
     * 获取注册中心信息
     *
     * @param address zk注册地址
     * @param group   dubbo服务所在的组
     * @return
     */
    private static RegistryConfig getRegistryConfig(String address, String group, String version) {
        String key = address + "-" + group + "-" + version;
        RegistryConfig registryConfig = registryConfigCache.get(key);
        if (null == registryConfig) {
            registryConfig = new RegistryConfig();
            registryConfig.setAddress(address);
            registryConfig.setGroup(group);
            registryConfigCache.put(key, registryConfig);
        }
        return registryConfig;
    }


    public static <T> T getDubboService(Class<T> className, String address,
                                        String group, String version) {
        ReferenceConfig<T> rc = new ReferenceConfig<T>();
        rc.setApplication(application);
        rc.setInterface(className);
        rc.setRegistry(getRegistryConfig(address, group, version));
        rc.setVersion(version);
        return rc.get();

    }


}
