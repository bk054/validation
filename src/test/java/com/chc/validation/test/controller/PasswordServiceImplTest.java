package com.chc.validation.test.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.chc.validation.config.ValidationConfig;
import com.chc.validation.service.impl.PasswordSeviceImpl;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={ValidationConfig.class})
public class PasswordServiceImplTest {
	
	@InjectMocks
	PasswordSeviceImpl passwordService;
	
	 @Test
	 public void testPasswordLengthIsBtwn5and12() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("test12456");
			assertTrue(isPasswordValid);
			System.out.println("Length of the password is between 5 to 12!");
		}
	 
	 @Test
	 public void testPasswordMinLengthisNot5() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("test");
			assertFalse(isPasswordValid);
			System.out.println("Minimum Length of the password is 5");
		}
	 
	 @Test
	 public void testPasswordMaxLengthisNot12() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("test1267456190");
			assertFalse(isPasswordValid);
			System.out.println("Maximum Length of the password is 12");
		}
	 
	 @Test
	 public void testPasswordContains1Digit() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("testabcdhijk");
			assertFalse(isPasswordValid);
			System.out.println("Password must contain 1 digit");
		}
	 
	 @Test
	 public void testPasswordContains1Character() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("7203363682");
			assertFalse(isPasswordValid);
			System.out.println("Password must contain 1 character");
		}
	 
	 @Test
	 public void testPasswordContainsOnlyLowerCaseLetters() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("Test123");
			assertFalse(isPasswordValid);
			System.out.println("Password should be in lowercase");
		}
	 
	 @Test
	 public void testPasswordShouldNotContainSpecialCharacters() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("123@#45678");
			assertFalse(isPasswordValid);
			System.out.println("Password should not have any special characters!");
		}
	 
	 @Test
	 public void testPasswordHasNoSequence() throws Exception {
			boolean isPasswordValid = passwordService.isPasswordValid("123abab");
			assertFalse(isPasswordValid);
			System.out.println("Password should not have repeatable sequence!");
		}

}
