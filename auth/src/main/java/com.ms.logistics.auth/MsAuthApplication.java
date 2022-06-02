package com.ms.logistics.auth;

import com.ms.logistics.auth.config.AppContext;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.Account;
import com.ms.logistics.auth.model.Role;
import com.ms.logistics.auth.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

@SpringBootApplication
@AllArgsConstructor
@EnableFeignClients
public class MsAuthApplication implements CommandLineRunner {

	public final AccountService accountService;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MsAuthApplication.class, args);
		AppContext.loadApplicationContext(ctx);
	}

	@Override
	public void run(String... args) throws Exception {
		this.createAdminUser();
	}

	private void createAdminUser() throws BusinessException {
		String usernameAdmin = "admin";
		String passwordAdmin = "admin@admin";
		if (Objects.isNull(this.accountService.findByUsername(usernameAdmin))) {
			Account admin = new Account(usernameAdmin, passwordAdmin);
			admin.setRole(Role.ROLE_ADMIN.getAuthority());
			this.accountService.insert(admin);
		}
	}
}
