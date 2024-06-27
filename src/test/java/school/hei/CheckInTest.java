package school.hei;

import org.junit.jupiter.api.Test;
import school.hei.Category.SecurityGuard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static school.hei.WorkShift.*;

public class CheckInTest {

    @Test
    public void hours_of_work_test(){
        var securityGuard = new SecurityGuard(70, 100000.0, 20000.0);
        var rakoto = new Employee("Rakoto","","001", new Date(),new Date(), new Date(), securityGuard);
        var rabe = new Employee("Rabe","","002", new Date(),new Date(), new Date(), securityGuard);

        var begin = LocalDate.of(2024,MAY,26);
        var end = LocalDate.of(2024,JULY,6);
        var calendar = new Calendar(begin, end);


        var rakotoCheckIn = new CheckIn(rakoto);
        var rabeCheckIn = new CheckIn(rabe);

        for (LocalDate date : calendar.getWorkingDays()) {
            rakotoCheckIn.addDayWork(date, day);
            rabeCheckIn.addDayWork(date, night);
        }

        assertEquals(420, rakotoCheckIn.getHoursOfWork());
        assertEquals(588, rabeCheckIn.getHoursOfWork());
    }

    @Test
    public void get_salary_test(){
        var securityGuard = new SecurityGuard(70, 100000.0, 20000.0);
        var rakoto = new Employee("Rakoto","","001", new Date(),new Date(), new Date(), securityGuard);
        var rabe = new Employee("Rabe","","002", new Date(),new Date(), new Date(), securityGuard);

        var begin = LocalDate.of(2024,MAY,26);
        var end = LocalDate.of(2024,JULY,6);
        var calendar = new Calendar(begin, end);


        var rakotoCheckIn = new CheckIn(rakoto);
        var rabeCheckIn = new CheckIn(rabe);

        for (LocalDate date : calendar.getWorkingDays()) {
            rakotoCheckIn.addDayWork(date, day);
            rabeCheckIn.addDayWork(date, night);
        }

        assertEquals(634_286, Math.rint(rakotoCheckIn.calculateSalary(calendar).getGrossSalary()));
        assertEquals(824_571, Math.rint(rabeCheckIn.calculateSalary(calendar).getGrossSalary()));
    }

    @Test
    public void get_salary_with_holiday_test(){
        var securityGuard = new SecurityGuard(70, 100000.0, 20000.0);
        var rakoto = new Employee("Rakoto","","001", new Date(),new Date(), new Date(), securityGuard);
        var rabe = new Employee("Rabe","","002", new Date(),new Date(), new Date(), securityGuard);

        var begin = LocalDate.of(2024,MAY,26);
        var end = LocalDate.of(2024,JULY,6);

        var ferie1 = LocalDate.of(2024, JUNE, 17);
        var ferie2 = LocalDate.of(2024, JUNE, 25);
        var ferie3 = LocalDate.of(2024, JUNE, 26);
        var holidays = new ArrayList<LocalDate>();
        holidays.add(ferie1);
        holidays.add(ferie2);
        holidays.add(ferie3);

        var calendar = new Calendar(begin, end, holidays);

        var rakotoCheckIn = new CheckIn(rakoto);
        var rabeCheckIn = new CheckIn(rabe);


        for (LocalDate date : calendar.getWorkingDays()) {
            rakotoCheckIn.addDayWork(date, day);
            rabeCheckIn.addDayWork(date, night);
        }

        rakotoCheckIn.addDayWork(ferie1, day);
        rakotoCheckIn.addDayWork(ferie2, day);
        rakotoCheckIn.addDayWork(ferie3, day);

        rabeCheckIn.addDayWork(ferie1, night);
        rabeCheckIn.addDayWork(ferie2, night);
        rabeCheckIn.addDayWork(ferie3, night);

        assertEquals(655_714, Math.rint(rakotoCheckIn.calculateSalary(calendar).getGrossSalary()));
        assertEquals(852_429, Math.rint(rabeCheckIn.calculateSalary(calendar).getGrossSalary()));
    }

    @Test
    public void rakoto_test(){
        var securityGuard = new SecurityGuard(70, 100000.0, 20000.0);
        var rakoto = new Employee("Rakoto","","001", new Date(),new Date(), new Date(), securityGuard);

        var begin = LocalDate.of(2024,MAY,26);
        var end = LocalDate.of(2024,JULY,6);

        var ferie = LocalDate.of(2024, JUNE, 26);
        var holidays = new ArrayList<LocalDate>();
        holidays.add(ferie);

        var calendar = new Calendar(begin, end, holidays);

        var rakotoCheckIn = new CheckIn(rakoto);

        for (int i = 1; i < calendar.getWorkingDays().size(); i++) {
            LocalDate date = calendar.getWorkingDays().get(i);
            rakotoCheckIn.addDayWork(date, day);
        }
        rakotoCheckIn.addDayWork(ferie, day);

        assertEquals(410, rakotoCheckIn.getHoursOfWork());
        assertEquals(621_429, Math.rint(rakotoCheckIn.calculateSalary(calendar).getGrossSalary()));
        assertEquals(497_143, Math.rint(rakotoCheckIn.calculateSalary(calendar).getNetSalary()));
    }

    @Test
    public void rabe_test(){
        var securityGuard = new SecurityGuard(70, 100000.0, 20000.0);
        var rabe = new Employee("Rabe","","002", new Date(),new Date(), new Date(), securityGuard);

        var begin = LocalDate.of(2024,MAY,26);
        var end = LocalDate.of(2024,JULY,6);

        var ferie = LocalDate.of(2024, JUNE, 26);
        var holidays = new ArrayList<LocalDate>();
        holidays.add(ferie);

        var calendar = new Calendar(begin, end, holidays);

        var rabeCheckIn = new CheckIn(rabe);


        for (LocalDate date : calendar.getWorkingDays()) {
            rabeCheckIn.addDayWork(date, night);
        }

        rabeCheckIn.addDayWork(ferie, night);

        var rabeSalary = rabeCheckIn.calculateSalary(calendar);

        assertEquals(588, rabeCheckIn.getHoursOfWork());
        assertEquals(833_857, Math.rint(rabeSalary.getGrossSalary()));
        assertEquals(667_086, Math.rint(rabeSalary.getNetSalary()));
    }
}
