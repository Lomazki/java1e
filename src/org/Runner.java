package org;

import org.components.DataSource;
import org.components.InteractiveWorker;
import org.components.impl.ConsoleWorker;
import org.components.impl.FileDataSource;
import org.controllers.Controller;
import org.serializers.FileWorker;
import org.repositories.UserRepository;
import org.repositories.impl.UserRepositoryImpl;
import org.services.UserService;
import org.services.impl.UserServiceImpl;
import org.utils.UserIdGenerator;
import org.validators.EmailValidator;
import org.validators.NameValidator;
import org.validators.PhoneValidator;
import org.validators.RoleValidator;
import org.validators.impl.EmailValidatorImpl;
import org.validators.impl.NameValidatorImpl;
import org.validators.impl.PhoneValidatorImpl;
import org.validators.impl.RoleValidatorImpl;
import org.views.View;
import org.views.impl.ConsoleView;

import java.io.Serializable;

public class Runner implements Serializable {

    private static final long serialVersionUID = 1575349246421981036L;

    public static void main(String[] args) {

        PhoneValidator phoneValidator = new PhoneValidatorImpl();
        RoleValidator roleValidator = new RoleValidatorImpl();
        NameValidator nameValidator = new NameValidatorImpl();
        View view = new ConsoleView();
        FileWorker fileWorker = new FileWorker();
        InteractiveWorker consoleWorker = new ConsoleWorker(view);
        DataSource dataSource = new FileDataSource(fileWorker);
        UserRepository repository = UserRepositoryImpl.getRepository(dataSource);

        UserService userService = new UserServiceImpl(repository);
        UserIdGenerator userIdGenerator = new UserIdGenerator(repository);
        EmailValidator emailValidator = new EmailValidatorImpl(userService);

        Controller controller = new Controller(consoleWorker, userService,
                userIdGenerator, emailValidator, nameValidator, roleValidator, phoneValidator,view);

        controller.launch();
    }
}
