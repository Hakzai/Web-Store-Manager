package com.akeir.webstore.security.initializers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.akeir.webstore.infra.utils.PasswordUtils;
import com.akeir.webstore.security.model.User;
import com.akeir.webstore.security.repository.UserRepository;

@Component
public class UserInitializer implements CommandLineRunner {

	/**
	 * Standard initial login/password for MASTER USER 
	 */
	private static final String SYSADMIN_USER = "sysadmin";
	
	/**
	 * Standard initial login/password for READ ONLY USER
	 */
	private static final String READ_ONLY_DOC_USER = "docreader";
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) 
	{
		createMasterUser();
		createReadOnlyUser();
	}
	
	/**
	 * Creating master user :: In case it doesn't exist we create it
	 */
	private void createMasterUser()
	{
		User user = new User();
		user.setUsername(SYSADMIN_USER);
		user.setPassword(PasswordUtils.hashPassword(SYSADMIN_USER));
		user.setRoles(SYSADMIN_USER);
		
		if(Optional.empty().equals(userRepository.findByUsername(SYSADMIN_USER)))
		{
			userRepository.save(user);
			System.out.println("DatabaseInitializer.createMasterUser :: MASTER USER CREATED");
		}
		else
		{
			System.out.println("DatabaseInitializer.createMasterUser :: MASTER USER ALREADY EXISTS");
		}
	}
	
	/**
	 * Creating read only user :: In case it doesn't exist we create it
	 */
	private void createReadOnlyUser()
	{
		User user = new User();
		user.setUsername(READ_ONLY_DOC_USER);
		user.setPassword(PasswordUtils.hashPassword(READ_ONLY_DOC_USER));
		user.setRoles(READ_ONLY_DOC_USER);
		
		if(Optional.empty().equals(userRepository.findByUsername(READ_ONLY_DOC_USER)))
		{
			userRepository.save(user);
			System.out.println("DatabaseInitializer.createReadOnlyUser :: DOC READER USER CREATED");
		}
		else
		{
			System.out.println("DatabaseInitializer.createReadOnlyUser :: DOC READER USER ALREADY EXISTS");
		}
	}
}
