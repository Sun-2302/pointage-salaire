package school.hei;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.Month.JUNE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarTest {
    @Test
    public void calendar_test(){
        var year = 2024;
        var month = JUNE;

        var begin = LocalDate.of(year,month,1);
        var end = LocalDate.of(year,month,30);

        var holidays = new ArrayList<LocalDate>();
        holidays.add(LocalDate.of(year, month, 17));
        holidays.add(LocalDate.of(year, month, 25));

        var june = new Calendar(begin, end, holidays);
        june.addHoliday(LocalDate.of(year, month, 26));


        assertEquals(3,june.getHolidays().size());
        assertEquals(27,june.getWorkingDays().size());
    }

}