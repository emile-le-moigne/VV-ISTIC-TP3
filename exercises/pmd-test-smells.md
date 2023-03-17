# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Here the liste of PMD rules and the test smells that implement them :

- DetachedTestCase : "The Free Ride" ;
- UseAssertTrueInsteadOfAssertEquals, UseAssertSameInsteadOfAssertTrue, UseAssertNullInsteadOfAssertTrue, and UseAssertEqualsInsteadOfAssertTrue : "Assertion roulette" ;
- JUnitUseExpected : "Manual intervention" ;
- JUnitTestsShouldIncludeAssert and JUnitTestContainsTooManyAsserts : "Eager test" and "Assertion roulette" ;
- JUnitStaticSuite : "Interacting Tests" ;
- JUnitAssertionsShouldIncludeMessage : "Assertion roulette" .

We use the JUnitTestsShouldIncludeAssert rule to detect test smells in [Apache Commons Math](https://github.com/apache/commons-math).
The command line :

```
pmd/bin/run.sh pmd -d Documents/VV/commons-math/ -R category/java/bestpractices.xml/JUnitTestsShouldIncludeAssert -f text -r report.txt
```

You can find the response here : [report.txt](/report.txt)
