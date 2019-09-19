package duke.exceptions;

public class DukeUnrecognizedTimeWarning extends DukeWarning {
    @Override
    public String getMessage() {
        return super.getMessage() + "Unrecognized time and date format!";
    }
}
