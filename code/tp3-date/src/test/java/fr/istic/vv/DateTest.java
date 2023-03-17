package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class DateTest {

    // isValidDate

    private static Stream<Arguments> dateInfZero() {
        return Stream.of(
                Arguments.of(1, 3, -1),
                Arguments.of(1, -1, 2023),
                Arguments.of(-1, 2, 2023));
    }

    private static Stream<Arguments> dateEqZero() {
        return Stream.of(
                Arguments.of(1, 3, 0),
                Arguments.of(1, 0, 2023),
                Arguments.of(0, 2, 2023));
    }

    private static Stream<Arguments> isValidDateBlock3() {
        return Stream.of(
                Arguments.of(1, 1, 2024),
                Arguments.of(14, 3, 2024),
                Arguments.of(23, 5, 2024),
                Arguments.of(30, 7, 2024),
                Arguments.of(3, 8, 2024),
                Arguments.of(5, 10, 2024),
                Arguments.of(6, 12, 2024));
    }

    private static Stream<Arguments> isValidDateBlock4() {
        return Stream.of(
                Arguments.of(31, 4, 2023),
                Arguments.of(43, 6, 2023),
                Arguments.of(31, 9, 2023),
                Arguments.of(90, 11, 2023));
    }

    private static Stream<Arguments> isValidDateBlock5() {
        return Stream.of(
                Arguments.of(29, 2, 2024),
                Arguments.of(28, 2, 2023));
    }

    private static Stream<Arguments> isValidDateBlock6() {
        return Stream.of(
                Arguments.of(1, 15, 2024));
    }

    // nextDay
    private static Stream<Arguments> validDatesAndRegularYearNotAtTheEndOfTheMonth() {
        return Stream.of(
                Arguments.of(1, 1, 2022, 2, 1, 2022),
                Arguments.of(27, 2, 2022, 28, 2, 2022),
                Arguments.of(15, 7, 2022, 16, 7, 2022));
    }

    private static Stream<Arguments> validDatesInARegularYearAtTheEndOfTheMonth() {
        return Stream.of(
                Arguments.of(31, 1, 2022, 1, 2, 2022),
                Arguments.of(30, 4, 2022, 1, 5, 2022),
                Arguments.of(31, 12, 2022, 1, 1, 2023));
    }

    private static Stream<Arguments> validDatesInALeapYearNotAtTheEndOfFebruary() {
        return Stream.of(
                Arguments.of(23, 5, 2024, 24, 5, 2024),
                Arguments.of(12, 11, 2008, 13, 11, 2008));
    }

    private static Stream<Arguments> validDatesInALeapYearThatAreAtTheEndOfFebruary() {
        return Stream.of(
                Arguments.of(29, 2, 2024, 1, 3, 2024),
                Arguments.of(29, 2, 2008, 1, 3, 2008));
    }

    // previousDay

    private static Stream<Arguments> validDatesNotAtTheBeginningOfTheMonth() {
        return Stream.of(
                Arguments.of(13, 3, 2023, 12, 3, 2023),
                Arguments.of(23, 2, 2023, 22, 2, 2023));
    }

    private static Stream<Arguments> validDatesRegularYearAtTheBeginningOfTheMonth() {
        return Stream.of(
                Arguments.of(1, 1, 2023, 31, 12, 2022),
                Arguments.of(1, 3, 2023, 28, 2, 2023),
                Arguments.of(1, 5, 2023, 30, 4, 2023));
    }

    private static Stream<Arguments> validDatesLeapYearAtTheBeginningOfTheMonth() {
        return Stream.of(
                Arguments.of(1, 3, 2024, 29, 2, 2024),
                Arguments.of(1, 2, 2024, 31, 1, 2024));
    }

    // compareTo

    private static Stream<Arguments> diffYear() {
        return Stream.of(
                Arguments.of(1, 1, 2024, 1, 1, 2021),
                Arguments.of(1, 1, 2024, 1, 1, 2026));
    }

    private static Stream<Arguments> diffMonth() {
        return Stream.of(
                Arguments.of(1, 5, 2024, 1, 3, 2024),
                Arguments.of(1, 5, 2024, 1, 6, 2024));
    }

    private static Stream<Arguments> diffDay() {
        return Stream.of(
                Arguments.of(23, 1, 2024, 20, 1, 2024),
                Arguments.of(23, 1, 2024, 27, 1, 2024));
    }

    // constructor

    @Test
    void testConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 0, 0));
    }

    // isValidDate

    @ParameterizedTest
    @MethodSource("dateInfZero")
    void testDateInfZero(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("dateEqZero")
    void testDateEqZero(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("isValidDateBlock3")
    void testisValidDateBlock3(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("isValidDateBlock4")
    void testisValidDateBlock4(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("isValidDateBlock5")
    void testisValidDateBlock5(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @MethodSource("isValidDateBlock6")
    void testisValidDateBlock6(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
    }

    // IsLeap
    @ParameterizedTest
    @ValueSource(ints = { 2022 })
    void testNotDivisibleBy4(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = { 2024, 2008 })
    void testdivisibleBy4ButNotBy100(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1700, 1900 })
    void testDivisibleBy100ButNotBy400(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1600 })
    void testDivisibleByBoth100And400(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    // NextDate
    @ParameterizedTest
    @MethodSource("validDatesAndRegularYearNotAtTheEndOfTheMonth")
    void testValidDatesAndRegularYearNotAtTheEndOfTheMonth(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, nextDate.compareTo(expectedDate));

    }

    @ParameterizedTest
    @MethodSource("validDatesInARegularYearAtTheEndOfTheMonth")
    void testValidDatesInARegularYearAtTheEndOfTheMonth(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, nextDate.compareTo(expectedDate));

    }

    @ParameterizedTest
    @MethodSource("validDatesInALeapYearNotAtTheEndOfFebruary")
    void testValidDatesInALeapYearNotAtTheEndOfFebruary(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, nextDate.compareTo(expectedDate));

    }

    @ParameterizedTest
    @MethodSource("validDatesInALeapYearThatAreAtTheEndOfFebruary")
    void testValidDatesInALeapYearThatAreAtTheEndOfFebruary(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    // previousDay
    @ParameterizedTest
    @MethodSource("validDatesNotAtTheBeginningOfTheMonth")
    void testValidDatesNotAtTheBeginningOfTheMonth(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @ParameterizedTest
    @MethodSource("validDatesRegularYearAtTheBeginningOfTheMonth")
    void testValidDatesRegularYearAtTheBeginningOfTheMonth(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @ParameterizedTest
    @MethodSource("validDatesLeapYearAtTheBeginningOfTheMonth")
    void testValidDatesLeapYearAtTheBeginningOfTheMonth(int day, int month, int year, int expectedDay,
            int expectedMonth,
            int expectedYear) {
        Date date = new Date(day, month, year);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    // compareTo
    @ParameterizedTest
    @MethodSource("diffYear")
    void testDiffYear(int day, int month, int year, int otherdDay,
            int otherdMonth,
            int otherYear) {
        Date date = new Date(day, month, year);
        Date diffDate = new Date(otherdDay, otherdMonth, otherYear);
        assertNotEquals(0, date.compareTo(diffDate));
    }

    @ParameterizedTest
    @MethodSource("diffMonth")
    void testDiffMonth(int day, int month, int year, int otherdDay,
            int otherdMonth,
            int otherYear) {
        Date date = new Date(day, month, year);
        Date diffDate = new Date(otherdDay, otherdMonth, otherYear);
        assertNotEquals(0, date.compareTo(diffDate));
    }

    @ParameterizedTest
    @MethodSource("diffDay")
    void testDiffDay(int day, int month, int year, int otherdDay,
            int otherdMonth,
            int otherYear) {
        Date date = new Date(day, month, year);
        Date diffDate = new Date(otherdDay, otherdMonth, otherYear);
        assertNotEquals(0, date.compareTo(diffDate));
    }

    @Test
    void testSameDay() {
        Date date = new Date(20, 01, 2000);
        Date diffDate = new Date(20, 01, 2000);
        assertEquals(0, date.compareTo(diffDate));
    }

    @Test
    void testNullObject() {
        Date date = new Date(20, 01, 2000);

        assertThrows(NullPointerException.class, () -> date.compareTo(null));
    }
}