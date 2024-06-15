package steps.definitions;

import io.cucumber.java.ParameterType;
import models.enums.Section;

public class ParameterDefinitions {
    @ParameterType("Пациентам|Врачам|Франчайзинг|Корпоративным клиентам|Прессе")
    public Section section(String section) {
        return Section.fromString(section);
    }
}
