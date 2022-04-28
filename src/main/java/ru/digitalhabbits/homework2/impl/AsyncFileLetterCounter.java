package ru.digitalhabbits.homework2.impl;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

public class AsyncFileLetterCounter implements FileLetterCounter {

    private final ExecutorService executorService = Executors.newFixedThreadPool(8);
    private final FileReader fileReader = new FileReaderImpl();
    private final LetterCounter letterCounter = new LetterCounterImpl();
    private final LetterCountMerger letterCountMerge = new LetterCountMergeImpl();
    private final ConcurrentLinkedQueue<Map<Character, Long>> queue = new ConcurrentLinkedQueue<>();
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    @Override
    public Map<Character, Long> count(File input) {
        Stream<String> stream = fileReader.readLines(input);
        stream.forEach(this::toMapProcess);
        while (atomicInteger.get() != fileReader.linesCount()) {
            if (queue.size() > 1) {
                mergeProcess(queue.poll(), queue.poll());
            }
        }
        return queue.poll();
    }

    private void toMapProcess(String line) {
        executorService.submit(() -> queue.add(letterCounter.count(line)));
    }

    private void mergeProcess(Map<Character, Long> first, Map<Character, Long> second) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                queue.add(letterCountMerge.merge(first, second));
                atomicInteger.getAndIncrement();
            }
        });
    }
}
