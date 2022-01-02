package org.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPhone {

    public Boolean validator(String[] phones) {
        for (String phone : phones) {
            StringBuilder sb = new StringBuilder(phone.trim());
            Pattern pattern = Pattern.compile("^[0-9]{5} [0-9]{7}$");
            Matcher matcher = pattern.matcher(sb);

            if (!matcher.find()) {
                System.out.println("The phone number is incorrect. Only numbers and one space are allowed. For example, 37576 9851569");
                return false;
            }
            if (phones.length > 3) {
                System.out.println("Only 3 phones are allowed");
                return false;
            }
        }
        return true;
    }
}
