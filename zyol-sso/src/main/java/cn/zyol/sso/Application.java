package cn.zyol.sso;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@ServletComponentScan(basePackages = {"cn.zyol"}) //使用注解注册Servlet
@MapperScan("cn.zyol.sso.dao") //通过使用@MapperScan可以指定要扫描的Mapper类的包的路径
@ComponentScan(basePackages = {"cn.zyol.sso.web", "cn.zyol.sso.service.*", "cn.zyol.basic.config"})
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableDubbo(scanBasePackages = "cn.zyol.sso.web")
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        logger.info("服务成功启动");
    }

}
