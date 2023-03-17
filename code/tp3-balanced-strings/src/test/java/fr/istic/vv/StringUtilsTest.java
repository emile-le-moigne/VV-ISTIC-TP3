package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testIsBalancedEmpty() {
        assertTrue(isBalanced(""));
    }

    //
    @ParameterizedTest
    @ValueSource(strings = { "{}", "[]", "()", "{[]}", "{()[]}", "{[()]}", "{[(){}]}", "{{}}", "[[]]",
             "{}[]", "{[()]}[]" })
    void testIsBalanced(String str) {
        assertTrue(isBalanced(str));
    }

    @ParameterizedTest
    @ValueSource(strings = { "(", ")", "[", "]", "{", "}", "{[)]}", "[}", "a[b(c]d)e" })
    void testIsUnbalanced(String str) {
        assertFalse(isBalanced(str));
    }
}