package it.unibo.inner.Impl;

import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class GenericClass<T> implements IterableWithPolicy<T>{

    final private List<T> elements;
    private Predicate<T> iterationPolicy;
    
    final private static int INITIAL_ARRAY_SIZE = 0;

    public GenericClass(final T[] elements, Predicate<T> predicate){
        this.elements = List.of(elements);
        this.iterationPolicy = predicate;
    }

    public GenericClass(final T[] elements) {
        this(elements, new Predicate<>(){

            @Override
            public boolean test(T elem) {
                return true;
            }

        });
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerClass();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.iterationPolicy = filter;
    }

    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        for (var elem : this) {
            sb.append(elem + ", ");
        }
        sb.append("]");
        return sb.toString();
    }


    public class InnerClass implements Iterator<T>{

        private int curr = INITIAL_ARRAY_SIZE; 
        
        @Override
        public boolean hasNext() {
            while (curr < elements.size()){
                if (iterationPolicy.test(elements.get(curr))){
                    return true;
                }
                curr++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()){
                return elements.get(curr++);
            }
            throw new NullPointerException();
        }

    }
}
