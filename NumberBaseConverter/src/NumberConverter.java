/**
 * Converts a number string from one base to another and records the
 * intermediate decimal step for display purposes.
 */
public class NumberConverter {

    private final String originalValue;
    private final int    fromBase;
    private final int    toBase;
    private long   decimalValue;   // intermediate decimal
    private String result;

    public NumberConverter(String value, int fromBase, int toBase) {
        this.originalValue = value.trim().toUpperCase();
        this.fromBase      = fromBase;
        this.toBase        = toBase;
    }

    /**
     * Performs the conversion.  Must be called before the getters.
     *
     * @throws IllegalArgumentException on overflow (number too large)
     */
    public void convert() {
        // Step 1 – parse the input in its source base to a decimal long
        decimalValue = Long.parseLong(originalValue, fromBase);

        // Step 2 – render the decimal value in the target base
        result = Long.toString(decimalValue, toBase).toUpperCase();
    }

    // ---------------------------------------------------------------
    // Getters
    // ---------------------------------------------------------------

    public String getOriginalValue() { return originalValue; }
    public int    getFromBase()      { return fromBase; }
    public int    getToBase()        { return toBase; }
    public long   getDecimalValue()  { return decimalValue; }
    public String getResult()        { return result; }
}
