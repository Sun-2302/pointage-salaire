package school.hei;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryTest {
    @Test
    public void salary_test(){
        var salary = new Salary(10000.0);

        assertEquals(salary.getGrossSalary()*0.8,salary.getNetSalary());
    }
}
