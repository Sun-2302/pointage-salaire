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
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private List<LocalDate> workingDays;
    private List<LocalDate> holidays;

    public Calendar(LocalDate beginDate, LocalDate endDate, List<LocalDate> holidays) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.holidays = holidays;
        this.workingDays = workingDays();
    }

    public Calendar(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.holidays = new ArrayList<>();
        this.workingDays = workingDays();
    }

    private List<LocalDate> workingDays() {
        List<LocalDate> dayOfTheMonth = new ArrayList<>();

        for (LocalDate date = beginDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dayOfTheMonth.add(date);
        }

        return dayOfTheMonth.stream()
                .filter(day -> !holidays.contains(day))
                .collect(toList());
    }

    public void addHoliday(LocalDate holiday){
        holidays.add(holiday);
        workingDays.remove(holiday);
    }
}
