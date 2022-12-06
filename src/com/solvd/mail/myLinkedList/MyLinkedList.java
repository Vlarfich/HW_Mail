package com.solvd.mail.myLinkedList;

public class MyLinkedList<T> {
    private ListElement<T> node = null;

    public MyLinkedList(T value)
    {
        node = new ListElement<>(value);
    }

    public boolean addElement(T value){
        ListElement l = node;
        while(l.getNext() != null){
            l = l.getNext();
        }
        l.getNext() = new ListElement(value);
    }

}
