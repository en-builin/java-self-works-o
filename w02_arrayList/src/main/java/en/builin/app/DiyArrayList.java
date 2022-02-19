package en.builin.app;

import java.util.*;
import java.util.function.UnaryOperator;

public class DiyArrayList<T> implements List<T> {

    private Object[] data;

    private int size;

    public DiyArrayList() {
        data = new Object[10];
        size = 0;
    }

    public DiyArrayList(int startSize) {
        data = new Object[startSize];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            result.append(data[i]).append("; ");
        }
        result.append("}");
        return result.toString();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new DiyListIterator();
    }

    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOfRange(data, 0, size-1);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) toArray();
        } else {
            for (int i = 0; i < size; i++) {
                a[i] = (T1) data[i];
            }
            for (int i = size; i < a.length; i++) {
                a[i] = null;
            }
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        sizeIncrement();
        data[size-1] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], o)) {
                for (int j = i; j < size-1; j++) {
                    data[j] = data[j+1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        data = new Object[10];
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        data[index] = element;
        return (T) data[index];
    }

    @Override
    public void add(int index, T element) {
        sizeIncrement();
        for (int i = size-1; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = element;
    }

    @Override
    public T remove(int index) {
        T result = (T) data[index];
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size-1; i >= 0; i--) {
            if (data[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DiyListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> result = new DiyArrayList<T>(toIndex - fromIndex + 1);
        for (int i = fromIndex; i <= toIndex; i++) {

        }
        return null;
    }

    private void sizeIncrement() {
        size++;
        if (data.length < size) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private class DiyListIterator implements ListIterator<T> {

        private int index;

        private DiyListIterator() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            return index < size-1;
        }

        @Override
        public T next() {
            return (T) data[++index];
        }

        @Override
        public boolean hasPrevious() {
            return index > -1;
        }

        @Override
        public T previous() {
            return (T) data[--index];
        }

        @Override
        public int nextIndex() {
            return (index + 1);
        }

        @Override
        public int previousIndex() {
            return (index - 1);
        }

        @Override
        public void remove() {
            DiyArrayList.this.remove(index);
        }

        @Override
        public void set(T o) {
            DiyArrayList.this.set(index, o);
        }

        @Override
        public void add(T o) {
            DiyArrayList.this.add(index, o);
        }
    }

    public static void main(String[] args) {
        DiyArrayList<String> diyArrayList = new DiyArrayList<>();
        DiyArrayList<String> diyArrayList2 = new DiyArrayList<>();
        for (int i = 0; i < 30; i++) {
            diyArrayList.add("Element " + i);
            diyArrayList2.add("a");
        }
        System.out.println(diyArrayList);
        System.out.println(diyArrayList2);

        Collections.addAll(diyArrayList2, "Added 1", "Added 2", "Added 3");
        System.out.println(diyArrayList2);

        Collections.copy(diyArrayList2, diyArrayList);
        System.out.println(diyArrayList2);

        Collections.sort(diyArrayList, Comparator.reverseOrder());
        System.out.println(diyArrayList);
    }
}
