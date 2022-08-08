package com.ms.logistics.auth;

import com.ms.logistics.auth.model.Account;
import com.ms.logistics.auth.model.Role;
import com.ms.logistics.auth.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Main class of application.
 *
 * @author LucianoReul
 */
@SpringBootApplication
@AllArgsConstructor
@EnableFeignClients
public class MsAuthApplication implements CommandLineRunner {

	public final AccountService accountService;

	/**
	 * MAIN
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsAuthApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) {
		this.createAdminUser();
	}

	/**
	 * Create a default account to access the system
	 */
	private void createAdminUser() {
		String usernameAdmin = "admin";
		String passwordAdmin = "admin@admin";
		if (Objects.isNull(this.accountService.findByUsername(usernameAdmin))) {
			Account admin = new Account(usernameAdmin, passwordAdmin);
			admin.setRole(Role.ROLE_ADMIN.getAuthority());
			this.accountService.insert(admin);
		}
	}
}
