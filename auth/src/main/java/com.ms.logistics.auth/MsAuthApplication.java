package com.ms.logistics.auth;

import com.ms.logistics.auth.config.AppContext;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.Role;
import com.ms.logistics.auth.model.User;
import com.ms.logistics.auth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

@SpringBootApplication
public class MsAuthApplication implements CommandLineRunner {

	private final UserService userService;

	private final String usernameAdmin = "admin";
	private final String passwordAdmin = "admin@admin";
	private final String nameAdmin = "Admin Application";

	public MsAuthApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MsAuthApplication.class, args);
		AppContext.loadApplicationContext(ctx);
	}

	@Override
	public void run(String... args) throws Exception {
		this.createAdminUser();
	}

	private void createAdminUser() throws BusinessException {
		if (Objects.isNull(this.userService.findByUsername(this.usernameAdmin))) {
			User admin = new User(this.usernameAdmin, this.nameAdmin, this.passwordAdmin);
			admin.setRole(Role.ROLE_ADMIN.getAuthority());
			this.userService.insert(admin);
		}
	}

}
