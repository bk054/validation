package com.chc.validation.service.impl;

import java.util.Arrays;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.IllegalCharacterRule;
import org.passay.WhitespaceRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordValidator;
import org.passay.RepeatCharacterRegexRule;
import org.passay.RuleResult;
import org.springframework.stereotype.Service;

import com.chc.validation.service.PasswordService;

@Service("passwordService")
public class PasswordSeviceImpl implements PasswordService {
	@Override
	public boolean isPasswordValid(String password) {
		return isValid(password);
	}
	
	private boolean doesAnySeqExists = false;
	public boolean isValid(String password) {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				new LengthRule(5, 12), 
				new CharacterRule(EnglishCharacterData.LowerCase,1),
				new CharacterRule(EnglishCharacterData.Digit,1),
				new IllegalCharacterRule(EnglishCharacterData.Special.getCharacters().toCharArray()),
				new IllegalCharacterRule(EnglishCharacterData.UpperCase.getCharacters().toCharArray()),
				new RepeatCharacterRegexRule(3),
				new WhitespaceRule()));
		RuleResult result = validator.validate(new PasswordData(password));
		findIfAnyRepeatingSeqExist(password);
		if(result.isValid() && !doesAnySeqExists) {
			return true;
		}
		return false;
	}

	private boolean findIfAnyRepeatingSeqExist(String password) {
		int count = 2;
		int start= 0;
		int endSubString = start+count;
		boolean isValid = false;
		if(endSubString<password.length()) {
			String subPwdString1 = password.substring(start, endSubString);
			int beginChar = endSubString;
			int endChar = endSubString+2;
			if(endChar<=password.length()) {
				String subPwdString2 = password.substring(beginChar,endChar);
				if(subPwdString1.equalsIgnoreCase(subPwdString2)) {
					doesAnySeqExists = true;
				}else {
					String newPasword = password.substring(beginChar-1, password.length());
					if(beginChar<newPasword.length()) {
						findIfAnyRepeatingSeqExist(newPasword);
					}
				}
			}
		}
		return isValid;
	}
}