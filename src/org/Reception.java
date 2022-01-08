package org;

import org.models.User;
import org.userService.impl.*;
import org.repository.impl.RepositoryImpl;
import org.models.ValidatorError;

import java.io.IOException;
import java.util.List;

import static org.constants.ExceptionMessage.*;

public class Reception {

    ScannerWorker scannerWorker = new ScannerWorker();
    RepositoryImpl repository = new RepositoryImpl();

    SearchImpl search = new SearchImpl();
    ShowAllImpl showAll = new ShowAllImpl();
    RemoveImpl remove = new RemoveImpl();
    CreateImpl create = new CreateImpl();
    EditImpl edit = new EditImpl();

    void mainChoice() throws IOException, ClassNotFoundException {
        String choice;
        System.out.println("Hi! What would you like?");
        do {
            choice = scannerWorker.mainSelect();
        } while (!(choice.equals("1") ||
                choice.equals("2") ||
                choice.equals("3") ||
                choice.equals("4") ||
                choice.equals("5")));

        switch (choice) {
            case ("1"):             // Creat

                ValidatorError createResult = create.runningCreate();
                if (createResult == null) {
                    repository.saveToUserBook();
                    System.out.println(USER_CREATED);
                } else {
                    System.out.println(createResult.getMessage());
                }
                break;
            case ("2"):             // Find
                ValidatorError findResult = search.findRunning();
                System.out.println(findResult == null ?
                        search.getSearchUser() : findResult.getMessage());
                break;
            case ("3"):             // Edit
                ValidatorError editResult = edit.editRunning();
                if (editResult == null) {
                    repository.saveToUserBook();
                    System.out.println(USER_WAS_EDITED);
                } else {
                    System.out.println(editResult.getMessage());
                }
                break;
            case ("4"):             // Remove
                ValidatorError resultRemove = remove.removeRunning();
                List<User> newUserList = remove.getNewUserList();
                if (newUserList == null || newUserList.size() == 0) {
                    System.out.println(USER_WAS_REMOVED);
                } else {
                    repository.saveToUserBook();
                    System.out.println(resultRemove.getMessage());
                }
                break;
            case ("5"):             // ShowAll
                ValidatorError resultShowAll = showAll.showAllUser();
                if (resultShowAll != null) {
                    System.out.println(resultShowAll.getMessage());
                } else {
                    break;
                }
                break;
        }
    }
}
