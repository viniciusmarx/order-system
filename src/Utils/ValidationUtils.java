package Utils;

public class ValidationUtils {

    public static void validateRequiredString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " é obrigatório e não pode estar vazio");
        }
    }

    public static void validateRequiredInteger(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " deve ser maior que zero");
        }
    }
}
