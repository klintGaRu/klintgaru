/**
 * Validates user input against the rules of a given number base.
 */
public class BaseValidator {

    /**
     * Returns a human-readable label for the supported bases.
     */
    public static String getBaseLabel(int base) {
        switch (base) {
            case 2:  return "Binary";
            case 8:  return "Octal";
            case 10: return "Decimal";
            case 16: return "Hexadecimal";
            default: return "Base-" + base;
        }
    }

    /**
     * Returns a description of the allowed characters for the given base.
     */
    public static String getAllowedChars(int base) {
        switch (base) {
            case 2:  return "0 and 1";
            case 8:  return "0-7";
            case 10: return "0-9";
            case 16: return "0-9 and A-F (case-insensitive)";
            default: return "digits valid for base " + base;
        }
    }

    /**
     * Returns true if {@code base} is one of the four supported bases.
     */
    public static boolean isSupportedBase(int base) {
        return base == 2 || base == 8 || base == 10 || base == 16;
    }

    /**
     * Returns true if every character in {@code input} is a valid digit for
     * the given {@code base}.  The check is case-insensitive for hex.
     *
     * @throws IllegalArgumentException if {@code input} is null or empty
     */
    public static boolean isValidForBase(String input, int base) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input must not be empty.");
        }
        String upper = input.trim().toUpperCase();
        for (char c : upper.toCharArray()) {
            int digit = Character.digit(c, base);
            if (digit == -1) {
                return false;
            }
        }
        return true;
    }
}
