package school.hei;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Salary {
    private final Double grossSalary;
    private final Double netSalary;

    public Salary(Double grossSalary) {
        this.grossSalary = grossSalary;
        this.netSalary = grossSalary * 0.8;
    }
}
