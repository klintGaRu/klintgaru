import java.util.Scanner;

/**
 * Console-based Number Base Converter
 * ------------------------------------
 * Converts numbers between Binary (2), Octal (8), Decimal (10), and
 * Hexadecimal (16) with full input validation and a menu-driven UI.
 */
public class NumberBaseConverterApp {

    private static final Scanner scanner = new Scanner(System.in);

    // ---------------------------------------------------------------
    // Entry point
    // ---------------------------------------------------------------

    public static void main(String[] args) {
        printBanner();
        boolean keepRunning = true;
        while (keepRunning) {
            try {
                int fromBase = promptBase("source");
                int toBase   = promptBase("target");
                String value = promptValue(fromBase);

                NumberConverter converter = new NumberConverter(value, fromBase, toBase);
                converter.convert();

                printResult(converter);
            } catch (NumberFormatException e) {
                System.out.println("\n  [ERROR] The number is too large to process. Please try a smaller value.");
            }

            keepRunning = promptContinue();
        }

        System.out.println("\n  Thank you for using the Number Base Converter. Goodbye!");
        scanner.close();
    }

    // ---------------------------------------------------------------
    // UI helpers
    // ---------------------------------------------------------------

    private static void printBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       NUMBER BASE CONVERTER  v1.0        ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  Supported bases:                        ║");
        System.out.println("║   [1] Binary       (base  2)             ║");
        System.out.println("║   [2] Octal        (base  8)             ║");
        System.out.println("║   [3] Decimal      (base 10)             ║");
        System.out.println("║   [4] Hexadecimal  (base 16)             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Prompts the user to choose a base (1-4) and returns the corresponding
     * radix (2, 8, 10, or 16).  Loops until a valid selection is made.
     */
    private static int promptBase(String label) {
        int[] radixes = {2, 8, 10, 16};
        while (true) {
            System.out.printf("  Select %s number system:%n", label);
            System.out.println("    [1] Binary       (base  2)");
            System.out.println("    [2] Octal        (base  8)");
            System.out.println("    [3] Decimal      (base 10)");
            System.out.println("    [4] Hexadecimal  (base 16)");
            System.out.print("  Your choice (1-4): ");

            String input = scanner.nextLine().trim();
            if (input.matches("[1-4]")) {
                int choice = Integer.parseInt(input);
                int base   = radixes[choice - 1];
                System.out.printf("  → %s selected.%n%n", BaseValidator.getBaseLabel(base));
                return base;
            }
            System.out.println("  [ERROR] Invalid choice. Please enter a number between 1 and 4.\n");
        }
    }

    /**
     * Prompts for the number to convert, validating it against the chosen base.
     * Loops until a valid, non-empty value is provided.
     */
    private static String promptValue(int base) {
        while (true) {
            System.out.printf("  Enter the %s number to convert%n", BaseValidator.getBaseLabel(base));
            System.out.printf("  (allowed characters: %s): ", BaseValidator.getAllowedChars(base));

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("  [ERROR] Input must not be empty. Please try again.\n");
                continue;
            }

            try {
                if (BaseValidator.isValidForBase(input, base)) {
                    System.out.println();
                    return input;
                } else {
                    System.out.printf(
                        "  [ERROR] \"%s\" contains characters not allowed in %s.%n"
                        + "          Allowed characters: %s. Please try again.%n%n",
                        input, BaseValidator.getBaseLabel(base), BaseValidator.getAllowedChars(base));
                }
            } catch (IllegalArgumentException e) {
                System.out.println("  [ERROR] " + e.getMessage() + "\n");
            }
        }
    }

    /**
     * Displays the conversion result with an intermediate decimal step when
     * neither the source nor target base is decimal.
     */
    private static void printResult(NumberConverter c) {
        String fromLabel = BaseValidator.getBaseLabel(c.getFromBase());
        String toLabel   = BaseValidator.getBaseLabel(c.getToBase());

        System.out.println("  ┌─────────────────────────────────────────┐");
        System.out.println("  │              CONVERSION RESULT          │");
        System.out.println("  ├─────────────────────────────────────────┤");
        System.out.printf ("  │  Original : %-10s  (%s)%n",
                            c.getOriginalValue(), fromLabel);
        System.out.printf ("  │  Result   : %-10s  (%s)%n",
                            c.getResult(), toLabel);

        // Show intermediate decimal step when useful
        if (c.getFromBase() != 10 && c.getToBase() != 10) {
            System.out.println("  ├─────────────────────────────────────────┤");
            System.out.printf ("  │  Step  1  : %s (%s) → %d (Decimal)%n",
                                c.getOriginalValue(), fromLabel, c.getDecimalValue());
            System.out.printf ("  │  Step  2  : %d (Decimal) → %s (%s)%n",
                                c.getDecimalValue(), c.getResult(), toLabel);
        } else if (c.getFromBase() != 10) {
            System.out.println("  ├─────────────────────────────────────────┤");
            System.out.printf ("  │  Step  1  : %s (%s) → %d (Decimal)%n",
                                c.getOriginalValue(), fromLabel, c.getDecimalValue());
        } else if (c.getToBase() != 10) {
            System.out.println("  ├─────────────────────────────────────────┤");
            System.out.printf ("  │  Step  1  : %d (Decimal) → %s (%s)%n",
                                c.getDecimalValue(), c.getResult(), toLabel);
        }

        System.out.println("  └─────────────────────────────────────────┘");
        System.out.println();
    }

    /**
     * Asks whether the user wants to perform another conversion.
     * Returns true for 'Y', false for 'N'.  Loops on invalid input.
     */
    private static boolean promptContinue() {
        while (true) {
            System.out.print("  Perform another conversion? (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) {
                System.out.println();
                return true;
            } else if (input.equals("N")) {
                return false;
            }
            System.out.println("  [ERROR] Please enter Y or N.\n");
        }
    }
}
