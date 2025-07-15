package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class DateUtil {

    public static final Set<LocalDate> JOURS_FERIES = Set.of(
        LocalDate.of(2025, 1, 1),   
        LocalDate.of(2025, 3, 29),  
        LocalDate.of(2025, 5, 1),   
        LocalDate.of(2025, 6, 26),  
        LocalDate.of(2025, 12, 25)  
    );

    public static boolean isJourNonOuvre(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY
            || date.getDayOfWeek() == DayOfWeek.SUNDAY
            || JOURS_FERIES.contains(date);
    }

    public static LocalDate prochainJourOuvre(LocalDate date) {
        while (isJourNonOuvre(date)) {
            date = date.plusDays(1);
        }
        return date;
    }
}
