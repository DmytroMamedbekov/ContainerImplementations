package com.dmytromamedbekov;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    protected static class Node {
        Object element;
        Node next;
        Node previous;

        Node(Object element, Node nextElementLink, Node lastElementLink) {
            this.element = element;
            this.next = nextElementLink;
            this.previous = lastElementLink;
        }

        Node() {
            this(null,null,null);
        }
    }

    protected Node first;
    protected Node last;

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

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node currentNode =new Node("",null,last);

        @Override
        public boolean hasNext() {
            return (currentNode.previous != null);
        }

        @Override
        public Object next() {
            if (hasNext()) {
                currentNode = currentNode.previous;
                return currentNode.element;
            }
            else throw new NoSuchElementException("No next element");
        }
    }

    @Override
    public void push(Object element) {
        Node a = new Node();
        a.element = element;
        a.next=null;
        a.previous=null;
        if (last == null)
        {
            first = a;
            last = a;
        } else {
            a.previous=last;
            last.next = a;
            last = a;
        }
    }

    @Override
    public Object top() {
        if (first == null)
        {
            return null;
        } else {
            Node t=first;
            while (t.next.next!=null)
            {
                t=t.next;
            }
            return t.next.element;
        }
    }

    @Override
    public Object pop()
    {
        if (first == null)
        {
            return null;
        } else {
            Node t=first;
            while (t.next.next!=null)
            {
                t=t.next;
            }
            Object returnedTop=t.next.element;
            t.next=null;
            last=t;

            return returnedTop;
        }
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("[");
        Node t = this.first;
        if (t!=null)  {
            result.append(t.element);
            while (t.next != null) {
                t = t.next;
                result.append(", ");
                result.append(t.element);
            }
        }
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        StackImpl example = new StackImpl();

        System.out.println("Creating stack:");
        example.push("A");
        example.push("B");
        example.push("C");
        System.out.println(example.toString());

        System.out.println("Pushing element D");
        example.push("D");
        System.out.println(example.toString());

        System.out.println("Popping the stack once:");
        example.pop();
        System.out.println(example.toString());
    }

}
