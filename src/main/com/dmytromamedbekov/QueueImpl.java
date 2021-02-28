package com.dmytromamedbekov;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

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

        protected Node first;
        protected Node last;

    @Override
    public void enqueue(Object element) {
        Node a = new Node();
        a.element = element;
        a.next=null;
        if (first == null)
        {
            first = a;
            last = a;
        } else {
            last.next = a;
            last = a;
        }
    }

    @Override
    public Object dequeue()
    {
        QueueImpl newQueue=this;
        if (newQueue.first == null)
        {
            return null;
        } else {
            Object head= this.first.element;
            if (newQueue.first.next==null) {
                newQueue.first = null;
                newQueue.last = null;
            } else {
                newQueue.first.element=newQueue.first.next.element;
                newQueue.first=newQueue.first.next;
            }
            return head;
        }
    }

        @Override
        public Object top()
        {
            if (first!=null)
                return first.element;
            else
                return null;
        }

    public QueueImpl(int size) {
        this();
        if (this.last!=null)
            this.last=null;
        else {
            this.last=new Node();
            this.last.element = size;
        }
    }

        public String toString() {
            StringBuilder result=new StringBuilder("[");
            Node t=this.first;
            if (t!=null) {
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
            public Object next()  {
                if (currentNode.element==null)
                    throw new NoSuchElementException("No element here");
                if (hasNext()) {
                    currentNode = currentNode.next;
                    return currentNode.element;
                }
                else throw new NoSuchElementException("No next element");
            }

        }

    public QueueImpl() {

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


    public static void main(String[] args) {
        QueueImpl example=new QueueImpl();

        System.out.println("Creating queue:");
        example.enqueue("A");
        example.enqueue("B");
        example.enqueue("C");
        System.out.println(example.toString());

        System.out.println("Top element of the queue:");
        System.out.println(example.top());

        System.out.println("Removing the head of the queue");
        example.dequeue();
        System.out.println(example.toString());

        System.out.println("Top element of the queue:");
        System.out.println(example.top());

    }

}
