import java.util.NoSuchElementException;

class node {
    int data;
    node next;
    public node(int data) {
        this.data = data;
        this.next = null;
    }
    public void printData() {
        System.out.print(data);
    }
}
class llist {
    int size;
    boolean isSorted = false;
    node head;
    node tail;
    public llist() {
        head = tail = null;
    }
    void insert_head(int x) {
        insert_after(new node(x), null);
        this.size++;
    }
    void insert_tail(int x) {
        insert_after(new node(x), tail);
        this.size++;
    }
    void insert_head(node node_add) {
        insert_after(node_add, null);
        this.size++;
    }
    void insert_tail(node node_add) {
        insert_after(node_add,tail);
        this.size++;
    }
    void insert(int value) {
        node newNode = new node(value);
        if(head == null || value <= head.data) {
            newNode.next = this.head;
            this.head = newNode;
            if(tail == null) {
                this.tail = newNode;
            }
            this.size++;
        }
        node current = head;
        while (current.next != null && value > current.next.data) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        if(current == tail) {
            this.tail = newNode;
        }
        this.size++;
    }
    private void insert_after(node t, node pred) {
        if(pred == null) {
            t.next = this.head;
            this.head = t;
        } else {
            t.next = pred.next;
            pred.next = t;
        }
        if(pred == tail) {
            this.tail = t;
        }
    }
    void addBefore(node nodeAfter, int value) {
        if(nodeAfter == null) {
            System.out.println("Node " + nodeAfter + " is null!, cant add before it!");
        }
        if(nodeAfter == head) {
            insert_head(value);
        } else {
            node pred = head;
            while (pred != null && pred.next != nodeAfter) {
                pred = pred.next;
            }
            if(pred != null) {
                node newNode = new node(value);
                newNode.next = nodeAfter;
                pred.next = newNode;
                this.size++;
            }
        }
    }
    void delete_head() {
        delete_after(null);
        this.size--;
    }
    void delete_tail() {
        node[] res = search(tail.data);
        delete_after(res[1]);
        this.size--;
    }
    void deleteValue(int value) {
        node[] res = search(value);
        if(res[0] != null) {
            delete_after(res[1]);
            this.size--;
        }
    }
    void deleteAtIndex(int index) {
        if(index < 0 || index >= this.size) {
            throw  new IndexOutOfBoundsException("Invalid Index!");
        }
        node pred = null;
        node current = head;
        int currentIndex = 0;
        while (current != null && currentIndex < index) {
            pred = current;
            current = current.next;
            currentIndex++;
        }
        delete_after(pred);
        this.size--;
    }
    void deleteNode(node node_del) {
        if(node_del == null) {
            System.out.println("Node is null, nothing to delete");
        }
        if(node_del == head) {
            head = head.next;
            if(head == null) {
                tail = null;
            }
        } else {
            node pred = head;
            while(pred != null && pred.next != node_del) {
                pred = pred.next;
            }
            if(pred != null) {
                pred.next = node_del.next;
                if(node_del == tail) {
                    tail = pred;
                }
            }
        }
        this.size--;
    }
    void delete_after(node pred) {
        node node_del;
        if(pred == null) {
            node_del = this.head;
            this.head = this.head.next;
        } else {
            node_del = pred.next;
            pred.next = node_del.next;
        }
        if(node_del == tail) {
            this.tail = pred;
        }
    }
    node[] search(int x) {
        node t = head, pred = null;
        while (t != null && t.data != x) {
            pred = t; t = t.next;
        }
        // t = null : not found
        // t !- null: found
        return new node[]{t, pred};
    }
    boolean isSorted() {
        return isSorted;
    }

    void sortAscending() {
        node current = head;
        isSorted = false;
        if(head == null || head.next == null) {
            System.out.println("Empty list or only one element, no need to sort!");
        }
        while (!isSorted) {
            isSorted = true;
            while (current != null && current.next != null) {
                if(current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    isSorted = false;
                }
                current = current.next;
            }
            current = head;
        }
    }
    void attachList(llist listToAttach) {
        if(listToAttach.head == null) {
            System.out.println("The list to attach is Empty! Nothing to attach!");
        }
        if(this.head == null) {
            this.head = listToAttach.head;
            this.tail = listToAttach.tail;
        } else {
            this.tail.next = listToAttach.head;
            this.tail = listToAttach.tail;
        }
        this.size += listToAttach.size();
    }
    int max() {
        if(head == null) {
            throw new NoSuchElementException("This list is empty!");
        }
        int max = head.data;
        node current = head.next;
        while (current != null) {
            if(current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }
    int min() {
        if(head == null) {
            throw new NoSuchElementException("This list is empty!");
        }
        int min = head.data;
        node current = head.next;
        while(current != null) {
            if(current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }
    int sum() {
        if(head == null) {
            throw new NoSuchElementException("This list is empty!");
        }
        int sum = 0;
        node current = head;
        while(current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }
    int avg() {
        if(head == null) {
            throw new NoSuchElementException("This list is empty!");
        }
        int avg = 0;
        int sum = 0;
        int size = 0;
        node current = head;
        while (current != null) {
            sum = this.sum();
            size = this.size();
            avg = sum / size;
            current = current.next;
        }
        return avg;
    }

    @Override
    public String toString() {
        String output = "";
        for(node t = head; t != null; t=t.next) {
            output += t.data + " ";
        }
        return output;
    }
    int size() {
        return this.size;
    }
    int[] toArray() {
        int[] array = new int[size];
        int index = 0;
        node current = head;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    void reverse() {
        node prev = null;
        node current = head;
        node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        tail = head;
        head = prev;
    }
    boolean equals(llist list) {
        if (size != list.size) {
            return false;
        }
        node current1 = head;
        node current2 = list.head;

        while (current1 != null && current2 != null) {
            if (current1.data != current2.data) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }
}

class LinkedListUtils {
    public llist mergeLists(llist list1, llist list2) {
        llist mergedList = new llist();
        node currentNodeOne = list1.head;
        node currentNodeTwo = list2.head;

        while (currentNodeOne != null && currentNodeTwo != null) {
            if(currentNodeOne.data <= currentNodeTwo.data) {
                mergedList.insert_tail(currentNodeOne.data);
                currentNodeOne = currentNodeOne.next;
            } else {
                mergedList.insert_tail(currentNodeTwo.data);
                currentNodeTwo = currentNodeTwo.next;
            }
        }
        while (currentNodeOne != null ) {
            mergedList.insert_tail(currentNodeOne.data);
            currentNodeOne = currentNodeOne.next;
        }
        while (currentNodeTwo != null) {
            mergedList.insert_tail(currentNodeTwo.data);
            currentNodeTwo = currentNodeTwo.next;
        }
        return mergedList;
    }
}

public class Code_1_Vo_Van_Vu {
    public static void main(String[] args) {
        llist linkedList = new llist();
        linkedList.insert_tail(4);
        linkedList.insert_head(2);
        linkedList.insert_tail(3);
        linkedList.insert_tail(9);
        linkedList.insert_head(2);
        linkedList.insert_head(10);
        linkedList.insert_head(12);
        linkedList.insert_tail(5);
        linkedList.insert_tail(55);
        linkedList.insert_head(18);

        System.out.println("LINKED LIST NUMBER ONE");
        System.out.println(linkedList);
        linkedList.delete_tail();
        System.out.println(linkedList);

        linkedList.deleteValue(2);
        linkedList.deleteAtIndex(1);
        System.out.println(linkedList.isSorted() ? "THIS LIST IS SORTED!" : "HAVE NOT SORTED YET!");
        System.out.println(linkedList);
        System.out.println("MAX VALUE = " + linkedList.max());
        System.out.println("MIN VALUE = " + linkedList.min());
        System.out.println("SUM OF ALL ELEMENTS IS: " + linkedList.sum());
        System.out.println("AVERAGE OF ALL ELEMENTS IS: " + linkedList.avg());

        System.out.println("SORTED: ");
        linkedList.sortAscending();
        System.out.println(linkedList.isSorted() ? "THIS LIST IS SORTED!" : "HAVE NOT SORTED YET!");
        System.out.println(linkedList);
        linkedList.insert(99);
        linkedList.insert(17);

        System.out.println(linkedList);
        linkedList.reverse();
        System.out.println("AFTER REVERSE: ");
        System.out.println(linkedList);
        System.out.println("Number of node: " + linkedList.size());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        node node1 = new node(23);
        node node2 = new node(56);
        node node3 = new node(131);
        node node4 = new node(42);
        node node5 = new node(65);
        llist linkedList2 = new llist();
        linkedList2.insert_head(node1);
        linkedList2.insert_head(node2);
        linkedList2.insert_head(node3);
        linkedList2.insert_head(node4);
        linkedList2.insert_tail(node5);
        linkedList2.addBefore(node2, 66);

        System.out.println("LINKED LIST NUMBER 2:");
        System.out.println("MAX VALUE = " + linkedList2.max());
        System.out.println("MIN VALUE = " + linkedList2.min());
        System.out.println("SUM OF ALL ELEMENTS IS: " + linkedList2.sum());
        System.out.println(linkedList2);

        linkedList2.deleteNode(node2);
        System.out.println(linkedList2.isSorted() ? "THIS LIST IS SORTED!" : "HAVE NOT SORTED YET!");
        System.out.println(linkedList2);
        linkedList2.reverse();
        System.out.println("AFTER REVERSE: ");
        System.out.println(linkedList2);
        System.out.println("Number of node in list: " + linkedList2.size());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        int[] array1 = linkedList.toArray();
        System.out.println("ARRAY LINKED LIST 1:");
        for (int value: array1) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println("ARRAY LINKED LIST 2:");
        int[] array2 = linkedList2.toArray();
        for(int value: array2) {
            System.out.print(value + " ");
        }

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("MERGED TWO LIST: ");
        LinkedListUtils linkedListMethod = new LinkedListUtils();
        llist mergedList = linkedListMethod.mergeLists(linkedList, linkedList2);
        System.out.println(mergedList);

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("ATTACH LINKEDLIST 2 TO LINKEDLIST 1");
        linkedList2.attachList(linkedList);
        System.out.println(linkedList2);

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        llist linkedList3 = linkedList2;
        System.out.println(linkedList.equals(linkedList2) ? "LinkedList1 EQUAL LinkedList2!" : "LinkedList1 NOT EQUAL LinkedList2!");
        System.out.println(linkedList2.equals(linkedList3) ? "LinkedList2 EQUAL LinkedList3!" : "LinkedList1 NOT EQUAL LinkedList2!");
    }
}