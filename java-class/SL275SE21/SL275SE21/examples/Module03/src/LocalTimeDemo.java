import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalTimeDemo {

    public static void main(String[] args) {
        // now, of
        LocalTime now = LocalTime.now();
        LocalTime time01 = LocalTime.of(13, 10);
        LocalTime time02 = LocalTime.of(13, 10, 30);
        LocalTime time03 = LocalTime.of(13, 10, 30, 20);
        
        System.out.println("\n===== now, of: ");
        System.out.println(now);
        System.out.println(time01);
        System.out.println(time02);
        System.out.println(time03);
        
        // plusHours, plusMinutes, plusSeconds, plusNanos
        LocalTime time04 = now.plusHours(1)
                              .plusMinutes(1)
                              .plusSeconds(1)
                              .plusNanos(1);
        
        System.out.println("\n===== plusHours, plusMinutes, plusSeconds, plusNanos: ");
        System.out.println(time04);
        
        // minusHours, minusMinutes, minusSeconds, minusNanos
        LocalTime time05 = now.minusHours(1)
                              .minusMinutes(1)
                              .minusSeconds(1)
                              .minusNanos(1);
        
        System.out.println("\n===== minusHours, minusMinutes, minusSeconds, minusNanos: ");
        System.out.println(time05);
        
        // plus, minus
        LocalTime time06 = now.plus(1, ChronoUnit.HOURS);
        LocalTime time07 = now.minus(1, ChronoUnit.HOURS);
        
        System.out.println("\n===== plus, minus: ");
        System.out.println(time06);
        System.out.println(time07);
        
        // withHour, withMinute, withSecond, withNano
        LocalTime time08 = now.withHour(3);
        LocalTime time09 = now.withMinute(3);
        LocalTime time10 = now.withSecond(3);
        LocalTime time11 = now.withNano(3);
        
        System.out.println("\n===== withHour, withMinute, withSecond, withNano: ");
        System.out.println(time08);
        System.out.println(time09);
        System.out.println(time10);
        System.out.println(time11);
        
        // getHour, getMinute, getSecond, getNano
        System.out.println("\n===== getHour, getMinute, getSecond, getNano: ");
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println(now.getNano());
        
        // toSecondOfDay, toNanoOfDay
        System.out.println("\n===== toSecondOfDay, toNanoOfDay: ");
        System.out.println(now.toSecondOfDay());
        System.out.println(now.toNanoOfDay());
        
        // isBefore, isAfter
        System.out.println("\n===== isBefore, isAfter: ");
        System.out.println(time01.isBefore(time02));
        System.out.println(time01.isAfter(time02));

    }

}
