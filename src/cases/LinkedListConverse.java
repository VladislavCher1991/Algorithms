package cases;

import java.util.Iterator;

public class LinkedListConverse {
    public static void main(String[] args) {
        SingleLinkList<Contact> list = new SingleLinkList<>();

        list.addToEnd(new Contact(111, "dfsfsdffsdf", "1232331"));
        list.addToEnd(new Contact(111, "aaaaaaaaaaaa", "1232331"));
        list.addToEnd(new Contact(121, "bbbbbbbbbbbb", "1232331"));
        list.addToEnd(new Contact(131, "cccccccccccc", "1232331"));
        list.addToEnd(new Contact(141, "dddddddddddd", "1232331"));
        list.addToEnd(new Contact(151, "eeeeeeeeeeee", "1232331"));

        for (Contact contact : list) {
            System.out.println(contact);
        }
        list.reverse();
        System.out.println("-----------------------");
        for (Contact contact : list) {
            System.out.println(contact);
        }

    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
            } else {
                tail.next = newItem;
            }
            tail = newItem;
        }

        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}
