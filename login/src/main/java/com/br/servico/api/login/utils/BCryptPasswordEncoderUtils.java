package com.br.servico.api.login.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    public static String encode(String caracters){
        return bCrypt.encode(caracters);
    }
}
