import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

public class Main {
    public static void main(String args[]) {
        printBonusDatesBetween(0,110000);
    }
    public static void printBonusDatesBetween(int fromYear, int toYear) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

        StringBuilder sbDate = new StringBuilder();
        StringBuilder sbDateReversed = new StringBuilder();

        ArrayList<String> bonusDates = new ArrayList<>();

        for (int y = fromYear; y <= toYear; y++) {
            int daysPerYear = Year.isLeap(y) ? 366 : 365;

            for (int d = 1; d <= daysPerYear; d++) {
                int yearLen = 4;
                int currYearLen = (int) (Math.log10(y) + 1);

                sbDate.append(LocalDate.ofYearDay(y,d).format(dateFormat));

                if (yearLen < currYearLen) {
                    yearLen = currYearLen;
                    sbDate.deleteCharAt(0); // Removes leading '+' sign
                }

                sbDateReversed.append(sbDate).reverse();

                if (sbDate.compareTo(sbDateReversed) == 0) {
                    /* "20100102" -> "2010-01-02" */
                    sbDate.insert(yearLen, '-');
                    sbDate.insert(yearLen+3, '-');

                    bonusDates.add(sbDate.toString());
                }

                sbDate.delete(0, sbDate.length());
                sbDateReversed.delete(0, sbDateReversed.length());
            }
        }

        bonusDates.forEach(System.out::println);
    }
}

