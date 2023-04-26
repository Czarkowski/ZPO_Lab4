package application.zpo_lab4;

public interface Validator {
    void validate(String value);
    boolean isValid();
    String getMessage();
}
