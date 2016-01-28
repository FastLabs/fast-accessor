A set of utility classes that helps building instances using builder pattern.
Easy to use to travers the object trees and implement transformation and mapping rules 



```
An example:
```java

final Amount subjectAmount = newAmount()
                .$(amount, 10.0)
                .$(crcy, newCurrency()
                        .$(crcyCd, "USD"))
                .build();

        final Party positionBalance = newParty()
                .$(partyTotalTurnover, subjectAmount).build();
        PipeAccessor<Party, String> accessor = new PipeAccessor<Party, String>(partyTotalTurnover)
                .take(crcy)
                .take(crcyCd);
        assertEquals("USD", accessor.get(positionBalance));
```