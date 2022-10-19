package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/**
 * Sorts all applicants in TrackAScholar whose status matches the keyword.
 * Keyword matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all applicants by the specified "
            + "input (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: name/scholarship\n"
            + "Example: " + COMMAND_WORD + " name";

    private final Comparator<Applicant> comparator;

    public SortCommand(Comparator<Applicant> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortApplicants(comparator);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
