package com.solvd.mail.myLinkedList;

public class ListElement<T> {
    private final T value;
    private ListElement previous = null;
    private ListElement next = null;

    public ListElement(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public ListElement getNext() {
        return next;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    public ListElement getPrevious() {
        return previous;
    }

    public void setPrevious(ListElement previous) {
        this.previous = previous;
    }


    @Override
    public String toString() {
        if (value != null)
            return value.toString();
        return null;
    }
}
