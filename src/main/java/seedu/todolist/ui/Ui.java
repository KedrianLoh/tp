package seedu.todolist.ui;

import seedu.todolist.constants.HelpMessages;
import seedu.todolist.constants.Messages;
import seedu.todolist.model.Task;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner input = new Scanner(System.in);

    public String getUserInput() {
        System.out.print("> ");
        return input.nextLine();
    }

    public void close() {
        input.close();
    }

    /**
     * Prints out the given strings with a newline separating them.
     *
     * @param strings The strings to print out.
     */
    private void println(String... strings) {
        System.out.println(Messages.LINE);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(Messages.LINE);
    }

    /**
     * Generates a grammar-appropriate string based on the number of tasks in the task list,
     * such as "0 tasks", "1 task", "2 tasks".
     *
     * @param taskListSize The size of the task list.
     * @return The task count string.
     */
    private String generateTaskCountString(int taskListSize) {
        return taskListSize + " task" + (taskListSize == 1 ? "" : "s");
    }

    public void printWelcomeMessage() {
        println(Messages.START);
    }

    public void printNewConfigMessage() {
        println(Messages.NEW_CONFIG);
    }

    public void printLoadConfigMessage() {
        println(Messages.LOAD_CONFIG);
    }

    public void printNewSaveMessage() {
        println(Messages.NEW_SAVE);
    }

    public void printLoadSaveMessage(int taskListSize) {
        println(Messages.LOAD_SAVE + generateTaskCountString(taskListSize));
    }

    public void printGoodbyeMessage() {
        println(Messages.EXIT);
    }

    public void printAddTaskMessage(String taskString) {
        println(Messages.ADD_TASK, taskString);
    }

    public void printMarkTaskMessage(String taskString) {
        println(Messages.MARK_TASK, taskString);
    }

    public void printUnmarkTaskMessage(String taskString) {
        println(Messages.UNMARK_TASK, taskString);
    }

    public void printDeleteTaskMessage(String taskString) {
        println(Messages.DELETE_TASK, taskString);
    }

    public void printEditTaskMessage(String parameterType, String newValue, String taskString) {
        println(String.format(Messages.EDIT_TASK, parameterType, newValue), taskString);
    }

    public void printEditDeleteTaskMessage(String parameterType, String taskString) {
        println(String.format(Messages.EDIT_DELETE_TASK, parameterType), taskString);
    }

    public void printEditConfigMessage(String taskString) {
        println(String.format(Messages.EDIT_CONFIG), taskString);
    }

    public void printConfigInfo(String taskString) {
        println(String.format(Messages.CONFIG_INFO), taskString);
    }

    public void printTaskList(int taskListSize, String taskListString) {
        if (taskListSize == 0) {
            println(Messages.LIST_EMPTY);
        } else {
            println(Messages.LIST_TASKS + generateTaskCountString(taskListSize), taskListString);
        }
    }

    public void printGetFullInfoMessage(String infoString) {
        println(Messages.FULL_INFO, infoString);
    }

    public void printGetTagsMessage(int tagCount, String tagsString) {
        if (tagCount == 0) {
            println(Messages.TAGS_EMPTY);
        } else {
            println(Messages.TAGS_INFO, tagsString);
        }
    }

    public void printError(Exception e) {
        println(e.getMessage());
    }

    //@@author jeromeongithub
    public void printProgressBar(int completedTasksThisWeek, int tasksThisWeek,
                                 int totalSections, String taskListString) {
        if (tasksThisWeek == 0) {
            println("You have no tasks due this week!");
            return;
        }

        assert tasksThisWeek != 0;
        double progress = (double) completedTasksThisWeek / tasksThisWeek;
        int completedSections = (int) (progress * totalSections);
        int incompleteSections = totalSections - completedSections;
        DecimalFormat twoDecimalPlaces = new DecimalFormat("0.00");
        String progressPercentage = twoDecimalPlaces.format(100 * progress);
        println("You have completed " + progressPercentage + "% of the " + generateTaskCountString(tasksThisWeek)
                + " due this week!", "Progress: |" + "=".repeat(completedSections)
                + "-".repeat(incompleteSections) + "|", taskListString);
    }

    //@@author KedrianLoh
    public void printTasksWithTag(ArrayList<Task> taskList) {
        taskList.forEach(System.out::println);
    }
    //@@author KedrianLoh
    public void printTasksWithPriority(ArrayList<Task> taskList) {
        taskList.forEach(System.out::println);
    }

    public void printHelpList() {
        println(HelpMessages.HELP_COMMAND);
    }
}
