import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateAndTime {
    public static void main(String[] args) {
        ZoneId london = ZoneId.of("Europe/London");
        ZoneId la = ZoneId.of("America/Los_Angeles");
        LocalDateTime someTime = LocalDateTime.of(2019, Month.APRIL, 1, 07, 14);
        ZonedDateTime londonTime = ZonedDateTime.of(someTime, london);
        ZonedDateTime laTime = londonTime.withZoneSameInstant(la);
    }
}
