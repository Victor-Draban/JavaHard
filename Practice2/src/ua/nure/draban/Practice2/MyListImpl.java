package ua.nure.draban.Practice2;

import java.util.*;

public class MyListImpl implements MyList, ListIterable {
    private static final int START_CAPASITY = 16;

    private Object[] myList;
    private int size;
    private int capasity;

    public MyListImpl() {
        capasity = START_CAPASITY;
        myList = new Object[capasity];
        size = 0;
    }

    @Override
    public void add(Object e) {
        if (e == null) {
            return;
        }
        if (size == capasity - 1) {
            Object[] temp = myList.clone();
            capasity *= 2;
            myList = new Object[capasity];
            System.arraycopy(temp, 0, myList, 0, size);
        }
        myList[size] = e;
        size++;
    }

    @Override
    public void clear() {
        capasity = START_CAPASITY;
        myList = new Object[capasity];
        size = 0;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (myList[i].equals(o)) {
                resizeArray(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(myList, 0, result, 0, size);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (myList[i].equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        Object[] array = c.toArray();
        for (int i = 0; i < array.length; i++) {
            if (!this.contains(array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(String.valueOf(myList[i])).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int cursor = -1;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return cursor < size - 1;
        }

        @Override
        public Object next() {
            if (cursor + 1 == size) {
                throw new IllegalStateException();
            }
            canRemove = true;
            return myList[++cursor];
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            canRemove = false;
            Object rm = myList[cursor--];
            MyListImpl.this.remove(rm);
        }

        public int getCursor() {
            return cursor;
        }

        public void setCursor(int cursor) {
            this.cursor = cursor;
        }

        public boolean isCanRemove() {
            return canRemove;
        }

        public void setCanRemove(boolean canRemove) {
            this.canRemove = canRemove;
        }
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        @Override
        public boolean hasPrevious() {
            return size > 0;
        }

        @Override
        public Object previous() {
            setCanRemove(true);
            return myList[--size];
        }

        @Override
        public void set(Object e) {
            if (!isCanRemove()) {
                throw new IllegalStateException();
            }
            setCanRemove(false);
            MyListImpl.this.myList[getCursor()] = e;
        }
    }

    private boolean resizeArray(int index) {
        size--;
        if (size >= START_CAPASITY*2 && size < capasity/4) {
            capasity /= 4;
            Object[] temp = new Object[capasity];
            for (int j = 0, k = 0; j < size; j++, k++) {
                if (k == index) {
                    j--;
                } else {
                    temp[j] = myList[k];
                }
            }
            myList = temp;
        } else {
            System.arraycopy(myList, index + 1, myList, index, size - index);
        }
        return true;
    }
}
