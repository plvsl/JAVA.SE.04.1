package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class WordsCounter {

    private final static String KEY_WORDS = "private, protected, public, abstract, class, extends, final, implements, interface, " +
            "native, new, static, strictfp, synchronized, transient, volatile, break, case, continue, default, " +
            "do, else, for, if, instanceof, return, switch, while, import, package, boolean, byte, char, double, " +
            "float, int, long, short, assert, catch, finally, throw, throws, try, enum, super, this, void, const, goto";

    public void countWordsFromFile(String path) throws IOException {
        String originalFilePath = path;
        File originalFile = new File(originalFilePath);
        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(originalFile)) {
            byte a;
            while ((a = (byte) fileInputStream.read()) != -1) {
                stringBuilder.append((char) a);
            }
        }

        String[] originalFileDataStringArray = stringBuilder.toString().split(" ");
        Map<String, Integer> counterMap = new HashMap();
        for (String string : KEY_WORDS.split(", ")) {
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
                if (!counterMap.get(string).equals(0)) {
                    fileOutputStream.write((string + " ").getBytes());
                    fileOutputStream.write(counterMap.get(string).toString().getBytes());
                    fileOutputStream.write("\n".getBytes());
                }
            }
        }
    }
}