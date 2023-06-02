import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class node {
    int key;
    node left, right;
    public node(int key) {
        this.key = key;
        left = right = null;
    }
    @Override
    public String toString() {
        return key + " ";
    }

    public int getKey() {
        return key;
    }
    public node getLeft() {
        return left;
    }
    public node getRight() {
        return right;
    }

    
}

class BST {
    node root;
    public BST() {
        root = null;
    }

    node[] search(int k) {
        node position = root;
        node parent = null;
        while(position != null) {
            if(position.key == k) {
                break;
            }
            parent = position;
            if(position.key < k) {
                position = position.right;
            } else {
                position = position.left;
            }
        }
        return new node[]{position, parent};
    }
    boolean insert(int new_key) {
        node[] result = search(new_key);
        if(result[0] != null) {
            return false;
        }
        node new_node = new node(new_key);
        if(result[1] == null) {
            root = new_node;
        } else if(result[1].key > new_node.key) {
            result[1].left = new_node;
        } else {
            result[1].right = new_node;
        }
        return true;
    }
    ArrayList<Integer> in_order() {
        ArrayList<Integer> traversal = new ArrayList<>();
        in_order(root, traversal);
        return traversal;
    }
    private void in_order(node subtree, ArrayList<Integer> traversal) {
        if(subtree == null) {
            return;
        }
        in_order(subtree.left, traversal);
        traversal.add(subtree.key);
        in_order(subtree.right, traversal);
    }
    node[] find_right_most() {
        if (empty()) {
            return new node[] {null,null};
        }
        node right_most = root, parent = null;
        while (right_most.right != null) {
            parent = right_most;
            right_most = right_most.right;
        }
        return new node[] {right_most, parent};
    }

    boolean empty() {
        return root == null;
    }
    public BST(int[] a) {
        this.root = null;
        for(int i : a) {
            insert(i);
        }
    }
    double average() {
        int[] sc = new int[]{0,0};
        sum_count(root, sc);
        return sc[0] / (sc[1]*1.0);
    }
    private void sum_count(node subtree, int[] sc) {
        if(subtree == null) return;
        sum_count(subtree.left,sc);
        sc[0] += subtree.key; sc[1]++;
        sum_count(subtree.right, sc);
    }
    int heigh() {
        return high(root);
    }
    private int high(node subtree) {
        if (subtree == null) {
            return 0;
        }
        int h_lef = high(subtree.left);
        int h_right = high(subtree.right);
        return  Math.max(h_lef, h_right) + 1;
    }
    ArrayList<KeyLevel> level_order() {
        ArrayList<KeyLevel> bfs = new ArrayList<>();
        Queue<node> visit = new LinkedList<>();
        visit.add(root);
        visit.add(null);
        int level = 0;
        while(!visit.isEmpty()) {
            node current = visit.remove(); //6
            if(current == null) {
                if(visit.isEmpty()) 
                    break;
                level++;
                visit.add(null);
                continue;
            }
            bfs.add(new KeyLevel(current.key, level)); //(6,0)
            if(current.left != null) visit.add(current.left);
            if(current.right != null) visit.add(current.right);
        }
        return bfs;
    }
    ArrayList<Integer> pre_order() {
        ArrayList<Integer> traversal = new ArrayList<>();
        pre_order(root, traversal);
        return traversal;
    }
    private void pre_order(node subtree, ArrayList<Integer> traversal) {
        if(subtree == null) {
            return;
        }
        traversal.add(subtree.key);
        pre_order(subtree.left, traversal);
        pre_order(subtree.right, traversal);
    }
    public BST(int[] lNr, int[] Nlr) { // lNr: inorder Nlr: preorder
        int i_lNr = 0, j_Nlr = 0;
        Stack<node> stk = new Stack<>();
        node last = (root = new node(-1)); //dummy node
        while(i_lNr < lNr.length) {
            boolean found_left_most = false;
            while(!found_left_most) {
                node new_node = new node(Nlr[j_Nlr] );
                if(last != null) { //lan dau noi vao last right
                    last.right = new_node;
                    last = null;
                } else {  //last = null: cac lan sau noi vao left cua cha...
                    stk.peek().left = new_node;
                }
                stk.push(new_node);
                if(lNr[i_lNr] == Nlr[j_Nlr]) {
                    found_left_most = true;
                }
                j_Nlr++;
            }
            while (!stk.isEmpty() && stk.peek().key == lNr[i_lNr]) {
                last = stk.pop(); i_lNr++;
            }
        }
        root = root.right;
    }

    void printTreeWithASCII(node nNode, String indent, boolean last) {
        if(nNode != null) {
            System.out.print(indent);
            if(last) {
                System.out.print("└── ");
                indent += "     ";
            } else {
                System.out.print("├── ");
                indent += "│   ";
            }
            System.out.println(nNode.key);
            printTreeWithASCII(nNode.left, indent, false);
            printTreeWithASCII(nNode.right, indent, true);
        }
    }
    void printTreeBaseOnLevel(node nNode, int level) {
        if(nNode == null) {
            return;
        }
        printTreeBaseOnLevel(nNode.right, level + 1);
        for(int i = 0; i < level; i++) 
            System.out.print("    ");
        System.out.println("   " + nNode.key);
        printTreeBaseOnLevel(nNode.left, level + 1);
    }
    void printTreeBaseOnLevel() {
        if(root == null) {
            return;
        }
        Queue<node> queue = new LinkedList<>();
        queue.add(root);
        System.out.println("BST: ");
        while(!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + (levelSize) + ": ");
            for(int i = 0; i < levelSize; i++) {
                node currentNode = queue.poll();
                System.out.print(currentNode.key + " ");

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                } 
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            System.out.println();
        }
    }
}

class KeyLevel {
    int key, level;
    public KeyLevel(int key, int level) {
        this.key = key;
        this.level = level;
    }
    @Override
    public String toString() {
        return "(" + key + ", " + level  + ")";
    }
}

public class Main {
    public static void main (String[] args) {
        BST T4 = new BST(new int[]{1,2,3,4,5,6,7,8,9,10}, new int[]{6,3,2,1,4,5,8,7,9,10});
        ArrayList<Integer> result = T4.in_order();
        System.out.println("In order traversal: " + Arrays.toString(result.toArray(new Integer[result.size()])) );
        ArrayList<Integer> result2 = T4.pre_order();
        System.out.println("Pre order traversal: " + Arrays.toString(result2.toArray(new Integer[result2.size()])) );

        int[] a = new int[]{6,8,3,4,2,5,9,7,1,10};
        BST T = new BST(a);        
        System.out.println("The high is: " + T.heigh());
        System.out.println("LEVEL: " + T.level_order());
        Arrays.sort(a);
        T = new BST(a);
        System.out.println("The high is: " + T.heigh());
        System.out.println("The average: " + T.average());
        System.out.println("In-order traversal: ");
        T.in_order().forEach(integer -> System.out.print(integer + " "));
        System.out.println();
        System.out.println("The right most: " + Arrays.toString(T.find_right_most()));
        System.out.println("Tree sort: " + Arrays.toString(tree_sort(new int[] {6,3,8,9,10,2,7,4,1,5})));

        System.out.println("\nDisplay way 1:");
        T4.printTreeBaseOnLevel();
        System.out.println();
        System.out.println("Display way 2:");
        T4.printTreeBaseOnLevel(T4.root, 1);
        System.out.println();
        System.out.println("Display way 3:");
        T4.printTreeWithASCII(T4.root, "", true);
        System.out.println();
      
    }

    static Integer[] tree_sort(int[] a) {
        BST newT = new BST();
        for (int i :a) {
            newT.insert(i);
        }
        ArrayList<Integer> result = newT.in_order();
        return result.toArray(new Integer[result.size()]);
    }
}