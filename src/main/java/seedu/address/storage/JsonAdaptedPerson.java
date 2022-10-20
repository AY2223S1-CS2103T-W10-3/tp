package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.CriticalIllnessInsurance;
import seedu.address.model.person.DisabilityInsurance;
import seedu.address.model.person.Email;
import seedu.address.model.person.HealthInsurance;
import seedu.address.model.person.Insurance;
import seedu.address.model.person.LifeInsurance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final String healthInsurance;
    private final String disabilityInsurance;
    private final String criticalIllnessInsurance;
    private final String lifeInsurance;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("birthday") String birthday,
                             @JsonProperty("healthInsurance") String healthInsurance,
                             @JsonProperty("disabilityInsurance") String disabilityInsurance,
                             @JsonProperty("criticalIllnessInsurance") String criticalIllnessInsurance,
                             @JsonProperty("lifeInsurance") String lifeInsurance,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.healthInsurance = healthInsurance;
        this.disabilityInsurance = disabilityInsurance;
        this.criticalIllnessInsurance = criticalIllnessInsurance;
        this.lifeInsurance = lifeInsurance;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().value;
        healthInsurance = Boolean.toString(source.getHealthInsurance().hasInsurance);
        disabilityInsurance = Boolean.toString(source.getDisabilityInsurance().hasInsurance);
        criticalIllnessInsurance = Boolean.toString(source.getCriticalIllnessInsurance().hasInsurance);
        lifeInsurance = Boolean.toString(source.getLifeInsurance().hasInsurance);
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Birthday modelBirthday = new Birthday(birthday);

        final Insurance modelHealthInsurance = new HealthInsurance(Boolean.valueOf(healthInsurance));
        final Insurance modelDisabilityInsurance = new DisabilityInsurance(Boolean.valueOf(disabilityInsurance));
        final Insurance modelCriticalIllnessInsurance = new CriticalIllnessInsurance(Boolean
                .valueOf(criticalIllnessInsurance));
        final Insurance modelLifeInsurance = new LifeInsurance(Boolean.valueOf(lifeInsurance));

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelBirthday,
                modelHealthInsurance, modelDisabilityInsurance, modelCriticalIllnessInsurance,
                modelLifeInsurance, modelTags);
    }

}
