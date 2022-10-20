package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.TrackAScholar;
import seedu.address.ui.AlertWindow;

/**
 * Clears all applicants from TrackAScholar.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TrackAScholar has been cleared!";
    public static final String MESSAGE_TERMINATION = "Clearing of all data is cancelled!";
    public static final String MESSAGE_CONFIRMATION =
            "Are you sure that you want to terminate all data from TrackAScholar?";

    private boolean hasConfirmed;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        promptUserConfirmation(MESSAGE_CONFIRMATION);
        if (hasConfirmed) {
            return confirmClear(model);
        } else {
            return cancelClear();
        }
    }
    /**
     * After confirmation from user, all data from TrackAScholar is purged.
     */
    public CommandResult confirmClear(Model model) {
        model.setTrackAScholar(new TrackAScholar());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * After cancellation from user, all data from TrackAScholar remains intact.
     */
    public CommandResult cancelClear() {
        return new CommandResult(MESSAGE_TERMINATION);
    }

    /**
     * Prompts user for confirmation before proceeding with purging of data
     * @param  message prompted to user
     */
    public void promptUserConfirmation(String message) {
        AlertWindow window = new AlertWindow();
        this.hasConfirmed = window.display(message);
    }
}
