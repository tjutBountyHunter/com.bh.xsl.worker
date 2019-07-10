package xsl_worker_main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
@SpringBootApplication
@ImportResource("classpath:*.xml")
@MapperScan("mapper")
@ComponentScan("quartz")
@ComponentScan("service")

public class WorkerApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(WorkerApplication.class, args);



    }
}
