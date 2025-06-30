package com.nhaqua23.jotion.config;

import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.model.Workspace;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.repository.WorkspaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StreamUtils;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

@Configuration
@Slf4j
public class ApplicationInitConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository, WorkspaceRepository workspaceRepository, DataSource dataSource) {
		return args -> {
			if (userRepository.findByUsername("ADMIN").isEmpty()){
				var role = UserRole.ADMIN;

				User user = new User();
				user.setUsername("ADMIN");
				user.setEmail("admin@example.com");
				user.setPassword(passwordEncoder.encode("admin"));
				user.setRole(role);
				userRepository.save(user);
				createDefaultWorkspace(workspaceRepository, user);

				log.warn("Admin has been created with default email: admin@gmail.com and password: admin, please change it");
			}

			if (userRepository.findByUsername("Account 2").isEmpty()){
				var role = UserRole.USER;

				User user = new User();
				user.setUsername("Account 2");
				user.setEmail("account2@example.com");
				user.setPassword(passwordEncoder.encode("account2"));
				user.setRole(role);
				userRepository.save(user);
				createDefaultWorkspace(workspaceRepository, user);

				log.warn("User2 has been created with default email: account2@gmail.com and password: account2, please change it");
			}

			if (userRepository.findByUsername("Account 3").isEmpty()){
				var role = UserRole.USER;

				User user = new User();
				user.setUsername("Account 3");
				user.setEmail("account3@example.com");
				user.setPassword(passwordEncoder.encode("account3"));
				user.setRole(role);
				userRepository.save(user);
				createDefaultWorkspace(workspaceRepository, user);

				log.warn("User3 has been created with default email: account3@gmail.com and password: account3, please change it");
			}

			try (Connection connection = dataSource.getConnection();
					 Statement statement = connection.createStatement()) {
				Resource resource = new ClassPathResource("data-manual.sql");
				String sql = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
				statement.execute(sql);
				log.info("SQL script executed successfully");
			} catch (Exception e) {
				log.error("Failed to execute SQL script", e);
			}
		};
	}

	private void createDefaultWorkspace(WorkspaceRepository workspaceRepository, User user) {
		Workspace workspace = new Workspace();
		workspace.setName("Shared Workspace");
		workspace.setUser(user);
		workspace.setCreatedAt(LocalDate.now());
		workspace.setUpdatedAt(LocalDate.now());
		workspace.setEditable(false);
		workspaceRepository.save(workspace);
	}
}
