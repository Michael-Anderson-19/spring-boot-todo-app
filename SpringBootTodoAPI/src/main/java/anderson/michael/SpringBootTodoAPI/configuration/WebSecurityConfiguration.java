package anderson.michael.SpringBootTodoAPI.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable();

        //temporarily allow all all requests to all endpoints for hte purpose of development
        http.authorizeRequests().anyRequest().permitAll(); //temporarily allow any requests to aid in development
    }
}
