package nl.saxion.minesweeper.model;

import java.util.ArrayList;
import java.util.Comparator;

public class RecordList {
    public ArrayList<Record> list;

    public RecordList() {
        list = new ArrayList<>();
    }

    public void addRecord(Record record) {
        this.list.add(record);
        this.list.sort(Comparator.comparingInt(Record::getScore).reversed());
        if (list.size() > 5) {
            this.list.remove(5);
        }
    }

    public ArrayList<Record> getList() {
        this.list.sort(Comparator.comparingInt(Record::getScore).reversed());
        return this.list;
    }
}
