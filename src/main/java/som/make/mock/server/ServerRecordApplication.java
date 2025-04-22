package som.make.mock.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class ServerRecordApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(ServerRecordApplication.class, args);
    }

}
