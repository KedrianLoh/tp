package seedu.todolist.constants;

/**
 * Enum that holds all the notification and help messages to be displayed.
 */
public enum Messages {
    START("Hello, I am your todo list and I will help you remember the tasks you need to do!"),
    NEW_SAVE("No save data found, creating a new task list for you!"),
    LOAD_SAVE("Your saved task list was successfully loaded!"),
    EXIT("See you again, bye!"),

    ADD_TASK("Okay, I have added this task:"),
    MARK_TASK("Okay, I have marked this task as complete:"),
    UNMARK_TASK("Okay, I have marked this task as incomplete:"),
    DELETE_TASK("Okay, I've removed this task:"),
    EDIT_TASK("Okay, I have edited the parameters of this task:"),
    LIST_TASKS("Okay, here are the tasks in your list:"),
    EMPTY_LIST("There are no tasks in your list.");

    public final String MESSAGE;

    Messages(String message) {
        MESSAGE = message;
    }
}
