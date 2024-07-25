package com.template.BcryptPasswordEncoder;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptEncoderConfig {

//    @Override
    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());   
    }

//    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}