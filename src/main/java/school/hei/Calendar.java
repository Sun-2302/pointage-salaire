package school.hei;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
public class Calendar {
    private final int year;
    private final Month month;
    private List<LocalDate> workingDays;
    private List<LocalDate> holidays;

    public Calendar(int year, Month month, List<LocalDate> holidays) {
        this.year = year;
        this.month = month;
        this.holidays = holidays;
        this.workingDays = workingDays();
    }

    private List<LocalDate> workingDays() {
        List<LocalDate> dayOfTheMonth = new ArrayList<>();
        LocalDate debutDate = LocalDate.of(year, month, 1);
        LocalDate endDate = debutDate.withDayOfMonth(debutDate.lengthOfMonth());

        for (LocalDate date = debutDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dayOfTheMonth.add(date);
        }

        return dayOfTheMonth.stream()
                .filter(day -> !holidays.contains(day))
                .collect(toList());
    }
}
