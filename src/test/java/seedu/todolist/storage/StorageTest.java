//@@author jeromeongithub
package seedu.todolist.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Test;
import seedu.todolist.logic.Parser;
import seedu.todolist.logic.command.Command;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class StorageTest {
    private static final String PROPER_SAVE_FILE = "./src/test/data/properSaveFile.txt";
    private static final String INVALID_SAVE_FILE = "./src/test/data/invalidSaveFile.txt";
    private static final String TEST_DATA_FOLDER = "./src/test/data";

    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Parser parser = new Parser();
    private Storage storage = new Storage(PROPER_SAVE_FILE);
    private Gson gson = new GsonBuilder().
            registerTypeAdapter(LocalDateTime .class, new Storage.LocalDateTimeAdapter()).create();

    @Test
    void saveData_properFilePath_success() {
        try {
            String sampleInput1 = "add bubu -due 18-02-2032 18:00 -rep 0";
            Command command1 = parser.parseCommand(sampleInput1);
            command1.execute(taskList, ui);
            String sampleInput2 = "add baba -due 18-04-2033 12:00 -rep 0";
            Command command2 = parser.parseCommand(sampleInput2);
            command2.execute(taskList, ui);

            // Save modified task list object
            storage.saveData(taskList, PROPER_SAVE_FILE);

            // convert task list object to a json string
            String taskListAsJson = gson.toJson(taskList);

            // read PROPER_SAVE_FILE as a string
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PROPER_SAVE_FILE));
            String savedFileString = bufferedReader.readLine();

            // compare the 2 strings
            assertEquals(taskListAsJson, savedFileString);
        } catch (Exception e) {
            // no exception will be thrown, so the program will not reach this line
            fail();
        }
    }

    @Test
    void saveData_directoryAsFilePath_exceptionThrown() {
        try {
            Storage storageDirectory = new Storage(TEST_DATA_FOLDER);
            storageDirectory.saveData(taskList, TEST_DATA_FOLDER);
            // The test should not reach the following line
            fail();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void loadData_fileWithInvalidData_exceptionThrown() {
        try {
            Storage storageInvalid = new Storage(INVALID_SAVE_FILE);
            taskList = storageInvalid.loadData(INVALID_SAVE_FILE);
            // The test should not reach the following line
            fail();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (JsonParseException e) {
            // this exception will be thrown
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
