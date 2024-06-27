package school.hei;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
public class CheckIn {
    private final Employee employee;
    private Map<LocalDate, WorkShift> workShift;

    public CheckIn(Employee employee) {
        this.employee = employee;
        this.workShift = new HashMap<>();
    }

    public void addDayWork(LocalDate date, WorkShift period) {
        workShift.put(date, period);
    }

    public Salary calculateSalary(Calendar calendar) {
        double grossSalary = 0.0;
        Double hourlyRate = employee.category().getHourlyRate();
        int hoursPerDay = 10;
        double nightMaj = 1.3;
        double sundayMaj = 0.4;
        double holidayMaj = 1.5;

        for (LocalDate date : workShift.keySet()) {
            WorkShift shift = workShift.get(date);
            boolean isHoliday = calendar.getHolidays().contains(date);
            boolean isSunday = date.getDayOfWeek() == DayOfWeek.SUNDAY;

            switch (shift) {
                case day:
                    if (isHoliday) {
                        grossSalary += hourlyRate * hoursPerDay * holidayMaj;
                    } else {
                        grossSalary += hourlyRate * hoursPerDay;
                    }
                    break;
                case night:
                    if (isHoliday) {
                        grossSalary += hourlyRate * hoursPerDay * nightMaj * holidayMaj;
                    } else {
                        grossSalary += hourlyRate * hoursPerDay * nightMaj;
                    }
                    break;
                case both:
                    if (isHoliday) {
                        grossSalary += hourlyRate * hoursPerDay * holidayMaj;
                        grossSalary += hourlyRate * hoursPerDay * nightMaj * holidayMaj;
                    } else {
                        grossSalary += hourlyRate * hoursPerDay;
                        grossSalary += hourlyRate * hoursPerDay * nightMaj;
                    }
                    break;
            }
            if(isSunday){
                switch (shift){
                    case day :
                        grossSalary += hourlyRate * hoursPerDay * sundayMaj;
                        break;
                    case night:
                        grossSalary += hourlyRate * hoursPerDay * nightMaj * sundayMaj;
                        break;
                    case both:
                        grossSalary += hourlyRate * hoursPerDay * sundayMaj;
                        grossSalary += hourlyRate * hoursPerDay * nightMaj * sundayMaj;
                }
            }
        }
        return new Salary(grossSalary);
    }

    public int getHoursOfWork() {
        int totalHours = 0;

        for (LocalDate date : workShift.keySet()) {
            WorkShift period = workShift.get(date);
            switch (period) {
                case day -> totalHours += 10;
                case night -> totalHours += 14;
                case both -> totalHours += 24;
            }
        }
        return totalHours;
    }

}
