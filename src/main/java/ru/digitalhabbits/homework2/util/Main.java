package ru.digitalhabbits.homework2.util;

import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.impl.FileReaderImpl;
import ru.digitalhabbits.homework2.impl.LetterCountMergeImpl;
import ru.digitalhabbits.homework2.impl.LetterCounterImpl;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    final static ExecutorService service = Executors.newFixedThreadPool(12);
    final static ConcurrentLinkedQueue<Map<Character, Long>> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {

        FileReader fileReader = new FileReaderImpl();
        LetterCounterImpl letterCounter = new LetterCounterImpl(service, queue);
        LetterCountMerger letterCountMerger = new LetterCountMergeImpl();
        fileReader.readLines(new File("src\\test\\resources\\test.txt")).forEach(letterCounter::count);

        int count = 10;
        while (count > 0) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(queue.size() > 1) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        queue.add(letterCountMerger.merge(queue.poll(), queue.poll()));
                    }
                });
                count = 10;
            }
            else {
                count--;
            }
        }
        service.shutdown();
        queue.poll().entrySet().stream().forEach(ent -> System.out.println(ent.getKey() + " : " + ent.getValue()));
    }
}
