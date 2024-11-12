import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        boolean[][] board2 = new boolean[N][M];
        boolean[][] board3 = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            // 한줄 입력받고 board2에 한줄씩 저장하기
            for(int j = 0; j < M; j++) {
                // 'W'면 true로 boolean배열에 저장
                if(str.charAt(j) == 'W') {
                    board3[i][j] = true;
                }
                // 'B'면 false로 저장
                else {
                    board3[i][j] = false;
                }
            }
        }

        int count;
        int min = 64;

        // 최초 지점 정하기
        for(int i=0; i<=N-8; i++) {
            for(int j=0; j<=M-8; j++) {
                // count 횟수 새로운 8*8 체스판 비교위해 초기화
                count = 0;
                // 색칠해서 count다 구한다음에
                // 다시 입력받은 배열과 똑같은 상태로 초기화!
                for(int a=0; a < board3.length; a++) {
                    System.arraycopy(board3[a], 0, board2[a], 0, board2[a].length);
                }
                // 그 지점에서 부터 8*8 체스판을 설정
                for(int g=0; g < 2; g++) {
                    // g는 2번 돌리면서 체스판 첫 번째 칸을 색칠한 경우와 아닌 경우를 나눠서 구하기
                    for(int k=i; k < i+7; k++) {
                        for(int l=j; l < j+7; l++) {
                            // 첫 번째 칸을 바꾸지 않은 색칠
                            if(g==0) {
                                if(board2[k][l] == board2[k][l + 1]) {
                                    board2[k][l + 1] = !board2[k][l + 1];
                                    count++;
                                }

                                if(board2[k][l] == board2[k + 1][l]) {
                                    board2[k + 1][l] = !board2[k + 1][l];
                                    count++;
                                }
                            }
                            // 첫 번째 칸을 바꾼 색칠
                            else {
                                if(board2[k][l] == board2[k][l + 1]) {
                                    board2[k][l + 1] = !board2[k][l + 1];
                                    count++;
                                }

                                if(board2[k][l] == board2[k + 1][l]) {
                                    board2[k + 1][l] = !board2[k + 1][l];
                                    count++;
                                }
                            }
                        }
                    }
                    if(board2[i+7][j+6] == board2[i+7][j+7]) {
                        board2[i+7][j+7] = !board2[i+7][j+7];
                        count++;
                    }
                    if(count <= min) {
                        min = count;
                    }
                    //count의 초기화
                    count = 0;
                    //체스판의 초기화
                    for(int a=0; a < board3.length; a++) {
                        System.arraycopy(board3[a], 0, board2[a], 0, board2[a].length);
                    }
                    board2[i][j] = !board2[i][j];
                    count++;
                }
            }
        }
        System.out.println(min);
    }
}