import java.util.regex.Pattern;

public class Main {
    public static boolean checkRegex(Pattern p, String s) {
        return p.matcher(s).matches();
    }
    public static void main(String[] args) {
        // Pattern 1: Email address
        Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        String positiveEmail = "shi.hongj@northeastern.edu";
        String negativeEmail = "hongji@ex.y";
        System.out.println(pattern1.matcher(positiveEmail).matches());
        System.out.println(pattern1.matcher(negativeEmail).matches());

        // Pattern 2: Phone Number
        Pattern pattern2 = Pattern.compile("^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$");
        String positivePhone = "+8615201461831";
        String negativePhone = "4433ij234";
        System.out.println(pattern2.matcher(positivePhone).matches());
        System.out.println(pattern2.matcher(negativePhone).matches());

        // Pattern 3: IPV4 address
        Pattern pattern3 = Pattern.compile("\\b([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\b");
        String positiveIPV4Address = "79.234.34.36";
        String negativeIPV4Address = "5E.d4.55.F1";
        System.out.println(checkRegex(pattern3,positiveIPV4Address));
        System.out.println(checkRegex(pattern3,negativeIPV4Address));

        // Pattern 4: URL
        Pattern pattern4 = Pattern.compile("^(https?:\\/\\/)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}(\\/\\S*)?$");
        String positiveURL = "https://www.westsea.tech";
        String negativeURL = "ftp://23.44.49.124";
        System.out.println(checkRegex(pattern4, positiveURL));
        System.out.println(checkRegex(pattern4, negativeURL));

        // Pattern 5: Date (yyyy-mm-dd)
        Pattern pattern5 = Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");
        String positiveDate = "2020-01-19";
        String negativeDate = "1999/09/23";
        System.out.println(checkRegex(pattern5, positiveDate));
        System.out.println(checkRegex(pattern5, negativeDate));
    }

}