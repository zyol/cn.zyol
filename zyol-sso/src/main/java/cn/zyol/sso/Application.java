package cn.zyol.sso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@ServletComponentScan(basePackages = {"cn.zyol"}) //使用注解注册Servlet
@MapperScan("cn.zyol.sso.dao") //通过使用@MapperScan可以指定要扫描的Mapper类的包的路径
@ComponentScan(basePackages = {"cn.zyol"})
public class Application implements EmbeddedServletContainerCustomizer {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        logger.info("服务成功启动");
    }

    /**
     * 修改端口
     *
     * @param configurableEmbeddedServletContainer
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8003);
    }
}
