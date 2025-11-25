import java.time.*;

public class InstantsDurationsAndPeriods {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate foolsDay = LocalDate.of(2019, Month.APRIL, 1);
        Instant timeStamp = Instant.now();
        int nanoSecondsFromLastSecond = timeStamp.getNano();
        Period howLong = Period.between(foolsDay, today);
        Duration twoHours = Duration.ofHours(2);
        long seconds = twoHours.minusMinutes(15).getSeconds();
        int days = howLong.getDays();
    }
}
