/*
    Manav Kulshrestha
    List.java
    12/2/18
*/

class Node<T> {
    T data;
    Node<T> next;
    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
    public Node(T data) {
        this(data, null);
    }
    public String toString() {
        return ""+data;
    }
}

public class MyList<T> {

    public Node head;
    public int size;

    public MyList() {
        this.head = null;
        this.size = 0;
    }

    public MyList(Object... obs) {
        for(Object o: obs)
            this.add((T) o);
    }

    public void add(T data) {
        if(this.head == null)
            this.head = new Node(data);
        else {
            Node<T> iter = this.head;
            for(; iter.next != null; iter=iter.next) {}
            iter.next = new Node(data);
        }
        this.size++;
    }

    public String toString() {
        return toString(this.head);
    }

    public String toString(Node<T> l) {
        if(l == null)
            return "";
        return l.data+","+toString(l.next);
    }

    public void addMiddle(T e) {
        Node ptr = head, node = new Node(e, null);

        if(head == null){
            head = node;
            return;
        }

        if(size == 1) {
            node.next = head;
            head = node;
            return;
        }

        for(int i=0; i<(size/2)-1; i++)
            ptr = ptr.next;

        node.next = ptr.next;
        ptr.next = node;
    }

    public void print() {
        System.out.print("["+this.toString()+"]\n");
    }

    public static void main(String[] args) {
        MyList<Integer> empty = new MyList<>();
        MyList<Integer> one = new MyList<>(1);
        MyList<Integer> two = new MyList<>(1, 2);

        empty.print();
        empty.addMiddle(2);
        empty.print();

        System.out.println();

        one.print();
        one.addMiddle(2);
        one.print();

        System.out.println();

        two.print();
        two.addMiddle(3);
        two.print();
    }
}