package Lesson3;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {

    private Map<String, String> phonebook;

    public Phonebook() {
        this.phonebook = new HashMap<>();
    }

    public void add(String name, String phone) {
        if (!phonebook.containsKey(name)) {
            phonebook.put(name, phone);
        }
        else {
            phonebook.put(name, phonebook.get(name) + "\n" + phone);
        }
    }

    public void get(String name) {
        if (phonebook.containsKey(name)) {
            System.out.println(name + "\n" + phonebook.get(name));
        }
        else {
            System.out.println(name + ": В справочнике нет такой фамилии");
        }
    }
}
