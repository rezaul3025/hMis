package org.hmis.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/","/login","/image/*","/scriptlib/**","/css/**").permitAll()
			.antMatchers("/home").hasAnyAuthority("ADMIN")
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin()
			.loginPage("/login").defaultSuccessUrl("/home", false).permitAll()
			.failureUrl("/login?error")
			.usernameParameter("username").passwordParameter("password")
			.and()
			.logout().logoutUrl("/").permitAll()
			.logoutSuccessUrl("/?logout")
			.and()
		    .exceptionHandling().accessDeniedPage("/403");
	}
	
	/*@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		//auth.inMemoryAuthentication()
		//.withUser("tom").password("pass").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource1)
		.usersByUsernameQuery("SELECT username, password, enabled FROM user_info WHERE username=?")
		.authoritiesByUsernameQuery("SELECT username, role FROM user_role WHERE username=?").rolePrefix("USER");
	}*/
	
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService)
         .passwordEncoder(new BCryptPasswordEncoder());
    }
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter 
	{

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception 
		{
			/*auth.ldapAuthentication().userDnPatterns("uid={0},ou=people")
					.groupSearchBase("ou=groups").contextSource()
					.ldif("classpath:test-server.ldif");*/
			
			
           /* LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProviderConfigurer = auth.ldapAuthentication();

            ldapAuthenticationProviderConfigurer
                //.userSearchFilter("cn={0}")
                //.userSearchBase("ou=people,dc=local")
            .userDnPatterns("uid={0},ou=people,dc=local")
            .groupSearchBase("ou=groups,dc=local")
                .contextSource(contextSource());
                */
		}
		
		/*@Bean
		public DefaultSpringSecurityContextSource contextSource() throws Exception 
		{
			DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource("ldap://192.168.1.60:389");
            contextSource.setUserDn("cn=admin,dc=local");
            contextSource.setPassword("admin");
            contextSource.setReferral("follow"); 
            contextSource.afterPropertiesSet();
            
            return contextSource;
		}
		
		@Bean
	    public LdapTemplate ldapTemplate() throws Exception {
	        return new LdapTemplate(contextSource());        
	    }*/
	}
}
