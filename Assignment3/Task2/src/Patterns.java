import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/* Class contains patterns and regex for processing input*/

public class Patterns {
    final static String yearReg = "(201[6-9]|202[0-9])";
    final static String monthReg = "(0[1-9]|1[0-2])";
    final static String dayReg = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])";
    final static String hourReg = "([0-1][0-9]|2[0-3])";
    final static String minReg = "([0-5][0-9])";
    final static String REGEX = yearReg + '-' + monthReg + '-' +
            dayReg + 'T' + hourReg + ':' + minReg;

    final static Pattern PATTERN_DATE = Pattern.compile(REGEX);
    final static Pattern PATTERN_COST = Pattern.compile("[0-9]+");
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    private Patterns(){}
}
