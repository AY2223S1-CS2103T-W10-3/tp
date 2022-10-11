package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTrackAScholar;
import seedu.address.model.TrackAScholar;
import seedu.address.model.applicant.Applicant;

/**
 * An Immutable TrackAScholar that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTrackAScholar {

    public static final String MESSAGE_DUPLICATE_APPLICANT = "Persons list contains duplicate applicant(s).";

    private final List<JsonAdaptedTrackAScholar> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackAScholar} with the given applicants.
     */
    @JsonCreator
    public JsonSerializableTrackAScholar(@JsonProperty("persons") List<JsonAdaptedTrackAScholar> applicants) {
        this.persons.addAll(applicants);
    }

    /**
     * Converts a given {@code ReadOnlyTrackAScholar} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrackAScholar}.
     */
    public JsonSerializableTrackAScholar(ReadOnlyTrackAScholar source) {
        persons.addAll(source.getApplicantList().stream()
                .map(JsonAdaptedTrackAScholar::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TrackAScholar into the model's {@code TrackAScholar} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackAScholar toModelType() throws IllegalValueException {
        TrackAScholar trackAScholar = new TrackAScholar();
        for (JsonAdaptedTrackAScholar jsonAdaptedTrackAScholar : persons) {
            Applicant applicant = jsonAdaptedTrackAScholar.toModelType();
            if (trackAScholar.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            trackAScholar.addApplicant(applicant);
        }
        return trackAScholar;
    }

}
