package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.util.DateTimeParser;
import duke.util.DukeParser;
import duke.util.NattyWrapper;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {
    /**
     * Classes used for storage of data
     * Ui output and inputs and current
     * active tasks in TaskList.
     */
    private Storage store;
    private Ui ui;
    private TaskList tasks;

    // Testing Natty Wrapper:
    private NattyWrapper natty;


    /**
     * Constructor for Duke class.
     */
    public Duke() {
        store = new Storage();
        ui = new Ui();
        tasks = new TaskList(store);
        natty = new NattyWrapper();
    }

    /**
     * The main run loop for Duke, requesting for user input
     * and running valid commands. Invalid commands will be
     * alerted to users.
     */
    private void run() {
        ui.helloMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = DukeParser.parse(fullCommand);
                c.execute(tasks, ui, store);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point for Duke.
     * @param args Additional command line parameters, unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
