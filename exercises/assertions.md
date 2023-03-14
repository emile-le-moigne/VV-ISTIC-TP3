# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion fails due to floating-point precision errors. A tolerance or a delta value should be used, checking that the difference between the expected and actual values is less than some small value, rather than checking for exact equality.

2. `assertEquals` compares the values of two objects for equality, while `assertSame` checks if two objects are the same object (i.e., they have the same identity).

   Scenario where they produce the same result:

   - assertEquals(5, 5) and assertSame(5, 5).

   Scenario where they do not produce the same result:

   - assertEquals(list1, list2) will pass, but assertSame(list1, list2) will fail (assuming list1 and list2 are two separate objects).

3. Mark incomplete or unimplemented code, where the expected behavior or result is not yet known or not yet implemented.

```java
public class SortNumbers {

    public static List<Integer> sortNumbers(List<Integer> numbers) {
        throw new UnsupportedOperationException("Sorting algorithm not yet implemented");
    }
}
public class SortNumbersTest {

    @Test
    public void testSortNumbers() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        try {
            List<Integer> sortedNumbers = SortNumbers.sortNumbers(numbers);
            assertEquals(Arrays.asList(1, 1, 3, 4, 5, 9), sortedNumbers);
        } catch (UnsupportedOperationException e) {
            // Sorting algorithm not yet implemented, test fails
            fail("Sorting algorithm not yet implemented");
        }
    }
}
```

4. In our opinion, the `assertThrows` is easier to read and allows for more flexibility in testing thanks to its ability to check for specific exception types.
