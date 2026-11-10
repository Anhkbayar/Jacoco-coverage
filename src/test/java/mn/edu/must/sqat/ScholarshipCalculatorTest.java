package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Хэсэг A: зөвхөн хэвийн урсгалын (happy path) тестүүд.
 */
class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test
    @DisplayName("letterGrade(95) -> A")
    void letterGradeA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    @DisplayName("letterGrade(85) -> B")
    void letterGradeB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    @DisplayName("gpa([95, 85]) -> 3.5")
    void gpaOfAandB() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 1e-9);
    }

    @Test
    @DisplayName("scholarship(3.9, 16, false) -> 100")
    void scholarshipFull() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    @DisplayName("scholarship(3.5, 13, false) -> 70")
    void scholarshipSeventy() {
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }
}
