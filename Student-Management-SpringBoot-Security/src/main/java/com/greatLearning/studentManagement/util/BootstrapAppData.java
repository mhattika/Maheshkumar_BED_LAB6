package com.greatLearning.studentManagement.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.greatLearning.studentManagement.entity.Role;
import com.greatLearning.studentManagement.entity.User;
import com.greatLearning.studentManagement.repository.UserRepository;

@Configuration
public class BootstrapAppData {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public BootstrapAppData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertAppData(ApplicationReadyEvent event) {
		System.out.println("Application is ready :: ");
		System.out.println("Inserting the test data :: ");
		User kiran = new User("kiran", this.passwordEncoder.encode("welcome"));
		User vinay = new User("vinay", this.passwordEncoder.encode("welcome"));

		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");

		kiran.addRole(userRole);

		vinay.addRole(userRole);
		vinay.addRole(adminRole);

		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
	}

}
