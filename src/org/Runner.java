package org;

import org.repository.impl.UserRepositoryImpl;
import org.service.impl.*;
import org.validation.impl.EmailValidatorImpl;
import org.validation.impl.NameValidatorImpl;
import org.validation.impl.PhoneValidatorImpl;
import org.validation.impl.RoleValidatorImpl;

import java.io.*;
import java.util.Scanner;

public class Runner implements Serializable {

    private static final long serialVersionUID = 1575349246421981036L;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Reception reception = new Reception(
                new ScannerWorker(new Scanner(System.in)),
                UserRepositoryImpl.getRepository(),
                new SearchServiceImpl(
                        UserRepositoryImpl.getRepository(),
                        new EmailValidatorImpl(),
                        new ScannerWorker(new Scanner(System.in))
                ),
                new ShowAllServiceImpl(
                        UserRepositoryImpl.getRepository()
                ),
                new RemoveServiceImpl(
                        new SearchServiceImpl(
                                UserRepositoryImpl.getRepository(),
                                new EmailValidatorImpl(),
                                new ScannerWorker(new Scanner(System.in))
                        ),
                        UserRepositoryImpl.getRepository()
                ),
                new CreateServiceImpl(
                        new ScannerWorker(new Scanner(System.in)),
                        UserRepositoryImpl.getRepository(),
                        new NameValidatorImpl(),
                        new EmailValidatorImpl(),
                        new RoleValidatorImpl(),
                        new PhoneValidatorImpl()
                ),
                new EditServiceImpl(
                        new ScannerWorker(new Scanner(System.in)),
                        UserRepositoryImpl.getRepository(),
                        new SearchServiceImpl(
                                UserRepositoryImpl.getRepository(),
                                new EmailValidatorImpl(),
                                new ScannerWorker(new Scanner(System.in))
                        ),
                        new CreateServiceImpl(
                                new ScannerWorker(new Scanner(System.in)),
                                UserRepositoryImpl.getRepository(),
                                new NameValidatorImpl(),
                                new EmailValidatorImpl(),
                                new RoleValidatorImpl(),
                                new PhoneValidatorImpl()
                        )
                )
        );
        reception.mainChoice();
    }
}
