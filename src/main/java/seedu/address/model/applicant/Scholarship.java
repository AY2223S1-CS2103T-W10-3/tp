package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Represents an Applicant's Scholarship name in TrackAScholar.
 * Guarantees: immutable; is valid as declared in {@link #isValidScholarship(String)}
 */
public class Scholarship {

    public static final String MESSAGE_CONSTRAINTS = "Scholarship can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Scholarship(String address) {
        requireNonNull(address);
        checkArgument(isValidScholarship(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidScholarship(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Scholarship // instanceof handles nulls
                && value.equals(((Scholarship) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Tests that a {@code Applicant}'s {@code Status} matches the keyword given.
     */
    public static class StatusContainsKeywordPredicate implements Predicate<Applicant> {
        private final String keyword;

        public StatusContainsKeywordPredicate(String keyword) {
            this.keyword = keyword;
        }

        @Override
        public boolean test(Applicant person) {
            return StringUtil.containsWordIgnoreCase(person.getApplicationStatus().applicationStatus, keyword);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof StatusContainsKeywordPredicate // instanceof handles nulls
                    && keyword.equals(((StatusContainsKeywordPredicate) other).keyword)); // state check
        }


    }
}
