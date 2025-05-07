package com.akeir.webstore.security.controller;

import javax.management.OperationsException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.akeir.webstore.security.controller.UserController;
import com.akeir.webstore.security.model.User;
import com.akeir.webstore.security.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {

	@InjectMocks
	private UserController userController;
	
	@Test
	@DisplayName("Saves the first user (system admin user) in the database")
	void saveFirstAuthor()
	{
		User user = new User();
		user.setUsername("sysadm");
		user.setPassword("sysadm12");
		user.setRoles("sysadmin");
		
//		Assertions.assertDoesNotThrow(() -> userController.create(user));
		
//		try {
			
//		} catch (DataIntegrityViolationException ex) {
//			System.err.println("MASTER USER ADM ALREADY EXIST");
//		}
	}
}
