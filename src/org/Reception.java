package org;

import org.repository.UserRepository;
import org.service.*;
import org.models.ValidationError;

import java.io.IOException;

import static org.constants.ExceptionMessage.*;

public class Reception {

    private ScannerWorker scannerWorker;
    private UserRepository userRepository;

    private SearchService search;
    private ShowAllService showAll;
    private RemoveService remove;
    private CreateService create;
    private EditService edit;

    public Reception(ScannerWorker scannerWorker, UserRepository userRepository, SearchService search, ShowAllService showAll, RemoveService remove, CreateService create, EditService edit) {
        this.scannerWorker = scannerWorker;
        this.userRepository = userRepository;
        this.search = search;
        this.showAll = showAll;
        this.remove = remove;
        this.create = create;
        this.edit = edit;
    }

    void mainChoice() {

        String choice;
        do {
            choice = scannerWorker.mainSelect();
        } while (!(choice.equals("1") ||
                choice.equals("2") ||
                choice.equals("3") ||
                choice.equals("4") ||
                choice.equals("5")));

        switch (choice) {
            case ("1"):             // Creat
                ValidationError createResult = create.runCreate();
                if (createResult == null) {
//                    userRepository.save();
                    System.out.println(String.format(USER_CREATED, create.getNewUser()));
                } else {
                    System.out.println(createResult.getMessage());
                }
                break;
            case ("2"):             // Find
                ValidationError findResult = search.runSearch();
                System.out.println(findResult == null ?
                        search.getSearchUser() : findResult.getMessage());
                break;
            case ("3"):             // Edit
                ValidationError editResult = edit.runEdit();
                if (editResult == null) {
//                    userRepository.save();
                    System.out.println(String.format(USER_WAS_EDITED, edit.getEditedUser()));
                } else {
                    System.out.println(editResult.getMessage());
                }
                break;
            case ("4"):             // Remove
                ValidationError resultRemove = remove.runRemove();
                if (resultRemove == null) {
//                    userRepository.save();
                    System.out.println(String.format(USER_WAS_REMOVED, remove.getRemovedUser()));
                } else {
                    System.out.println(resultRemove.getMessage());
                }
                break;
            case ("5"):             // ShowAll
                ValidationError resultShowAll = showAll.showAllUser();
                if (resultShowAll != null) {
                    System.out.println(resultShowAll.getMessage());
                } else {
                    break;
                }
                break;
        }
    }
}
