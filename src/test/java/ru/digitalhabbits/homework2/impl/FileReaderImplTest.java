package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class FileReaderImplTest {

    private final FileReader fileReader = new FileReaderImpl();
    private final File fileExists = new File("src\\test\\resources\\test.txt");
    private final File fileNotExists = new File(".txt");

    @Test
    public void if_have_file() {
        fileReader.readLines(fileExists).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
            }
        });
        assertThat(fileReader.linesCount()).isNotEqualTo(0);
    }

    @Test
    public void if_doesnt_have_file() {
        fileReader.readLines(fileNotExists).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
            }
        });
        assertThat(fileReader.linesCount()).isEqualTo(0);
    }
}