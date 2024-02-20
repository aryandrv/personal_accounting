package enums;

public enum TypeEnum {

    INCOME("Income"),
    COST("Cost");

    private final String value;

    private TypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static TypeEnum toEnum(String str) {
        if (str != null) {
            for (TypeEnum en : TypeEnum.values()) {
                if (en.toString().equals(str)) {
                    return en;
                }
            }
        }
        return null;
    }
}
