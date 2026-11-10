package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Хэсэг A: хэвийн урсгалын (happy path) тестүүд.
 * Хэсэг B: CFG-ийн хамрагдаагүй ирмэгүүдийг хаах тестүүд (Branch/Line 100%).
 * Хэсэг C: gpa() давталтын 0 / 1 / 2+ удаагийн тестүүд.
 * Хэсэг D: letterGrade() зааг утгын тестүүд.
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

    // ---------------- Хэсэг B: letterGrade ----------------

    @Test
    @DisplayName("letterGrade(-1) -> IllegalArgumentException")
    void letterGradeNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    @DisplayName("letterGrade(101) -> IllegalArgumentException")
    void letterGradeAbove100Throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    @DisplayName("letterGrade(75) -> C")
    void letterGradeC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    @DisplayName("letterGrade(65) -> D")
    void letterGradeD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    @DisplayName("letterGrade(50) -> F")
    void letterGradeF() {
        assertEquals("F", calc.letterGrade(50));
    }

    // ---------------- Хэсэг B: gpa ----------------

    @Test
    @DisplayName("gpa(null) -> IllegalArgumentException")
    void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    @DisplayName("gpa([]) -> IllegalArgumentException")
    void gpaEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    @DisplayName("gpa([75, 65, 50]) -> (2+1+0)/3 = 1.0")
    void gpaOfCDandF() {
        assertEquals(1.0, calc.gpa(new double[]{75, 65, 50}), 1e-9);
    }

    // ---------------- Хэсэг B: scholarship ----------------

    @Test
    @DisplayName("scholarship(-0.1, 15, false) -> IllegalArgumentException")
    void scholarshipNegativeGpaThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 15, false));
    }

    @Test
    @DisplayName("scholarship(4.1, 15, false) -> IllegalArgumentException")
    void scholarshipGpaAbove4Throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 15, false));
    }

    @Test
    @DisplayName("scholarship(3.5, -1, false) -> IllegalArgumentException")
    void scholarshipNegativeCreditsThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.5, -1, false));
    }

    @Test
    @DisplayName("scholarship(3.9, 16, true) -> 0")
    void scholarshipViolation() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    @Test
    @DisplayName("scholarship(3.9, 12, false) -> 70 (gpa ok, credits < 15)")
    void scholarshipHighGpaLowCredits() {
        assertEquals(70, calc.scholarship(3.9, 12, false));
    }

    @Test
    @DisplayName("scholarship(3.5, 10, false) -> 50 (gpa >= 3.4, credits < 12)")
    void scholarshipMidGpaLowCredits() {
        assertEquals(50, calc.scholarship(3.5, 10, false));
    }

    @Test
    @DisplayName("scholarship(3.2, 20, false) -> 50")
    void scholarshipFifty() {
        assertEquals(50, calc.scholarship(3.2, 20, false));
    }

    @Test
    @DisplayName("scholarship(2.5, 20, false) -> 0")
    void scholarshipNone() {
        assertEquals(0, calc.scholarship(2.5, 20, false));
    }

    // ---------------- Хэсэг C: gpa() давталт 0 / 1 / 2+ ----------------
    // 0 удаа: gpaEmptyThrows (хоосон массив) ба gpaNullThrows (null) — Хэсэг B-д бичсэн.

    @Test
    @DisplayName("loop 1x: gpa([95]) -> 4.0")
    void gpaLoopOnce() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 1e-9);
    }

    @Test
    @DisplayName("loop 2+x: gpa([95, 85, 75, 65]) -> 2.5")
    void gpaLoopMany() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 1e-9);
    }

    // ---------------- Хэсэг D: letterGrade зааг утгууд ----------------

    @Test
    @DisplayName("boundary: letterGrade(60) -> D")
    void letterGradeBoundary60IsD() {
        assertEquals("D", calc.letterGrade(60));
    }

    @Test
    @DisplayName("boundary: letterGrade(59.99) -> F")
    void letterGradeBoundary5999IsF() {
        assertEquals("F", calc.letterGrade(59.99));
    }

    @Test
    @DisplayName("boundary: letterGrade(69.99) -> D")
    void letterGradeBoundary6999IsD() {
        assertEquals("D", calc.letterGrade(69.99));
    }

    @Test
    @DisplayName("boundary: letterGrade(0) -> F")
    void letterGradeBoundary0IsF() {
        assertEquals("F", calc.letterGrade(0));
    }

    @Test
    @DisplayName("boundary: letterGrade(100) -> A")
    void letterGradeBoundary100IsA() {
        assertEquals("A", calc.letterGrade(100));
    }

    @Test
    @DisplayName("boundary: letterGrade(89.99) -> B")
    void letterGradeBoundary8999IsB() {
        assertEquals("B", calc.letterGrade(89.99));
    }

    @Test
    @DisplayName("boundary: letterGrade(90) -> A")
    void letterGradeBoundary90IsA() {
        assertEquals("A", calc.letterGrade(90));
    }
}
