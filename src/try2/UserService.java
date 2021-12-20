package try2;
import java.util.List;

public class UserService {

    public User create(String name, String lastName, String email, String role, String phone){
        return new User(name, lastName, email, role, phone);
    }

    public User found(List <User> userList, String email){
        for (User user : userList){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public String remove (List <User> userList, String email){
        for (User user : userList){
            if (user.getEmail().equals(email)){
                userList.remove(user);
                return "User removed";
            }
        }
        return "User not found";
    }

    public User edit(User user){
        return null;
    }
}
