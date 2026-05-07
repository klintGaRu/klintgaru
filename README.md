
Java
// Convert to Decimal
public static int toDecimal(String number, int fromBase) {
Explanation:
Defines a static method toDecimal, which takes a number as a String and its base (fromBase). The method will convert this number into its decimal (base 10) equivalent and return it as an int.

Java
    int decimal = 0;
Explanation:
Initializes an integer variable decimal that will store the accumulated decimal value of the given number.

Java
    for (char digit : number.toCharArray()) {
Explanation:
Begins a loop that iterates through each character (digit) in the input number, converting the string into an array of characters.

Java
        int digitValue = Character.digit(digit, fromBase);
Explanation:
Converts the character digit to its integer value in the given base using Java's Character.digit() method.

Java
        decimal = decimal * fromBase + digitValue;
Explanation:
Updates the decimal result by multiplying the current value by the base and adding the numeric value of the current digit. This is the standard positional notation conversion.

Java
    }
Explanation:
Ends the for-loop.

Java
    return decimal;
}
Explanation:
Returns the calculated decimal value.

Java
// Convert from Decimal to another base
public static String fromDecimal(int decimal, int toBase) {
Explanation:
Defines a static method fromDecimal, which inputs a decimal number and a target base, then returns the number’s representation in the target base as a String.

Java
    if (decimal == 0) return "0";
Explanation:
Handles the special case where the number is zero.

Java
    String result = "";
Explanation:
Initializes an empty string to build the result.

Java
    while (decimal > 0) {
Explanation:
Begins a while-loop to process the decimal number until it's reduced to zero (by division).

Java
        int remainder = decimal % toBase;
Explanation:
Computes the remainder (the least significant digit in the new base).

Java
        if (remainder < 10) {
            result = remainder + result;
        } else {
            result = (char)('A' + remainder - 10) + result;
        }
Explanation:

If the digit is less than 10, prepend the digit as a number to the result string.
Otherwise, convert remainders 10–15 to letters 'A'–'F' for hexadecimal (or beyond for higher bases) and prepend to the result string.
Java
        decimal = decimal / toBase;
Explanation:
Reduces the decimal value by dividing it by the base—effectively handling the next most significant digit.

Java
    }
Explanation:
Ends the while-loop.

Java
    return result;
}
Explanation:
Returns the result string containing the number in the new base.

Java
// Check if number is valid for the base
public static boolean isValid(String number, int base) {
Explanation:
Defines a static method isValid to check if the provided number string is valid for the given base.

Java
    for (char c : number.toCharArray()) {
        if (Character.digit(c, base) == -1) {
            return false;
        }
    }
Explanation:

Iterates through each character of the input number.
If Character.digit(c, base) returns -1, it means the digit is not valid in that base, so the method returns false.
Java
    return number.length() > 0;
}
Explanation:
If all digits are valid and the number is not empty, returns true; otherwise (if the number is empty), returns false.

Java
// Get base number from user choice (1-4)
public static int getBaseFromChoice(int choice) {
Explanation:
Defines a static method mapping a user’s numeric menu choice to the actual number base.

Java
    if (choice == 1) return 2;
    else if (choice == 2) return 8;
    else if (choice == 3) return 10;
    else if (choice == 4) return 16;
    else return -1;
}
Explanation:
Maps:

1 ⇒ 2 (Binary)
2 ⇒ 8 (Octal)
3 ⇒ 10 (Decimal)
4 ⇒ 16 (Hexadecimal)
Any other input ⇒ -1 (invalid choice)
In summary:

toDecimal() converts a value in another base to decimal.
fromDecimal() converts a decimal value to another base.
isValid() checks the validity of a value in the specified base.
getBaseFromChoice() maps user input to the numerical base.
