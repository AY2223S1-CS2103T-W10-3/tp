package seedu.trackascholar.model.applicant;

import static seedu.trackascholar.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.trackascholar.model.major.Major;

/**
 * Represents an Applicant in TrackAScholar.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final ApplicationStatus applicationStatus;
    private final Scholarship scholarship;
    private final Set<Major> majors = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Scholarship scholarship,
                     ApplicationStatus applicationStatus, Set<Major> majors) {
        requireAllNonNull(name, phone, email, scholarship, applicationStatus, majors);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.scholarship = scholarship;
        this.applicationStatus = applicationStatus;
        this.majors.addAll(majors);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Returns an immutable major set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Major> getMajors() {
        return Collections.unmodifiableSet(majors);
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName());
    }

    /**
     * Returns true if this applicant has a matching application status.
     */
    public boolean isMatchingApplicationStatus(ApplicationStatus otherStatus) {
        return otherStatus != null
                && otherStatus.equals(applicationStatus);
    }

    /**
     * Returns a comparator that sorts applicants lexicographically by name.
     * No tiebreaker needed as applicant names are guaranteed to be unique.
     */

    public static Comparator<Applicant> sortByName() {
        return (a1, a2) -> {
            Name n1 = a1.getName();
            Name n2 = a2.getName();
            return n1.compareTo(n2);
        };
    }

    /**
     * Returns a comparator that sorts applicants lexicographically by scholarship.
     * If both applicants have the same scholarship, tiebreak by name.
     */

    public static Comparator<Applicant> sortByScholarship() {
        return (a1, a2) -> {
            Scholarship s1 = a1.getScholarship();
            Scholarship s2 = a2.getScholarship();
            int result = s1.compareTo(s2);
            if (result == 0) {
                Name n1 = a1.getName();
                Name n2 = a2.getName();
                return n1.compareTo(n2);
            }
            return result;
        };
    }

    /**
     * Returns a comparator that sorts applicants by application status. Pending applicants are
     * displayed at the top of the list, followed by accepted applicants, and lastly rejected applicants.
     * If both applicants have the same application status, tiebreak by name.
     */

    public static Comparator<Applicant> sortByStatus() {
        return (a1, a2) -> {
            ApplicationStatus as1 = a1.getApplicationStatus();
            ApplicationStatus as2 = a2.getApplicationStatus();
            int result = as1.compareTo(as2);
            if (result == 0) {
                Name n1 = a1.getName();
                Name n2 = a2.getName();
                return n1.compareTo(n2);
            }
            return result;
        };
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getScholarship().equals(getScholarship())
                && otherApplicant.getApplicationStatus().equals(getApplicationStatus())
                && otherApplicant.getMajors().equals(getMajors());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, scholarship, applicationStatus, majors);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Scholarship: ")
                .append(getScholarship())
                .append("; Application Status: ")
                .append(getApplicationStatus());

        Set<Major> majors = getMajors();
        if (!majors.isEmpty()) {
            builder.append("; Majors: ");
            majors.forEach(builder::append);
        }
        return builder.toString();
    }

}