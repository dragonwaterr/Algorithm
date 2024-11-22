import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static int[] inorder;
    static int[] postorder;
    static int[] inorderIndex;
    static int n;

    static class Node {
        int data;
        Node left = null;
        Node right = null;
    }

    static Node atchSub(Node node, int postRootIndex, int start, int end) {
        
        // 좌우 서브트리 크기 계산
        int inRootIndex = inorderIndex[postorder[postRootIndex]];
        int leftSize = inRootIndex - start;
        int rightSize = end - inRootIndex - 1;
        
        // 노드에 값 넣기 
        node.data = inorder[inRootIndex];
        sb.append(node.data).append(" ");
        
        // 왼쪽 서브트리의 루트 붙이기
        if(leftSize != 0) { 
            node.left = atchSub(new Node(), postRootIndex - rightSize - 1, inRootIndex - leftSize, inRootIndex);
        }
        
        // 오른쪽 서브트리의 루트 붙이기
        if(rightSize != 0) { 
            node.right = atchSub(new Node(), postRootIndex-1, inRootIndex + 1, inRootIndex + rightSize + 1);
        }

        return node;
    }

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        postorder = new int[n];
        inorderIndex = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderIndex[inorder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        
        Node root = new Node();
        atchSub(root, n-1, 0, n);
        System.out.println(sb.toString());
    }
}