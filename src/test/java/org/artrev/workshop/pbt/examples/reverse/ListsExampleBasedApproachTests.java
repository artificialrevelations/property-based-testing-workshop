package org.artrev.workshop.pbt.examples.reverse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Lists tests")
public class ListsExampleBasedApproachTests {
    @Nested
    @DisplayName("Reverse on")
    public class ReverseTests {
        @Nested
        @DisplayName("an empty list")
        public class EmptyList {
            @Test
            public void should_return_an_empty_list() {
                // given:
                final List<String> tested = Collections.emptyList();
                // when:
                final List<String> result = Lists.reverse(tested);
                // then:
                assertTrue(result.isEmpty());
            }
        }

        @Nested
        @DisplayName("a single element list")
        public class SingleElementList {
            @Test
            public void should_return_a_single_element_list() {
                // given:
                final List<String> tested = Collections.singletonList("42");
                // when:
                final List<String> result = Lists.reverse(tested);
                // then:
                assertEquals(1, result.size());
            }

            @Test
            public void should_return_a_list_with_the_same_element() {
                // given:
                final String element = "42";
                final List<String> tested = Collections.singletonList(element);
                // when:
                final List<String> result = Lists.reverse(tested);
                // then:
                assertEquals(element, result.get(0));
            }
        }

        @Nested
        @DisplayName("a multiple element list")
        public class MultipleElementList {
            @Test
            public void should_return_list_of_the_same_size() {
                // given:
                final List<String> tested = Arrays.asList("This", "is", "a", "test");
                // when:
                final List<String> result = Lists.reverse(tested);
                // then:
                assertEquals(tested.size(), result.size());
            }

            @Test
            public void should_return_list_with_the_same_elements() {
                // given:
                final List<String> tested = Arrays.asList("This", "is", "a", "test");
                // when:
                final List<String> result = Lists.reverse(tested);
                // then:
                assertTrue(result.containsAll(tested));
                assertTrue(tested.containsAll(result));
            }
        }
    }
}
