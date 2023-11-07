package it.unibo.inner.Impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class GenericClass<T> implements IterableWithPolicy<T>{

    final private List<T> elements;
    final private static int INITIAL_ARRAY_SIZE = -1;

    public GenericClass(final T[] elements) {
        this.elements = List.of(elements);
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerClass();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    //@Override
    //public String toString() {
    //  return elements.toString();
    //}

    public class InnerClass implements Iterator<T>{

        private int curr = INITIAL_ARRAY_SIZE; 

        @Override
        public boolean hasNext() {
            return (curr + 1 < elements.size());
        }

        @Override
        public T next() {
            if (hasNext()){
                curr++;
                return elements.get(curr);
            }
            throw new NullPointerException();
        }

    }
}
