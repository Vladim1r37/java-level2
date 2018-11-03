package Lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class Main3 {

    public static void main(String[] args) {
        System.out.println("Тест массива строк");
        System.out.println();
        testStringArray();
        System.out.println();
        System.out.println("Тест телефонного справочника");
        System.out.println();
        testPhonebook();
    }

    private static void testPhonebook() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", "+79799593635");
        phonebook.add("Петров", "+79010077777");
        phonebook.add("Боширов", "+79020077777");
        phonebook.add("Иванов", "+79033433535");
        phonebook.add("Сидоров", "+79087626453");
        phonebook.add("Медведев", "+79650165862");
        phonebook.add("Золотов", "+79563245684");
        phonebook.add("Петров", "+79364562359");
        phonebook.add("Иванов", "+79121256854");

        phonebook.get("Иванов");
        phonebook.get("Петров");
        phonebook.get("Медведев");
        phonebook.get("Путин");
    }

    private static void testStringArray() {
        String[] arr = new String[20];
        fill(arr);

        for (String cell : arr) {
            System.out.println(cell);
        }
        HashSet<String> set = new HashSet<>();

        for (String cell : arr) {
            set.add(cell);
        }
        System.out.println();
        System.out.println(set);
        HashMap<String, Integer> stringMap = new HashMap<>();

        for (String cell : arr) {
            if (!stringMap.containsKey(cell)) {
                stringMap.put(cell, 1);
            }
            else {
                stringMap.put(cell, stringMap.get(cell) + 1);
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : stringMap.entrySet()) {
            System.out.println("Слово " + entry.getKey() +
                    " встречается в массиве " + entry.getValue() + " раз(а)");
        }
    }

    private static void fill(String[] arr) {
        String[] temp = {"abstract", "continue", "for", "new", "switch",
                "assert", "default", "package", "boolean", "do", "if",
                "private", "break", "double", "implements", "protected",
                "throw", "byte", "else", "import", "public", "case",
                "enum", "return", "catch", "extends", "int", "short", "try",
                "char", "final", "interface", "static", "void", "class",
                "finally", "long", "volatile", "float", "native", "super", "while"};
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[rnd.nextInt(temp.length)];
        }
    }
}
