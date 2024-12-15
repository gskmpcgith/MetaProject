package com.example.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class MetaProjectApplication implements CommandLineRunner {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

    public MetaProjectApplication(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
	
	public static void main(String[] args) {
		//SpringApplication.run(MetaProjectApplication.class, args);
		System.out.println("Hello");
		
		ApplicationContext ctx = SpringApplication.run(MetaProjectApplication.class, args);
        
        // Print bean names for debugging
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}

	@Override
	public void run(String... args) throws Exception {
		//saveDefaultRecords();
	}
	
	public void saveDefaultRecords() {
		Role roleUser = new Role();
		Role roleAdmin = new Role();
		roleUser.setRoleName("ROLE_USER");
		roleAdmin.setRoleName("ROLE_ADMIN");
		Set<Role> userRoles = new HashSet<>();
		// Initialize users
		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword(new BCryptPasswordEncoder().encode("password"));
		
		try {
			//roleRepository.save(roleUser);
			//roleRepository.save(roleAdmin);
			user1.setCreateDate(new Date());
			user1.setUpdateDate(new Date());
			user1.setIsDeleted(0);
			userRepository.save(user1);
			user1.setUserIdKey(new BCryptPasswordEncoder().encode(String.valueOf(user1.getUserId())));
			roleUser.setUser(user1);
			userRoles.add(roleUser);
			user1.setRoles(userRoles);
			userRepository.save(user1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
