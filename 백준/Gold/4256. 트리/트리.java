import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 트리
    static StringBuilder sb;
    static int[] preorder;
    static int[] inorder;

    static class Node {
        int data;
        Node left = null;
        Node right = null;
    }

    // 노드의 데이터를 채우고 좌우 자식 노드를 연결하는 메소드
    static Node findRoot(Node node, int rootIndex, int start, int end) {
        if(start == end) return null;

        node.data = preorder[rootIndex];
        int inorderIndex = 0;
        for(int i = start; i < end; i++) {
            if (inorder[i] == node.data) {
                inorderIndex = i;
                break;
            }
        }

        int size = inorderIndex - start;
        node.left = findRoot(new Node(), rootIndex + 1, start, inorderIndex);
        node.right = findRoot(new Node(), rootIndex + size + 1, inorderIndex + 1, end);

        return node;
    }

    // postorder 출력
    static void printPostorder(Node node) {
        if(node.left != null) printPostorder(node.left);
        if(node.right != null) printPostorder(node.right);
        sb.append(node.data).append(" ");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            preorder = new int[n];
            inorder = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            Node root = new Node();
            root = findRoot(root, 0, 0, n); // 트리 연결

            printPostorder(root);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}