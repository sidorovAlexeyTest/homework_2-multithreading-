package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LetterCountMergeImpl implements LetterCountMerger {

    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        if (first == null && second == null) {
            return new HashMap<>();
        } else if ((first == null || first.isEmpty()) && second != null) {
            return second;
        } else if (second == null || second.isEmpty()) {
            return first;
        }
        return Stream.of(first, second)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Long::sum
                        )
                );
    }
}
