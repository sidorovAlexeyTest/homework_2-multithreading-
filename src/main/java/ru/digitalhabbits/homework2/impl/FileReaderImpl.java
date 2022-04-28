package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {

    @Override
    public Stream<String> readLines(File file) {
        try {
            return Files.lines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }
}
