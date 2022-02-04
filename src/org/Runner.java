package src.org;

import src.org.datesource.DateSource;
import src.org.datesource.impl.FileDateSource;
import src.org.repository.UserRepository;
import src.org.repository.impl.UserRepositoryImpl;
import src.org.service.UserService;
import src.org.service.impl.UserServiceImpl;
import src.org.validation.EmailValidator;
import src.org.validation.impl.EmailValidatorImpl;
import src.org.validation.impl.NameValidatorImpl;
import src.org.validation.impl.PhoneValidatorImpl;
import src.org.validation.impl.RoleValidatorImpl;
import src.org.view.View;
import src.org.view.impl.ConsoleView;

import java.io.*;

public class Runner implements Serializable {

    private static final long serialVersionUID = 1575349246421981036L;



    public static void main(String[] args) {

        PhoneValidatorImpl phoneValidator = new PhoneValidatorImpl();
        RoleValidatorImpl roleValidator = new RoleValidatorImpl();
        NameValidatorImpl nameValidator = new NameValidatorImpl();
        View view = new ConsoleView();
        ConsoleWorker consoleWorker = new ConsoleWorker(view);
        FileWorker fileWorker = new FileWorker();
        DateSource dateSource = new FileDateSource(fileWorker);
        UserRepository repository = UserRepositoryImpl.getRepository(dateSource);

        UserService userService = new UserServiceImpl(repository);
        UserIdGenerator userIdGenerator = new UserIdGenerator(repository);
        EmailValidator emailValidator = new EmailValidatorImpl(userService);

        Controller controller = new Controller(consoleWorker, userService,
                userIdGenerator, emailValidator, nameValidator, roleValidator, phoneValidator,view);

        controller.launch();
    }
}
