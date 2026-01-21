package calories_control.imc.domain;

public enum IMCCategory {
    UNDERWEIGHT,
    NORMAL,
    OVERWEIGHT,
    OBESITY_CLASS_ONE,
    OBESITY_CLASS_TWO,
    OBESITY_CLASS_TREE;

    public static IMCCategory category(double imcValue) {
        if (imcValue < 18.5) return UNDERWEIGHT;
        if (imcValue >= 18.5 && imcValue < 25) return NORMAL;
        if (imcValue >= 25 && imcValue < 30) return OVERWEIGHT;
        if (imcValue >= 30 && imcValue < 35) return OBESITY_CLASS_ONE;
        if (imcValue >= 35 && imcValue < 40) return OBESITY_CLASS_TWO;
        return OBESITY_CLASS_TREE;

    }
}