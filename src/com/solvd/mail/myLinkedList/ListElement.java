package com.solvd.mail.myLinkedList;

public class ListElement<T> {
    private T value;
    private ListElement previous = null;
    private ListElement next = null;

    public T getValue(){
        return value;
    }

    public ListElement getNext(){
        return next;
    }

    public ListElement(ListElement l, T value){
        this.previous = l;
        l.next = this;
        this.value = value;
    }

    public ListElement(T value){
        this.value = value;
    }
}
