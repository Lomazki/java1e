package src.org;

import src.org.repository.UserRepository;
import src.org.repository.impl.UserRepositoryImpl;
import src.org.service.UserService;
import src.org.service.impl.UserServiceImpl;
import src.org.validation.EmailValidator;
import src.org.validation.impl.EmailValidatorImpl;
import src.org.validation.impl.NameValidatorImpl;
import src.org.validation.impl.PhoneValidatorImpl;
import src.org.validation.impl.RoleValidatorImpl;

import java.io.*;

public class Runner implements Serializable {

    private static final long serialVersionUID = 1575349246421981036L;
    private static ScannerWorker scannerWorker;

    public Runner(ScannerWorker scannerWorker) {
        Runner.scannerWorker = scannerWorker;
    }

    public static void main(String[] args) {

        PhoneValidatorImpl phoneValidator = new PhoneValidatorImpl();
        RoleValidatorImpl roleValidator = new RoleValidatorImpl();
        NameValidatorImpl nameValidator = new NameValidatorImpl();
        ConsoleWorker consoleWorker = new ConsoleWorker();
        UserRepository repository = UserRepositoryImpl.getRepository();

        ScannerWorker scannerWorker = new ScannerWorker(consoleWorker);
        UserService userService = new UserServiceImpl(repository);
        UserIdGenerator userIdGenerator = new UserIdGenerator(repository);
        EmailValidator emailValidator = new EmailValidatorImpl(userService);

        Controller controller = new Controller(consoleWorker, repository, scannerWorker,
                userIdGenerator, nameValidator, emailValidator, roleValidator, phoneValidator);

        String choice1 = Runner.scannerWorker.mainSelect();
        for (ChoosingActions action : ChoosingActions.values()) {
            if (action.getNumber().equals(choice1)) {
                action.action();
            }
        }
    }
}
