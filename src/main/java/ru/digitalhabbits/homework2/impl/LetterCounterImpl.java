package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;

public class LetterCounterImpl implements LetterCounter {

    @Override
    public Map<Character, Long> count(String input) {
        return input.chars()
                .mapToObj(e -> (char) e)
                .collect(HashMap::new,
                        (map, ch) -> map.put(ch, map.containsKey(ch) ? (map.get(ch) + 1) : 1),
                        HashMap::putAll);
    }
}
