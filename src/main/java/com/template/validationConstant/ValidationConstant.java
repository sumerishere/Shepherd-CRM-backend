package com.template.validationConstant;

public interface ValidationConstant {
	
	String NAME_isVALID = "^[A-Za-z\\s]+$";
	String ADDRESS_isVALID = "^[A-Za-z0-9\\s,.-]+$";
	String MOBILE_NUMBER_PATTERN = "\\d{1,12}";
    String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
    String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
}
