package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class WordsCounter {

    public void countWordsFromFile(String path) throws IOException {
        String originalFilePath = path;
        File originalFile = new File(originalFilePath);
        FileInputStream fileInputStream = new FileInputStream(originalFile);

        byte a;
        StringBuilder stringBuilder = new StringBuilder();
        while ((a = (byte) fileInputStream.read()) != -1) {
            stringBuilder.append((char) a);
        }

        String[] originalFileDataStringArray = stringBuilder.toString().split(" ");

        String keyWords = "private, protected, public, abstract, class, extends, final, implements, interface, " +
                "native, new, static, strictfp, synchronized, transient, volatile, break, case, continue, default, " +
                "do, else, for, if, instanceof, return, switch, while, import, package, boolean, byte, char, double, " +
                "float, int, long, short, assert, catch, finally, throw, throws, try, enum, super, this, void, const, goto";


        Map<String, Integer> counterMap = new HashMap();
        for (String string : keyWords.split(", ")) {
            counterMap.put(string, 0);
        }

        for (String mapString : counterMap.keySet()) {
            for (String string : originalFileDataStringArray) {
                if (string.equals(mapString)) {
                    counterMap.put(mapString, counterMap.get(mapString) + 1);
                }
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("keyWordsCount")) {
            for (String string : counterMap.keySet()) {
                fileOutputStream.write((string + " ").getBytes());
                fileOutputStream.write(counterMap.get(string).toString().getBytes());
                fileOutputStream.write("\n".getBytes());
            }
        }
    }
}