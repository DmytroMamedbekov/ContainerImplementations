package com.dmytromamedbekov;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayImpl implements Array {
    private int size;
    private Object[] content;
    private int lastOpenIndex;

    public ArrayImpl(int size){
        this.content=new Object[0];
        this.size=0;
        this.lastOpenIndex=0;
    }

    @Override
    public void clear() {
        this.content=new Object[0];
        this.lastOpenIndex=0;
        this.size=0;
    }

	@Override
    public int size() {
        return this.size;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return (currentPosition<size-1);
        }

        @Override
        public Object next() {
            if (hasNext()) {
                currentPosition++;
                return content[currentPosition];
            }
            else {
                throw new NoSuchElementException("No elements after current position");
            }
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(currentPosition);
            currentPosition--;
        }
    }
	
	@Override
    public void add(Object element) {

            Object[] newContent=new Object[size+1];
            for (int i=0;i<size;i++) {
                newContent[i] = this.content[i];
            }
            newContent[size]=element;
            this.content=newContent;
            this.lastOpenIndex++;
            this.size++;
        }

	@Override
    public void set(int index, Object element) {
        if(index<=this.lastOpenIndex)
        {
            this.content[index]=element;
        }
        
    }

	@Override
    public Object get(int index) {
        if (index<this.lastOpenIndex)
        {
            return this.content[index];
        }
        else throw new NoSuchElementException("Index is exceeding the array");
    }

	@Override
    public int indexOf(Object element) {
        for (int i=0;i<this.lastOpenIndex;i++)
        {
            if ((this.content[i]!=null) && (this.content[i].equals(element)))
                    return i;
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        if (index<=this.lastOpenIndex)
        {
            size--;
            Object[] newContent=new Object[size];
            for (int i=0;i<index;i++)
                newContent[i]=this.content[i];
            for (int i=index;i<size;i++)
                newContent[i]=this.content[i+1];

            this.lastOpenIndex--;
            this.content=newContent;
        }
        else throw new NoSuchElementException("Index is exceeding the array");
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("[");
        if (this.content.length>0) {
            result.append(String.valueOf(this.content[0]));
            for (int i = 1; i < lastOpenIndex; i++) {
                result.append(", ");
                result.append(String.valueOf(this.content[i]));
            }
        }
        result.append("]");
        return result.toString();

    }

    public static void main(String[] args) {
        //Demonstration of work
        System.out.println("Creating array of 4 elements:");
        ArrayImpl example = new ArrayImpl(4);
        for (int i=0;i<4;i++)
        {
            example.add(i);
        }

        System.out.println(example.toString());

        System.out.println("Removing element with index 2:");
        example.remove(2);
        System.out.println(example.toString());

        System.out.println("Clearing an array:");
        example.clear();
        System.out.println(example.toString());
    }
}
