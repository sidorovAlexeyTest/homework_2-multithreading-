package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {

    private Integer linesCount = 0;

    @Override
    public Stream<String> readLines(File file) {
        try {
            return Files.lines(file.toPath())
                    .peek(line -> linesCount++);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    @Override
    public Integer linesCount() {
        return linesCount;
    }
}
