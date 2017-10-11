package ua.nure.draban.Practice2;

import java.util.Arrays;
import java.util.Iterator;

public class Demo {

    public static void main(String[] args) {
        MyList con = new MyListImpl();
        MyList con2 = new MyListImpl();
        Object obj = "A";
        Object obj2 = "B";
        con2.add(obj);
        con2.add(obj2);
        con.add(obj);
        con.add(obj2);
        con.add(433);
        con.add(888);
        con.add(new Object());
        System.out.println(con);
        con.remove(433);
        System.out.println("con.size() = " + con.size());
        System.out.println("con.contains(A) = " + con.contains("A"));
        System.out.println("con.containsAll(A,B) = " + con.containsAll(con2));
        System.out.println("con = " + con);
        con.remove(obj);
        System.out.println("con after remove obj = A " + con);
        Object[] toArray = con.toArray();
        System.out.println(Arrays.toString(toArray));
        con.clear();
        System.out.println("con.clear() = " + con);
        System.out.println("con.size() = " + con.size());
        for (Object o : con) {
            System.out.println(o);
        }
        Iterator<Object> it = con.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        MyListImpl myList = new MyListImpl();
        myList.add(1234);
        myList.add(2345);
        myList.add(3456);
        myList.add(4567);
        System.out.println("-------------------");
        ListIterator listIterator = myList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        listIterator.remove();
        System.out.println(listIterator.previous());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
