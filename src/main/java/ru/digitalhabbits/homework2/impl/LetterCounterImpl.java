package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;
import ru.digitalhabbits.homework2.util.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class LetterCounterImpl implements LetterCounter {

    private ExecutorService service;
    private ConcurrentLinkedQueue<Map<Character, Long>> queue;

    public LetterCounterImpl(ExecutorService service,
                             ConcurrentLinkedQueue<Map<Character, Long>> queue) {
        this.service = service;
        this.queue = queue;
    }

    @Override
    public Map<Character, Long> count(String input) {

        try {
            queue.add(service.submit(new Callable<Map<Character, Long>>() {
                @Override
                public Map<Character, Long> call() throws Exception {
                    return input.chars().mapToObj(e -> (char) e)
                            .collect(Collectors.toList())
                            .stream().collect(HashMap::new, (m, c) -> {
                                m.put(c, 1L);
                                m.put(c, 1L);
                            }, HashMap::putAll);
                }
            }).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
