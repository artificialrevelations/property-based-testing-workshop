package org.artrev.workshop.pbt.examples.reverse;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;

import java.util.List;

public class ListsPropertyBasedTests {
    @Property
    @Report(Reporting.GENERATED)
    public boolean reverse_of_reversed_is_equal_to_original(@ForAll List<?> original) {
        return Lists.reverse(Lists.reverse(original)).equals(original);
    }
    /*
     What happens if the property is not satisfied?

     Below is a property that is broken. It states that the reversed list should be
     equal to the original list. Uncomment it and check what will be returned by the
     property based testing framework as a result.
     */

   /*
    @Property
    @Report(Reporting.GENERATED)
    public boolean broken_reverse_property(@ForAll List<?> original) {
        return Lists.reverse(original).equals(original);
    }
    */

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
