package model.spreadsheet;

import java.io.Serializable;

/**
 * A course has a List of GradeRanges. Each range corresponds to a letter
 * grade. A typical setup would be A (90, 100), B(80, 89), C(70, 79)
 * and so forth.
 *
 */
public class GradeRange implements Serializable {
    private static final long serialVersionUID = -3966549869734563187L;

    /**
     * The letter grade the GradeRange represents, ex A or A-, must not be null.
     */
    String letterGrade;

    /**
     * The low number of the GradeRange, must not be null.
     */
    Double low;

    /**
     * The high number of the GradeRange, must not be null.
     */
    Double high;
}
