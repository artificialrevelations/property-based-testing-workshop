package org.artrev.workshop.pbt.examples.reverse;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListsExampleBasedPropertyTests {
    @Test
    public void reverse_reversed_equals_original() {
        // given:
        final List<String> tested = Arrays.asList("This", "is", "a", "test");
        // when:
        final List<String> result =
                Lists.reverse(Lists.reverse(tested));
        // then:
        assertEquals(tested, result);
        // Should we create more examples?
    }
}
