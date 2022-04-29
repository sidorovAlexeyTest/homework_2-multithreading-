package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCountMergeImplTest {
    private final LetterCountMerger letterCountMerger = new LetterCountMergeImpl();
    private final Map<Character, Long> map = Map.of(
            'a', 1L,
            'b', 2L,
            'c', 3L);
    @Test
    void if_first_correct_and_second_correct() {
        assertThat(letterCountMerger.merge(map, map)).containsOnly(
                entry('a', 2L),
                entry('b', 4L),
                entry('c', 6L)
        );
    }

    @Test
    void if_first_correct_and_second_is_empty() {
        assertThat(letterCountMerger.merge(map, new HashMap<>())).containsOnly(
                entry('a', 1L),
                entry('b', 2L),
                entry('c', 3L)
        );
    }

    @Test
    void if_first_correct_and_second_null() {
        assertThat(letterCountMerger.merge(map, null)).containsOnly(
                entry('a', 1L),
                entry('b', 2L),
                entry('c', 3L)
        );
    }

    @Test
    void if_first_is_empty_and_second_is_empty() {
        assertThat(letterCountMerger.merge(new HashMap<>(), new HashMap<>())).isEmpty();
    }

    @Test
    void if_first_is_null_and_second_is_null() {
        assertThat(letterCountMerger.merge(null, null)).isEmpty();
    }
}