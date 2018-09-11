package cn.lancel0t.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String KEY = "lancel0t.cn";
	
	@Value("${login.username}")
	private String username;

	@Value("${login.password}")
    private String password;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 需要管理权限
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/admin")
				.failureUrl("/login?error")
				.permitAll()
				.and()
				.logout().permitAll()
				// 启用 remember me
				.and().rememberMe().key(KEY);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(new BCryptPasswordEncoder())
				.withUser(username)
				.password(new BCryptPasswordEncoder().encode(password))
				.roles("ADMIN");
	}
}
