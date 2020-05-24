package yu.mthgh123.booksmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("yu.mthgh123.booksmall.dao")
@SpringBootApplication
public class BooksmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksmallApplication.class, args);
    }

}
