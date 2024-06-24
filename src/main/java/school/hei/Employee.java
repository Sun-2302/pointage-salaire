package school.hei;

import lombok.Getter;
import school.hei.Category.Category;

import java.util.Date;

public record Employee(String firstName, String lastName, String serialNumber, Date birthDate, Date hiringDate,
                       Date contractEndDate, Category category) {
}
