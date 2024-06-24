package school.hei.Category;

import static school.hei.Category.CategoryName.*;

public final class Normal extends Category{
    public Normal(int hourPerWeek, Double salaryPerWeek, Double hourlyRate, Double compensation) {
        super(normal, hourPerWeek, salaryPerWeek, compensation);
    }
}
