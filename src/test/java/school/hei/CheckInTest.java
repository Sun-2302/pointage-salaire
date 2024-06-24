package school.hei;

import org.junit.jupiter.api.Test;
import school.hei.Category.SecurityGuard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.WorkShift.both;
import static school.hei.WorkShift.day;

public class CheckInTest {
    @Test
    public void checkIn_test(){
        //Calendar Instance
        var year = 2024;
        var month = JUNE;

        var holidays = new ArrayList<LocalDate>();
        holidays.add(LocalDate.of(year, month, 17));
        holidays.add(LocalDate.of(year, month, 25));
        holidays.add(LocalDate.of(year, month, 26));

        var june = new Calendar(year, month, holidays);

        //Create Rakoto SecurityGuard
        var securityGuard = new SecurityGuard(56, 100000.0, 20000.0);
        var rakoto = new Employee("Rakoto","Mahaliana","001", new Date(),new Date(), new Date(), securityGuard);

        var rakotoCheckIn = new CheckIn(rakoto);

        //Do checkIn for June
        for (LocalDate date : june.getWorkingDays()) {
            rakotoCheckIn.addDayWork(date, day);
        }

        //Work in holidays
        rakotoCheckIn.addDayWork(LocalDate.of(year, month, 17), day);
        rakotoCheckIn.addDayWork(LocalDate.of(year, month, 25), both);
        rakotoCheckIn.addDayWork(LocalDate.of(year, month, 26), both);

        Salary rakotoSalary = rakotoCheckIn.calculateSalary(june);

        assertEquals(328, rakotoCheckIn.getHoursOfWork());
        assertEquals(695714, Math.round(rakotoSalary.getGrossSalary()));
        assertEquals(Math.round(rakotoSalary.getNetSalary()),Math.round(rakotoSalary.getGrossSalary()*0.8));
    }
}
