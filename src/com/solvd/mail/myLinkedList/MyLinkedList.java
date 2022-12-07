package com.solvd.mail.myLinkedList;

import org.jetbrains.annotations.NotNull;

public class MyLinkedList<T> {
    private ListElement<T> node = null;

    public MyLinkedList() {
        node = null;
    }

    public MyLinkedList(T value) {
        node = new ListElement<>(value);
    }

    public boolean addElement(T value) {
        if (node == null) {
            node = new ListElement<>(value);
        } else {
            ListElement l = node;
            while (l.getNext() != null) {
                l = l.getNext();
            }
            l.setNext(new ListElement(value));
            l.getNext().setPrevious(l);
        }
        return true;
    }

    public boolean add(@NotNull T... value) {
        for (T v : value)
            addElement(v);
        return true;
    }

    public int size() {
        if (node == null)
            return 0;
        int size = 1;
        ListElement l = node;
        while (l.getNext() != null) {
            l = l.getNext();
            size++;
        }
        return size;

    }

    public boolean remove(T value) {
        if (node != null) {
            ListElement l = node;
            if ((value == null && l.getValue() == null) || l.getValue().equals(value)) {
                node = l.getNext();
                return true;
            }
            while (l.getNext() != null) {
                l = l.getNext();
                if ((value == null && l.getValue() == null) || l.getValue().equals(value)) {
                    if (l.getPrevious() != null)
                        l.getPrevious().setNext(l.getNext());
                    if (l.getNext() != null)
                        l.getNext().setPrevious(l.getPrevious());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(int index) {
        int size = size();
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " out of range " + (size - 1));

        if(index == 0){
            node = node.getNext();
            return true;
        }

        ListElement l = node;
        for (int i = 0; i < index; i++) {
            if (l.getNext() != null) {
                l = l.getNext();
            } else {
                return false;
            }
        }
        if (l.getPrevious() != null)
            l.getPrevious().setNext(l.getNext());
        if (l.getNext() != null)
            l.getNext().setPrevious(l.getPrevious());
        return true;
    }

    public ListElement get(int index) {
        int size = size();
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " out of range " + (size - 1));

        ListElement l = node;
        for (int i = 0; i < index; i++) {
            if (l.getNext() != null) {
                l = l.getNext();
            } else {
                return null;
            }
        }
        return l;
    }

    public boolean check(T value) {
        if (node != null) {
            ListElement l = node;
            if (value == null) {
                if (l.getValue() == null)
                    return true;
            }
            if (l.getValue().equals(value))
                return true;
            while (l.getNext() != null) {
                l = l.getNext();
                if (value == null) {
                    if (l.getValue() == null)
                        return true;
                }
                if (l.getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public String toString() {
        String res = "MyLinkedList{";
        if (node != null) {
            ListElement l = node;
            res += l + ", ";
            while (l.getNext() != null) {
                l = l.getNext();
                res += l + ", ";
            }
            res += "}";
            res = res.replace(", }", "}");
        } else {
            res += "}";
        }
        return res;
    }

    public Object[] asArray() {
        int size = size();
        if (size == 0)
            return new Object[0];
        Object[] mas = new Object[size];
        ListElement l = node;
        mas[0] = node;
        int index = 1;
        while (l.getNext() != null) {
            l = l.getNext();
            mas[index++] = l;
        }
        return mas;
    }

    public boolean clear(){
        node = null;
        return true;
    }
}
