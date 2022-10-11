package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.applicant.*;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    public static final String DEFAULT_SCHOLARSHIP = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_APPLICATION_STATUS = "pending";

    private Name name;
    private Phone phone;
    private Email email;

    private Scholarship scholarship;
    private ApplicationStatus applicationStatus;
    private Set<Tag> tags;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        scholarship = new Scholarship(DEFAULT_SCHOLARSHIP);
        applicationStatus = new ApplicationStatus(DEFAULT_APPLICATION_STATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        scholarship = applicantToCopy.getScholarship();
        applicationStatus = applicantToCopy.getApplicationStatus();
        tags = new HashSet<>(applicantToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withScholarship(String scholarship) {
        this.scholarship = new Scholarship(scholarship);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withApplicationStatus(String applicationStatus) {
        this.applicationStatus = new ApplicationStatus(applicationStatus);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone, email, scholarship, applicationStatus, tags);
    }

}