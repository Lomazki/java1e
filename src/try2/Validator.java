package try2;

/*
     Класс для валидации
        - name
        - lastName
        - email
        - role
        - phone
 */

/*
телефоны должны быть в виде 375 *****, к примеру, | 37500 1234567 |.
email в виде *****@*****.*, к примеру, | any@email.com |. Т.е. достаточно проверки на ‘@’ и точку
 */

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public Boolean validatorName(String name) {
        StringBuilder sb = new StringBuilder(name);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(sb);
        return matcher.find();
    }


    public Boolean validatorEmail(String email) {
        StringBuilder sb = new StringBuilder(email);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$");
        Matcher matcher = pattern.matcher(sb);
        return matcher.find();
    }

    public Boolean validatorRole(List<String> role) {

        /*
        Проверяем role на:
        1) Корректность. Есть ли такая роль в enum
        2) Есть ли уже ADMIN
        3) Соответствие уравней
         */




//        Role.getRoleNames().contains(role.toUpperCase());
        return null;
    }

    public Boolean validatorPhone(List phone) {

        /*
        Проверяем на:
        1) Корректность ввода самого номера
        2) Количество (до трех номеров)
         */
//        StringBuilder sb = new StringBuilder(phone);
//        Pattern pattern = Pattern.compile("^[0-9]{5} [0-9]{7}$");
//        Matcher matcher = pattern.matcher(sb);

        // добавить проверку на наличие/ограничение до 3х телефонов

        return null;  //matcher.find();
    }
}
