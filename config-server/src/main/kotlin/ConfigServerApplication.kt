import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@EnableConfigServer
class ConfigServerApplication

fun main(args: Array<String>) {
  SpringApplication.run(ConfigServerApplication::class.java, *args)
}
