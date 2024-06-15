package models.enums;

public enum Section {PATIENTS("Пациентам"),
    DOCTORS("Врачам"),
    FRANCHISING("Франчайзинг"),
    CORPORATE_CLIENTS("Корпоративным клиентам"),
    PRESS("Прессе");

    private final String displayName;

    Section(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Section fromString(String text) {
        for (Section section : Section.values()) {
            if (section.displayName.equalsIgnoreCase(text)) {
                return section;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }
}