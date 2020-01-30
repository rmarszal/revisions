package fr.umlv.conc;

import java.util.ArrayList;

public class ThreadSafeList {
    private final ArrayList<Integer> list;

    public ThreadSafeList() {
        list = new ArrayList<>();
    }

    public void add(int i) {
        synchronized (list) {
            list.add(i);
        }
    }

    public int size() {
        synchronized (list) {
            return list.size();
        }
    }

    @Override
    public String toString() {
        synchronized (list) {
            return list.toString();
        }
    }
}
