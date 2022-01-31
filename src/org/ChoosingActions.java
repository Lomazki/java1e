package src.org;

import src.org.repository.impl.UserRepositoryImpl;
import src.org.service.impl.UserServiceImpl;
import src.org.validation.impl.EmailValidatorImpl;
import src.org.validation.impl.NameValidatorImpl;
import src.org.validation.impl.PhoneValidatorImpl;
import src.org.validation.impl.RoleValidatorImpl;

public enum ChoosingActions {

    CREATE ("1") {
        @Override
        public void action() {
            controller.create();
        }
    },
    FIND ("2") {
        @Override
        public void action() {
            controller.find();
        }
    },
    EDIT ("3") {
        @Override
        public void action() {
            controller.edit();
        }
    },
    REMOVE ("4") {
        @Override
        public void action() {
            controller.remove();
        }
    },
    SHOW_ALL ("5") {
        @Override
        public void action() {
            controller.showAll();
        }
    };

    Controller controller = new Controller(new ConsoleWorker(),
            UserRepositoryImpl.getRepository(),
            new ScannerWorker(new ConsoleWorker()),
            new UserIdGenerator(UserRepositoryImpl.getRepository()),
            new NameValidatorImpl(),
            new EmailValidatorImpl(new UserServiceImpl(UserRepositoryImpl.getRepository())),
            new RoleValidatorImpl(),
            new PhoneValidatorImpl());

    private final String number;

    ChoosingActions(String number) {
        this.number = number;
    }

    public abstract void action();

    public String getNumber() {
        return number;
    }
}
