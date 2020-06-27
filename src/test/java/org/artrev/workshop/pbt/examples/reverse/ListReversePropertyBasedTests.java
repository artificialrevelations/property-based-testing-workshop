package org.artrev.workshop.pbt.examples.reverse;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;

import java.util.List;

public class ListReversePropertyBasedTests {
    /*
     The same reverse property but expressed with a PBT framework called jqwik.

     */
    @Property
    @Report(Reporting.GENERATED)
    public boolean reverse_of_reversed_is_equal_to_original(@ForAll List<?> original) {
        return Lists.reverse(Lists.reverse(original)).equals(original);
    }
    /*
     What happens if the property is not satisfied?

     Below is a property that is broken. The same example that we were checking for our
     simple PBT framework in the previous example. It states that the reversed list should be
     equal to the original list. Uncomment it and check what will be returned by the
     property based testing framework as a result.

     Please notice from the jqwik report that it did 2 tries to find a failing example.
     Why 2? Jqwik does not generate the values purely randomly, it tries to pass some
     common edge cases. In the example below the first value to test will be a simple
     empty list and for an empty list this broken property surprisingly holds.
     Next example was was not that simple and thus it failed the test.

     You can check the original failing sample in the same report, jqwik also shows the
     shrunk sample which can be used for testing. It is possible to configure an
     after failure strategy, for example jqwik will use the previous seed that failed
     when property is run again.

     Another aspect that can be appreciated about shrinking the example List is that
     the values on these lists were shrunk as well. We easily carould have gotten an example
     like [2321231231, 1231231212] instead of simple [0, 1]
     */
    @Property
    @Report(Reporting.GENERATED)
    public boolean broken_reverse_property(@ForAll List<?> original) {
        return Lists.reverse(original).equals(original);
    }

    /*
     We can add other properties for the reverse function based on
     what we defined so far. Below is an example of a property that
     we checked multiple times in our example based approach tests
     which is the reversed and original sizes should be the same.
     */
    @Property
    public boolean reverse_has_the_same_size_as_original(@ForAll List<?> original) {
        return Lists.reverse(original).size() == original.size();
    }
}
