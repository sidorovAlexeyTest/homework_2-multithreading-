package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCounterImplTest {

    private final LetterCounter letterCounter = new LetterCounterImpl();

    @Test
    void if_line_correct() {
        String line = "abbcccdddd";
        assertThat(letterCounter.count(line)).containsOnly(
                entry('a', 1L),
                entry('b', 2L),
                entry('c', 3L),
                entry('d', 4L)
        );
    }

    @Test
    void if_line_is_empty() {
        String line = "";
        assertThat(letterCounter.count(line)).isEmpty();
    }

    @Test
    void if_line_is_null() {
        String line = null;
        assertThat(letterCounter.count(line)).isEmpty();
    }
}