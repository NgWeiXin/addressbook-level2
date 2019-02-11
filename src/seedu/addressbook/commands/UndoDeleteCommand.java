package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

public class UndoDeleteCommand extends Command {

    public static final String COMMAND_WORD = "undoDelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "undo the previous delete command.";

    public static final String MESSAGE_SUCCESS = "Successfully undo previous delete command.";

    public static final String MESSAGE_FAIL = "Unable to undo previous delete command.";

    private static Person deletedPerson = null;

    public UndoDeleteCommand() {

    }

    public static void replaceLastDeletedPerson(ReadOnlyPerson recentlyDeletedPerson) {
        deletedPerson = new Person(recentlyDeletedPerson);
    }

    @Override
    public CommandResult execute() {
        if (deletedPerson == null) {
            return new CommandResult(MESSAGE_FAIL + " deletedPerson is null.");
        }

        try {
            addressBook.addPerson(deletedPerson);
            deletedPerson = null;
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_FAIL + " duplicated person exception.");
        }
    }

}
