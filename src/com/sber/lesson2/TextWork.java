package com.sber.lesson2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextWork {

    private HashMap<String, Integer> uniqueWord;
    private List<String[]> strFile;

    public TextWork() {
        try {
            List<String> fileText = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/text.txt"));

            Supplier<Stream<String>> supplier = fileText::stream;

            uniqueWord = supplier.get()
                    .map(line -> line.split("\\s"))
                    .flatMap(Arrays::stream)
                    .collect(
                            (Supplier<HashMap<String, Integer>>) HashMap::new,
                            (map, item) -> map.merge(item, 1, Integer::sum),
                            HashMap::putAll
                    );

            strFile = supplier.get()
                    .map(line -> line.split("\\s"))
                    .collect(Collectors.toList()
                    );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void numDifferentWords() {
        Optional<Map.Entry<String, Integer>> maxNumWord = uniqueWord.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        maxNumWord.ifPresent(e -> System.out.println("Самое встречаемое слово ( " + e.getValue() + " раз ) " + e.getKey()));
    }

    public void allUniqueWord() {
        System.out.println("Всего уникальных слов " + uniqueWord.size());
    }

    public void printSortWord() {
        uniqueWord.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(m -> m.getKey().length()))
                .forEach(System.out::println);
    }

    public void printLine(int... num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] >= strFile.size()) {
                System.out.println("Вы вышли за пределы массива ( " + strFile.size() + " )");
                return;
            }
        }

        for (int i = 0; i < num.length; i++) {
            System.out.println(Arrays.toString(strFile.get(num[i])));
        }
    }

    public void printReverse() {
        ListIteratorReverse<String[]> iterator = new ListIteratorReverse<>(strFile);

        while (iterator.hasNext()) {
            System.out.println(Arrays.toString(iterator.next()));
        }
    }
}
