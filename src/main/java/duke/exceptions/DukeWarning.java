package duke.exceptions;

public class DukeWarning extends Exception {
    @Override
    public String getMessage() {
        return "DukeWarning: ";
    }
}
