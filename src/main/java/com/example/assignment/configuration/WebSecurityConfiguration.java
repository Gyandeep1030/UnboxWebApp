package com.example.assignment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
	
	private static final String[] WHITELIST= { "/", "/login", "/info" };

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@SuppressWarnings({ "removal" })
	@Bean
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((authz) -> authz

						.requestMatchers(WHITELIST)

						.permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/sale/**").hasRole("SALE")
						.requestMatchers("/hr/**").hasRole("HR")
						.requestMatchers("/payroll").hasAnyRole("ACCOUNTANT", "HR")
                        .anyRequest()
                        .authenticated()

				)
				.formLogin(form -> {
					try {
						form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("email")
								.passwordParameter("password").defaultSuccessUrl("/dashboard", true).failureUrl("/login?error")
								.permitAll().and()
								.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
										.logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true))
								.rememberMe(me -> me.rememberMeParameter("remember-me")).httpBasic(withDefaults());
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
		return http.build();
	}
}
