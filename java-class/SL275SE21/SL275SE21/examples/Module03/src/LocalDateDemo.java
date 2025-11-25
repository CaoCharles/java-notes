import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class LocalDateDemo {

    public static void main(String[] args) {
        // now, of
        LocalDate date = LocalDate.now();
        LocalDate date02 = LocalDate.of(2015, 8, 13);
        LocalDate date03 = LocalDate.of(2015, Month.AUGUST, 13);
        
        System.out.println("\n===== now, of: ");
        System.out.println(date);
        System.out.println(date02);
        System.out.println(date03);
        
        // plusDays, plusWeeks, plusMonths, plusYears
        LocalDate date04 = date.plusDays(1)
                               .plusWeeks(1)
                               .plusMonths(1)
                               .plusYears(1);
        System.out.println("\n===== plusDays, plusWeeks, plusMonths, plusYears: ");
        System.out.println(date04);
        
        // minusDays, minusWeeks, minusMonths, minusYears
        LocalDate date05 = date.minusDays(1)
                               .minusWeeks(1)
                               .minusMonths(1)
                               .minusYears(1);
        System.out.println("\n===== minusDays, minusWeeks, minusMonths, minusYears: ");
        System.out.println(date05);
        
        // plus, minus
        LocalDate date06 = date.plus(1, ChronoUnit.DAYS);
        LocalDate date07 = date.minus(1, ChronoUnit.DAYS);
        
        System.out.println("\n===== plus, minus: ");
        System.out.println(date06);
        System.out.println(date07);
        
        // withDayOfMonth, withDayOfYear, withMonth, withYear
        LocalDate date08 = date.withDayOfMonth(1);
        LocalDate date09 = date.withDayOfYear(256);
        
        System.out.println("\n===== withDayOfMonth, withDayOfYear, withMonth, withYear: ");
        System.out.println(date08);
        System.out.println(date09);
        
        // getDayOfMonth, getDayOfYear, getDayOfWeek
        System.out.println("\n===== getDayOfMonth, getDayOfYear, getDayOfWeek: ");
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfYear());
        System.out.println(date.getDayOfWeek());
        
        // getMonth, getMonthValue, getYear
        System.out.println("\n===== getMonth, getMonthValue, getYear: ");
        System.out.println(date.getMonth());
        System.out.println(date.getMonthValue());
        System.out.println(date.getYear());
        
        LocalDate date10 = LocalDate.of(1968, 11, 20);
        LocalDate date11 = LocalDate.of(1976, 8, 13);
        
        // until
        System.out.println("\n===== until: ");
        System.out.println(date10.until(date11));
        System.out.println(date10.until(date11, ChronoUnit.DAYS));
        
        // isBefore, isAfter
        System.out.println("\n===== isBefore, isAfter: ");
        System.out.println(date10.isBefore(date11));
        System.out.println(date10.isAfter(date11));
        
        // isLeapYear
        System.out.println("\n===== isLeapYear: ");
        System.out.println(date.isLeapYear());
        
    }

}
