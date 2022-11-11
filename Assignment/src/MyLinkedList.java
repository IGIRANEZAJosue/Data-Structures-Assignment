                    /*DATA STRUCTURES ASSIGNMENT */

//Group Members below

/*
- SHYAKA Rukundo Kelly Hubert       221018959
- SHEJA Lervy Emeric                221008663
- IGIRANEZA Josue                   221026624
- SIBO Arnaud Francois              221015343
*/


public class MyLinkedList {

    class Node {

        int data;
        Node next;

        Node() {
            next = null;
        }

        Node(int a) {
            this.data = a;
            next = null;
        }

        public int value() {
            return data;
        }
    }

    Node head;
    Node tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedList(int a) {
        head = tail = new Node(a);
        size = 1;
    }

    public void insertFront(int a) {
        Node newNode = new Node(a);
        if (head != null) {

            newNode.next = head;
            head = newNode;
        } else {
            head = newNode;
            tail = newNode;
        }
        ++size;

    }

    public void insertBack(int a) {
        Node newNode = new Node(a);
        tail.next = newNode;
        tail = newNode;
        ++size;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        } else {
            Node current = head;
            String s = "[";
            while (current != null) {
                s += current.data;
                if (current.next == null) {
                    s += "]";
                } else {
                    s += ",";
                }
                current = current.next;
            }
            return s;
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    public void reverse() {
        
        if (this.size() <= 1){
            return;
        } 
        else {

            Node previous = this.head;
            Node forward = previous.next;
            Node other;
            
            while (forward.next != null) {
                other = forward.next;
                forward.next = previous;
                previous = forward;
                forward = other;
            }

            forward.next = previous;
            Node temp = head;
            head = tail;
            tail = temp;
            tail.next = null;
        }
    }

    public void insertAt(int a, int i) {
        if (i < 0 || i > this.size()) {
            return;
        } 
        else if (i == 0) {
            insertFront(a);
        } 
        else if (i == size()) {
            insertBack(a);
        } 
        else {
            Node current = head;
            Node forward = head.next;
            for (int j = 1; j < i; ++j) {
                current = current.next;
                forward = forward.next;
            }
            Node newNode = new Node(a);
            current.next = newNode;
            newNode.next = forward;
        }
    }

    public boolean equals(MyLinkedList l) {
        if (this.size() != l.size()) {
            return false;
        }
        
        Node h1 = this.head;
        Node h2 = l.head;
        while (h1 != null) {
            if (h1.data != h2.data) {
                return false;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    public MyLinkedList concat(MyLinkedList list) {
        this.tail.next = list.head;
        this.tail = list.tail;
        this.tail.next = null;
        return this;
    }

    public static boolean isSorted(MyLinkedList list) {
        
        Node currentNode = list.head;
        
        while (currentNode.next != null) {
            if (currentNode.data > currentNode.next.data) {
                return false;
            }
            
            currentNode = currentNode.next;
        }
        return true;
    }

    public MyLinkedList merge(MyLinkedList list){
        if (!isSorted(this)) {
            return null;
        }
        
        if (!isSorted(list)) {
            return null;
        }
        
        Node currentOne, currentTwo, resultNode;
        
        if (this.head.data < list.head.data) {
            currentOne = this.head.next;
            currentTwo = list.head;
        } 
        else {
            this.head = list.head;
            currentTwo = list.head.next;
            currentOne = this.head;
        }
        resultNode = this.head;
        while (currentOne != null || currentTwo != null) {
          
            if (currentOne == null) {
                resultNode.next=currentTwo;
                resultNode = currentTwo;
                currentTwo = currentTwo.next;
            } 
            else if (currentTwo == null) {
                resultNode.next=currentOne;
                resultNode = currentOne;
                currentOne = currentOne.next;

            } 
            else if (currentOne.data < currentTwo.data) {
                resultNode.next = currentOne;
                resultNode = currentOne;
                currentOne = currentOne.next;
            }
            else{
                resultNode.next = currentTwo;
                resultNode = currentTwo;
                currentTwo = currentTwo.next;
            }
        }
        
        this.tail = resultNode;
        resultNode.next=null;
        return this;
    }
    public static void main(String args[]) {
        
        MyLinkedList list1 = new MyLinkedList();
        list1.insertFront(1);
        list1.insertBack(2);
        list1.insertBack(10);
        list1.insertBack(15);
        list1.insertFront(-4);
        
        System.out.println("*********************************");
        System.out.println();
        System.out.println("Sorted List One:" );
        System.out.println(list1.toString());
        System.out.println();
        
        MyLinkedList list2 = new MyLinkedList();
        list2.insertFront(5);
        list2.insertBack(6);
        list2.insertBack(7);
        list2.insertBack(11);
        
        System.out.println("*********************************");
        System.out.println();
        System.out.println("Sorted List Two:");
        System.out.println(list2.toString());
        System.out.println();
        
        System.out.println("*********************************");
        System.out.println();
        System.out.println("Merged List of both: ");
        System.out.println(list1.merge(list2).toString());
        System.out.println();
        
        System.out.println("*********************************");
        System.out.println();
        System.out.println("List One concatenated to list two");
        System.out.println(list1.concat(list2));
        System.out.println();
        System.out.println("*********************************");
    }
}

