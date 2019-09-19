package duke.tasks;

import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.DukeUnrecognizedTimeWarning;
import duke.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DoAfter extends Task {
    private LocalDateTime dateTime;
    private String prevTask;

    /**
     * Constructor for DoAfter class, using String Varargs.
     * @param input Parsed user input containing task name and time.
     */
    public DoAfter(String... input) {
        super(input[0]);
        try {
            dateTime = DateTimeParser.getStringToDate(input[input.length - 1]);
        }
        catch (DukeInvalidTimeException ex) {
//            System.out.println(new DukeUnrecognizedTimeWarning().getMessage());
            prevTask = input[input.length - 1];
        }
    }

    public String getStartTime() {
        if (dateTime == null) {
            return prevTask;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy [HH:mm]"));
    }

    public void setDateTime(String time) {
        try {
            this.dateTime = DateTimeParser.getStringToDate(time);
            this.prevTask = null;
        }
        catch (DukeInvalidTimeException ex) {
            this.prevTask = time;
            this.dateTime = null;
        }
    }

    @Override
    public String writingFile() {
        String prefix = "A"
                + "|"
                + super.writingFile()
                + "|";
        if (dateTime == null) {
            return prefix + prevTask;
        }
        else {
            return prefix + dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy [HH:mm]"));
        }
    }

    @Override
    public String toString() {
        String prefix = "[A]"
                + super.toString()
                + " (after: ";
        if (dateTime == null) {
            return prefix + prevTask + ")";
        }
        else {
            return prefix + dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy [HH:mm]")) + ")";
        }
    }
}
