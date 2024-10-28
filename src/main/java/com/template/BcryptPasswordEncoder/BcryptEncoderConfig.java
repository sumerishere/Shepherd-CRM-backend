package com.template.BcryptPasswordEncoder;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptEncoderConfig {

    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());   
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}