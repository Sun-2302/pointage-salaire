package school.hei.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract sealed class Category permits
Normal, SecurityGuard{
    private final CategoryName name;
    private final int hourPerWeek;
    private final Double salaryPerWeek;
    private final Double hourlyRate;
    private Double compensation;

    public Category(CategoryName name, int hourPerWeek, Double salaryPerWeek, Double compensation) {
        this.name = name;
        this.hourPerWeek = hourPerWeek;
        this.salaryPerWeek = salaryPerWeek;
        this.hourlyRate = salaryPerWeek/hourPerWeek;
        this.compensation = compensation;
    }
}
