package try2.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPhone {

    public Boolean validator(String[] phones) {

        /*
        Проверяем на:
        1) Корректность ввода самого номера
        2) Количество (до трех номеров)
         */
        for (String phone : phones) {
            StringBuilder sb = new StringBuilder(phone.trim());
            Pattern pattern = Pattern.compile("^[0-9]{5} [0-9]{7}$");
            Matcher matcher = pattern.matcher(sb);

            if (!matcher.find()) {
                System.out.println("Неверно указан номер телефона.Допускаются только цифры и один пробел. К примеру, 37576 9851569");
                return false;
            }
            if (phones.length > 2) {
                System.out.println("Допускается только 3 телефона");
                return false;
            }
        }


        // добавить проверку на наличие/ограничение до 3х телефонов

        return true;  //matcher.find();
    }
}
