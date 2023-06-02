
class Node {
    int data;
    int priority;
    Node next;
}
class Solution {
    Node node = new Node();
    Node newNode(int d, int p)
    {
        Node temp = new Node();
        temp.data = d;
        temp.priority = p;
        temp.next = null;

        return temp;
    }

    // Return the value at head
   int peek(Node head)
    {
        return (head).data;
    }

    // Removes the element with the
// highest priority from the list
    Node pop(Node head)
    {
        Node temp = head;
        (head) = (head).next;
        return head;
    }

    // Function to push according to priority
    Node push(Node head, int d, int p)
    {
        Node start = (head);

        // Create new Node
        Node temp = newNode(d, p);

        // Special Case: The head of list has lesser
        // priority than new node. So insert new
        // node before head node and change head node.
        if ((head).priority < p) {

            // Insert New Node before head
            temp.next = head;
            (head) = temp;
        }
        else {

            // Traverse the list and find a
            // position to insert new node
            while (start.next != null &&
                    (start.next.priority > p || start.next.priority == p)) {
                start = start.next;
            }

            // Either at the ends of the list
            // or at required position
            temp.next = start.next;
            start.next = temp;
        }
        return head;
    }

    // Function to check is list is empty
    int isEmpty(Node head)
    {
        return ((head) == null)?1:0;
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node pq = solution.newNode(4,1);
        pq = solution.push(pq, 5, 2);
        pq = solution.push(pq, 6, 3);
        pq = solution.push(pq, 7, 0);
        pq = solution.push(pq, 8, 2);
        while (solution.isEmpty(pq)==0) {
            System.out.printf("%d ", solution.peek(pq));
            pq=solution.pop(pq);
        }
    }
}