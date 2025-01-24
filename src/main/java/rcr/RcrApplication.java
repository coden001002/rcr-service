package rcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class RcrApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RcrApplication.class);
        application.setRegisterShutdownHook(true);
        ConfigurableApplicationContext context = application.run(args);
        context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
            if ((event.getSource() instanceof org.springframework.context.annotation.AnnotationConfigApplicationContext)) {
                return;
            }
        });

    }
}
