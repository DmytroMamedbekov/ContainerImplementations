package com.dmytromamedbekov;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListImpl implements List {

    protected static class Node {
        Object element;
        Node next;


        public Node(Object element, Node nextElementLink) {
            this.element = element;
            this.next = nextElementLink;
        }

        public Node() {
            this.element = null;
            this.next = null;
        }
    }

    protected Node first = null;
    protected Node last;

    @Override
    public void addFirst(Object element)
    {
        Node a = new Node();
        a.element = element;

        if (first == null)
        {
            first = a;
            last = a;
        } else {
            a.next = first;
            first = a;
        }
    }


    @Override
    public void addLast(Object element) {
        Node a = new Node();
        a.element = element;
        a.next=null;
        if (last == null)
        {
            first = a;
            last = a;
        } else {
            last.next = a;
            last = a;
        }
    }

    @Override
    public void removeFirst()
    {
        if (first != null) {
            if (first.next==null) {
                first = null;
                last = null;
            } else {
                first.element=first.next.element;
                first=first.next;
            }
        }
    }

    @Override
    public void removeLast()
    {
        if (first != null)
        {
            Node t=first;
            while (t.next.next!=null)
            {
                t=t.next;
            }
            t.next=null;
            last=t;
        }
    }

    @Override
    public Object getFirst()
    {
        if (first!=null)
            return first.element;
        else
            return null;
    }

    @Override
    public Object getLast()
    {
        if (last!=null)
            return last.element;
        else
            return null;
    }

    @Override
    public boolean remove(Object element)
    {
        if (first == null)
            return false;


        if (first.element == element) {
            first = first.next;
            return true;
        }

        Node t = first;
        while (t.next != null) {
            if (t.next.element == element) {
                if (last == t.next)
                {
                    last = t;
                }
                t.next = t.next.next;
                return true;
            }
            t = t.next;
        }
        return false;
    }

    @Override
    public Object search(Object element)
    {
        if (first == null)
            return null;


        if (first.element == element) {
            return first.element;
        }

        Node t = first;
        while (t.next != null) {
            if (t.element == element) {
                return t.element;
            }
            t = t.next;
        }
        return null;
    }

    public ListImpl() {
    }

    @Override
    public void clear() {
        first = null;
        last = null;
    }



    @Override
    public int size() {
        int size = 0;
        Node t = first;
        while (t != null) {
            size++;
            t = t.next;
        }
        return size;
    }

    public String toString() {
        StringBuilder result=new StringBuilder("[");
        Node t = this.first;
         if (t!=null)  {
            result.append(String.valueOf(t.element));
            while (t.next != null) {
                t = t.next;
                result.append(", ");
                result.append(String.valueOf(t.element));
            }
        }
        result.append("]");
        return result.toString();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node currentNode =new Node("",first);

        @Override
        public boolean hasNext() {
            return (currentNode.next != null);
        }

        @Override
        public Object next() {
            if (currentNode.element==null)
                throw new NoSuchElementException("No element here");
            if (hasNext()) {
                currentNode = currentNode.next;
                return currentNode.element;
            }
            else throw new NoSuchElementException("No next element");
        }

    }

    public static void main(String[] args) {
        ListImpl example = new ListImpl();

        System.out.println("Creating a list containing a single element:");
        example.addFirst("Starting element");
        System.out.println(example.toString());

        System.out.println("Adding an element to the beginning of a list:");
        example.addFirst("Element at the beginning");
        System.out.println(example.toString());

        System.out.println("Adding an element to the end");
        example.addLast("Element at the end");
        System.out.println(example.toString());

    }
}


