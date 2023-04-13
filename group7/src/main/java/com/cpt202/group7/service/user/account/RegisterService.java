package com.cpt202.group7.service.user.account;

import java.util.Map;

public interface RegisterService {
    public Map<String, String> register (String username, String password, String confirm_password, String email, String phone_number);


}
