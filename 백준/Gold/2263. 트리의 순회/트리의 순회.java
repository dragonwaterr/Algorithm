import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static int[] inorder;
    static int[] postorder;
    static int n;

    static class Node {
        int data;
        Node left = null;
        Node right = null;
    }

    static Node atchSub(Node node, int postRootIndex, int start, int end) {
        
        // 좌우 서브트리 크기 계산
        int inRootIndex = 0;
        for(int i = start; i < end; i++) {
            if(inorder[i] == postorder[postRootIndex]) {
                inRootIndex = i;
                break;
            }
        }
        int leftSize = inRootIndex - start;
        int rightSize = end - inRootIndex - 1;
        
        // 노드에 값 넣기 
        node.data = inorder[inRootIndex];

        // 오른쪽 서브트리의 루트 붙이기
        if(rightSize != 0) { 
            node.right = atchSub(new Node(), postRootIndex-1, inRootIndex + 1, inRootIndex + rightSize + 1);
        }

        // 왼쪽 서브트리의 루트 붙이기
        if(leftSize != 0) { 
            node.left = atchSub(new Node(), postRootIndex - rightSize - 1, inRootIndex - leftSize, inRootIndex);
        }

        return node;
    }

    static void makePreorder(Node node) {
        sb.append(node.data).append(" ");
        if(node.left != null) makePreorder(node.left);
        if(node.right != null) makePreorder(node.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        postorder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        
        Node root = new Node();
        atchSub(root, n-1, 0, n);
        
        sb = new StringBuilder();
        makePreorder(root);
        System.out.println(sb.toString());
    }
}