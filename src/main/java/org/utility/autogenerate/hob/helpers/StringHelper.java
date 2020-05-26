package org.utility.autogenerate.hob.helpers;


import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;

public final class StringHelper {

    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String SPECIAL_CHARACTERS = ",.`!@#$%^&*()_+=-/;~<>?:'{}[]|" + '"' + "\\";
    private static final String NULL_OR_EMPTY_VALUE = "Null or empty delimiter passed";
    private StringHelper() {
    }

    public static int searchStringArray(String[] strings, String searchFor) {
        for (int i = 0; i < strings.length; i++) {
            if (searchFor.equals(strings[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String removeLeading(String string, char removeChar) {
        int index = 0;
        while ((index < string.length()) && (string.charAt(index) == removeChar))
            index++;
        if (index > 0)
            return string.substring(index);
        return string;
    }

    public static String removeTrailing(String string, char removeChar) {
        int index = string.length();
        while ((index > 0) && (string.charAt(index - 1) == removeChar))
            index--;
        if (index < string.length())
            return string.substring(0, index);
        return string;
    }

    public static String removeTrailingChar(String string, char removeChar) {
        if (string.charAt(string.length() - 1) == removeChar)
            return string.substring(0, string.length() - 1);
        return string;
    }

    public static String removeLeadingTrailing(String string, char removeChar) {
        return removeTrailing(removeLeading(string, removeChar), removeChar);
    }

    public static String merge(Collection<?> stringList, String delimiter) {
        if (stringList == null)
            return "";

        StringBuilder sb = new StringBuilder(200);

        int i = 0;
        for (Object obj : stringList) {
            if (i != 0)
                sb.append(delimiter);

            if (obj != null)
                sb.append(obj.toString());

            i++;
        }
        return sb.toString();
    }

    public static String merge(Object[] strings, String delimiter) {
        if (strings == null)
            return "";

        StringBuilder sb = new StringBuilder(200);
        for (int i = 0; i < strings.length; i++) {
            if (i != 0)
                sb.append(delimiter);

            if (strings[i] != null)
                sb.append(strings[i]);
        }
        return sb.toString();
    }

    public static String prefixMatchOneExactly(String[] options, String matchString) {
        String matched = null;

        for (String option : options) {
            if (option.startsWith(matchString)) {
                // double-match
                if (matched != null)
                    return null;

                matched = option;
            }
        }

        return matched;
    }

    public static boolean checkCharacters(String str, String validCharacters) {
        // null check
        if (isEmpty(str))
            return true;

        // This function returns whether the string is composed entirely of the characters
        // contained in validCharacters
        for (int i = 0; i < str.length(); i++) {
            boolean matched = false;
            char currentChar = str.charAt(i);
            for (int j = 0; j < validCharacters.length(); j++) {
                // if character is valid go to the next one
                if (currentChar == validCharacters.charAt(j)) {
                    matched = true;
                    break;
                }
            }

            // if this character wasn't matched return false
            if (!matched)
                return false;
        }

        return true;
    }

    public static boolean checkUpperAndLower(String str) {
        boolean upper = false;
        boolean lower = false;
        // null check
        if (isEmpty(str)) {
            return true;
        }
        //For upper case ascii lies between 65 and 90
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 64 && str.charAt(i) < 91) {
                upper = true;
                break;
            }
        }

        //For lower case ascii lies between 97 and 122
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) > 96 && str.charAt(i) < 123) {
                lower = true;
                break;
            }
        }

        return upper && lower;
    }

    public static boolean checkSpecialCharacters(String str, String validSpecialChars) {
        // null check
        if (isEmpty(str)) {
            return true;
        }
        for (int j = 0; j < str.length(); j++) {
            //For upper case ascii lies between 65 and 91
            for (int i = 0; i < validSpecialChars.length(); i++) {
                if (str.charAt(j) == validSpecialChars.charAt(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String stripInvalidCharacters(String str, String validCharacters) {
        // This function removes all non valid characters from the digits
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // loop over valid chars
            for (int j = 0; j < validCharacters.length(); j++) {
                // if a character matches a valid char add it to the buffer
                if (currentChar == validCharacters.charAt(j)) {
                    sb.append(currentChar);
                    break;
                }
            }
        }

        return sb.toString();
    }

    public static String padLeft(String str, int padLength) {
        return padLeft(str, padLength, ' ');
    }

    public static String padFillLeft(String str, int padLength, char padChar) {
        if (str.length() >= padLength)
            return str;

        StringBuilder sb = new StringBuilder(padLength);

        for (int i = 0; i < padLength - str.length(); i++) {
            sb.append(padChar);
        }

        sb.append(str);

        return sb.toString();
    }

    public static String padFillRight(String str, int padLength, char padChar) {
        if (str.length() >= padLength)
            return str;

        StringBuilder sb = new StringBuilder(padLength);

        sb.append(str);

        for (int i = 0; i < padLength - str.length(); i++) {
            sb.append(padChar);
        }

        return sb.toString();
    }

    public static String padLeft(String str, int padLength, char padChar) {
        if (padLength < 0)
            throw new IllegalArgumentException("Invalid padLength passed in: " + padLength);

        char[] padding = new char[padLength];

        for (int i = 0; i < padLength; i++) {
            padding[i] = padChar;
        }

        return new String(padding) + str;
    }

    public static String padRight(String str, int padLength) {
        return padRight(str, padLength, ' ');
    }

    public static String padRight(String str, int padLength, char padChar) {
        if (padLength < 0)
            throw new IllegalArgumentException("Invalid padLength passed in: " + padLength);

        char[] padding = new char[padLength];

        for (int i = 0; i < padLength; i++) {
            padding[i] = padChar;
        }

        return str + new String(padding);
    }

    //convert a camel string to lower case segments separated by dots '.'
    public static String camelCaseToDots(String camelString) {
        return camelCaseSeparate(camelString, ".").toLowerCase();
    }

    //convert a camel string to lower case segments separated by underscores
    public static String camelCaseToUnderScore(String str) {
        return camelCaseSeparate(str, "_").toLowerCase();
    }

    public static String[] camelCaseSplit(String str) {
        if (isEmpty(str))
            return new String[0];

        List<String> strList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            // for an upper case that is not the first character insert an underscore
            if (Character.isUpperCase(str.charAt(i)) && i != 0) {
                strList.add(sb.toString());

                sb.setLength(0);
            }

            sb.append(str.charAt(i));
        }

        strList.add(sb.toString());

        return strList.toArray(new String[strList.size()]);
    }

    public static String camelCaseSeparate(String str, String separator) {
        return merge(camelCaseSplit(str), separator);
    }

    public static String underScoreToCamelCase(String str) {
        // Loop through turning, e.g. evt_id -> evtId

        if (isEmpty(str))
            return "";

        StringBuilder sb = new StringBuilder();
        boolean upperNext = true;
        for (int i = 0; i < str.length(); i++) {
            // Get the next character
            char c = str.charAt(i);

            // If an underscore then ignore but set upperNext
            if (c == '_') {
                upperNext = true;
            } else {
                // after an underscore or first character
                sb.append(upperNext ? Character.toUpperCase(c) : c);
                upperNext = false;
            }
        }
        return sb.toString();
    }

    public static String left(String str, int leftCount) {
        if (leftCount < 0)
            throw new IllegalArgumentException("Invalid leftCount passed in: " + leftCount);
        if (leftCount >= str.length())
            return str;
        return str.substring(0, leftCount);
    }

    public static String right(String str, int rightCount) {
        if (rightCount < 0)
            throw new IllegalArgumentException("Invalid rightCount passed in: " + rightCount);
        if (rightCount >= str.length())
            return str;
        return str.substring(str.length() - rightCount);
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static String replace(String str, String match, String replace) {
        int index = str.indexOf(match);

        // optimise for no match
        if (index == -1)
            return str;

        int currentIndex = 0;

        StringBuilder sb = new StringBuilder(str.length());

        while (index != -1) {
            // copy all the chars from the source before the match string
            for (int i = currentIndex; i < index; i++) {
                sb.append(str.charAt(i));
            }

            sb.append(replace);

            // reset the current index to the last index skipping over the match string
            currentIndex = index + match.length();

            index = str.indexOf(match, currentIndex);
        }

        // copy the last part of the string in, after the last match
        for (int i = currentIndex; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    // split a string into equal size chunks
    // passing in null or empty string returns an empty array
    public static String[] splitEvery(String str, int splitLength) {
        if (isEmpty(str))
            return new String[0];

        List<String> strs = new ArrayList<>();

        int position = 0;

        while (position < str.length()) {
            int endPosition = Math.min(str.length(), position + splitLength);

            strs.add(str.substring(position, endPosition));

            position = endPosition;
        }

        return strs.toArray(new String[0]);
    }

    // character version of split
    // might be faster than than a single char string
    public static String[] split(String str, char delimiter) {
        // Short-cut empty or null strings
        if (isEmpty(str))
            return new String[0];

        List<String> strs = new ArrayList<>();

        int currentIndex = 0;
        int nextIndex = str.indexOf(delimiter);

        while (nextIndex != -1) {
            // add the string
            strs.add(str.substring(currentIndex, nextIndex));

            // move the read ptr to the character beyond the delimiter
            currentIndex = nextIndex + 1;

            // find the next delimiter
            nextIndex = str.indexOf(delimiter, currentIndex);
        }

        // add the last string after the last delimiter
        strs.add(str.substring(currentIndex));

        return strs.toArray(new String[0]);
    }

    // string version of split
    public static String[] split(String str, String delimiter) {
        // Validate the delimiter
        if ((delimiter == null) || (delimiter.length() == 0))
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE);

        // Short-cut empty or null strings
        if (isEmpty(str))
            return new String[0];

        // Single char string, call the faster single char version
        if (delimiter.length() == 1) {
            return split(str, delimiter.charAt(0));
        }

        List<String> strs = new ArrayList<>();

        int currentIndex = 0;
        int nextIndex = str.indexOf(delimiter);

        while (nextIndex != -1) {
            // add the string
            strs.add(str.substring(currentIndex, nextIndex));

            // move the read ptr to the character beyond the delimiter
            currentIndex = nextIndex + delimiter.length();

            // find the next delimiter
            nextIndex = str.indexOf(delimiter, currentIndex);
        }

        // add the last string after the last delimiter
        strs.add(str.substring(currentIndex));

        return strs.toArray(new String[0]);
    }

    public static List<String> splitToList(String str, String delimiter) {
        // Validate the delimiter
        if ((delimiter == null) || (delimiter.length() == 0))
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE);

        // Short-cut empty or null strings
        if (isEmpty(str))
            return new ArrayList<>();

        List<String> strs = new ArrayList<>();

        int currentIndex = 0;
        int nextIndex = str.indexOf(delimiter);

        while (nextIndex != -1) {
            // add the string
            strs.add(str.substring(currentIndex, nextIndex));

            // move the read ptr to the character beyond the delimiter
            currentIndex = nextIndex + delimiter.length();

            // find the next delimiter
            nextIndex = str.indexOf(delimiter, currentIndex);
        }

        // add the last string after the last delimiter
        strs.add(str.substring(currentIndex));

        return strs;
    }

    public static Set<String> splitToSet(String str, String delimiter) {
        // Validate the delimiter
        if ((delimiter == null) || (delimiter.length() == 0))
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE);

        // Short-cut empty or null strings
        if (isEmpty(str))
            return new HashSet<>();

        Set<String> strs = new HashSet<>();

        int currentIndex = 0;
        int nextIndex = str.indexOf(delimiter);

        while (nextIndex != -1) {
            // add the string
            strs.add(str.substring(currentIndex, nextIndex));

            // move the read ptr to the character beyond the delimiter
            currentIndex = nextIndex + delimiter.length();

            // find the next delimiter
            nextIndex = str.indexOf(delimiter, currentIndex);
        }

        // add the last string after the last delimiter
        strs.add(str.substring(currentIndex));

        return strs;
    }

    // string version of split
    public static String[] splitMergeDelimiter(String str, String delimiter) {
        // Validate the delimiter
        if ((delimiter == null) || (delimiter.length() == 0))
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE);

        // Short-cut empty or null strings
        if (isEmpty(str))
            return new String[0];

        List<String> strs = new ArrayList<>();

        int currentIndex = 0;
        int nextIndex = str.indexOf(delimiter);

        while (nextIndex != -1) {
            // if the next delimiter is at the start of the string then skip it
            if (nextIndex == currentIndex) {
                currentIndex += delimiter.length();

                nextIndex = str.indexOf(delimiter, currentIndex);

                continue;
            }

            // add the string
            strs.add(str.substring(currentIndex, nextIndex));

            // move the read ptr to the character beyond the delimiter
            currentIndex = nextIndex + delimiter.length();

            // find the next delimiter
            nextIndex = str.indexOf(delimiter, currentIndex);
        }

        // add the last string after the last delimiter
        strs.add(str.substring(currentIndex));

        return strs.toArray(new String[0]);
    }

    public static int findPositionOfCamelCaseSuffix(String str, int suffixWordCount) {
        // Validate the word count
        if (suffixWordCount <= 0)
            throw new IllegalArgumentException("The suffixWordCount must be greater than 0");

        int upperCount = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (Character.isUpperCase(str.charAt(i))) {
                upperCount++;

                if (upperCount == suffixWordCount)
                    return i;
            }
        }

        // No match
        return -1;
    }

    public static String getCamelCaseSuffix(String str, int suffixWordCount) {
        // Find the position
        int position = StringHelper.findPositionOfCamelCaseSuffix(str, suffixWordCount);

        // Either return empty if not found, or the suffix
        return (position == -1 ? "" : str.substring(position));
    }

    public static String removeCamelCaseSuffix(String str, int suffixWordCount) {
        // Find the position
        int position = StringHelper.findPositionOfCamelCaseSuffix(str, suffixWordCount);

        // Either return empty if not found, or the string minus the suffix
        return (position == -1 ? "" : str.substring(0, position));
    }

    public static int nthIndexOf(String str, char ch, int nthInstance) {
        if (str == null)
            throw new NullPointerException("String is null");

        int instanceCount = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                instanceCount++;
                if (instanceCount == nthInstance) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int nthIndexOfReverse(String str, char ch, int nthInstance) {
        if (str == null)
            throw new NullPointerException("String is null");

        int instanceCount = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ch) {
                instanceCount++;
                if (instanceCount == nthInstance) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static String reverse(String str) {
        StringBuilder reverse = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            reverse.append(str.charAt(i));
        return reverse.toString();
    }

    public static String fill(char ch, int count) {
        char[] chArr = new char[count];
        Arrays.fill(chArr, ch);
        return new String(chArr);
    }

    public static String firstWord(String str) {
        str = str.trim();
        int index = str.indexOf(' ');

        if (index == -1) {
            return str;
        }
        return str.substring(0, index);
    }

    public static String consumeFirstWord(String str) {
        str = str.trim();
        int index = str.indexOf(' ');

        if (index == -1) {
            return "";
        }
        return str.substring(index).trim();
    }

    public static String byteArrayTo255String(byte[] b) {
        char[] arr2 = new char[b.length];

        for (int i = 0; i < b.length; i++) {
            arr2[i] = (char) (b[i] + 128);
        }

        return new String(arr2);
    }

    public static String escapeSpecialCharacters(String str) {
        StringBuilder strBuffer = new StringBuilder();
        if (null == str)
            return strBuffer.toString();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '"':
                    strBuffer.append("\\&quot;");
                    break;
                case '&':
                    strBuffer.append("&amp;");
                    break;
                case '<':
                    if (str.charAt(i + 1) == ' ')
                        strBuffer.append("&lt;");
                    else
                        strBuffer.append(c);
                    break;

                default:
                    strBuffer.append(c);
            }
        }
        return strBuffer.toString();

    }

    public static byte[] encodeUTF8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Could not find UTF-8 encoding");
        }
    }

    public static String consumeFromStringBuilder(StringBuilder sb, int length) {
        String consumed = sb.substring(0, length);

        sb.delete(0, length);

        return consumed;
    }

    public static String substringLength(String str, int index, int length) {
        int indexTo = (index + length);
        return str.substring(index, indexTo);
    }

    public static String removeLeft(String str, int leftRemove) {
        if (leftRemove >= str.length())
            return "";

        return str.substring(leftRemove);
    }

    public static String removeRight(String str, int rightRemove) {
        if (rightRemove >= str.length())
            return "";

        return str.substring(0, str.length() - rightRemove);
    }

    public static String substringUpTo(String str, char ch) {
        int index = str.indexOf(ch);

        if (index == -1)
            return str;

        return str.substring(0, index);
    }

    public static String substringDownTo(String str, char ch) {
        int index = str.lastIndexOf(ch);

        if (index == -1)
            return str;

        return str.substring(index + 1);
    }

    public static String parse(String baseMessage, String placeholderString, Object[] args) {
        // Loop around all the parameters and replace the '%x' placedholders
        // with the corresponding entries in the args array (from %1 to %x)
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                baseMessage = baseMessage.replace(placeholderString + (i + 1), "NULL");
            } else {
                baseMessage = baseMessage.replace(placeholderString + (i + 1), args[i].toString());
            }
        }
        return baseMessage;
    }

    public static Pattern getReplaceAllPattern(String pattern) {
        // initialise the reg exp pattern objects
        String patternString = pattern;

        // add ^ and $ to ensure complete matching when we do the replacement
        if (!patternString.startsWith("^"))
            patternString = "^" + patternString;

        if (!patternString.endsWith("$"))
            patternString = patternString + "$";

        // return the compiled pattern
        return Pattern.compile(patternString);
    }

    public static String toUpperCase(String str) {
        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 'a' + 'A');
                sb.setCharAt(i, ch);
            }
        }

        return sb.toString();
    }

    public static String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - 'A' + 'a');
                sb.setCharAt(i, ch);
            }
        }

        return sb.toString();
    }

    public static String token(String str, char delim, int token) {
        int beginIndex = 0;

        // find the start of the substring
        if (token != 0) {
            beginIndex = nthIndexOf(str, delim, token);

            // if start index not found then no string match
            if (beginIndex == -1)
                return null;

            // move the begin index past the delimiter
            beginIndex++;
        }

        // find the end index
        int endIndex = str.indexOf(delim, beginIndex);

        // set to end of string if not matched
        if (endIndex == -1)
            endIndex = str.length();

        return str.substring(beginIndex, endIndex);
    }

    public static String removeWhitespace(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n')
                continue;

            sb.append(ch);
        }

        return sb.toString();
    }

    public static String quoteSingle(Object str) {
        return "'" + str + "'";
    }

    public static String quoteDouble(Object str) {
        return "\"" + str + "\"";
    }

    public static String upperFirstChar(String str) {
        if (isEmpty(str))
            return str;

        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String lowerFirstChar(String str) {
        if (isEmpty(str))
            return str;

        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    public static boolean hasLettersAndDigits(String password) {
        boolean hasLetter = false;
        boolean hasDigit = false;

        // check each character for at least one alpha and one numeric
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                hasLetter = true;
            }

            if (Character.isDigit(password.charAt(i)))
                hasDigit = true;

            // if we have detected both a letter and digit then stop
            if (hasLetter && hasDigit)
                break;
        }

        // if we were missing either a letter or digit character then fail
        return (hasLetter && hasDigit);
    }

    public static String mergeBinaryStrings(String val1, String val2) {
        // sanity check
        if (val1.length() != val2.length())
            throw new RuntimeException("StringHelper.MergeBinaryStrings passed two strings of unequal length");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < val1.length(); i++)
            sb.append(val1.charAt(i) == 'Y' ? val1.charAt(i) : val2.charAt(i));

        return sb.toString();
    }

    public static String repeat(String stringToBeRepeated, int numberOfTimes) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < numberOfTimes; i++)
            temp.append(stringToBeRepeated);

        return temp.toString();
    }

    public static String concatStrings(String... str1) {
        StringBuilder sb = new StringBuilder();

        for (String str : str1) {
            sb.append(str);
        }

        return sb.toString();
    }

    public static boolean checkWhiteSpace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }

	/* wrap the word if it more than 100 characters long and put - at the end ,to the continuation of word in next line ,
	 currently limit is set to 100 characters*/

    public static String getWrappedString(String str) {
        final int LIMIT = 100;

        if (isEmpty(str) || str.length() < LIMIT) {
            return str;
        }

        int i = 0;
        final String SPACE = " ";
        final String HYPHEN_SPACE = "- ";
        String[] words = str.split(SPACE);
        StringBuilder message = new StringBuilder();

        while (i < words.length) {
            if (words[i].length() > LIMIT) {
                words[i] = words[i].substring(0, LIMIT) + HYPHEN_SPACE + getWrappedString(words[i].substring(LIMIT));
            }
            message.append(words[i] + SPACE);
            i++;
        }

        return message.toString();
    }

    public static String getShortHandString(String str) {
        return getShortHandString(str, 80);
    }

    public static String getShortHandString(String str, int length) {
        if (!isEmpty(str) && str.length() > length) {
            str = str.substring(0, length);
            //need to use proper ellipses i.e &hellip; need to see what is problem with proper usage.
            str = str.concat("...");

        }
        return str;
    }

    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != ',') {
                return false;
            }
        }
        return true;
    }

    public static boolean hasNDigits(String str, int n) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)))
                count++;

            // if we have n digits then stop
            if (count >= n)
                return true;
        }
        return false;
    }

    public static boolean hasNAlphabets(String str, int n) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i)))
                count++;

            // if we have n letters then stop
            if (count >= n)
                return true;
        }
        return false;
    }

    public static boolean hasNLowerCase(String str, int n) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i)))
                count++;

            // if we have n upper case letters then stop
            if (count >= n)
                return true;
        }
        return false;
    }

    public static boolean hasNUpperCase(String str, int n) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i)))
                count++;

            // if we have n lower case letters then stop
            if (count >= n)
                return true;
        }
        return false;
    }

    public static boolean hasNSpecialCharacters(String str, String validSpecialChars, int n) {
        int count = 0;
        for (int j = 0; j < str.length(); j++) {
            for (int i = 0; i < validSpecialChars.length(); i++) {
                if (str.charAt(j) == validSpecialChars.charAt(i))
                    count++;
            }

            // if we have n special characters then stop
            if (count >= n)
                return true;
        }
        return false;
    }
}
