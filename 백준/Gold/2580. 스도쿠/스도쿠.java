import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    // 스도쿠
    static int[][] sudoku = new int[9][9];
    static Set<Integer>[] squares = new Set[9]; // 각 3*3 사각형마다 포함된 숫자 set
    static Set<Integer>[] rows = new Set[9];  // 각 행에 포함된 숫자 set
    static Set<Integer>[] cols = new Set[9]; // 각 열에 포함된 숫자 set

    public static void main(String[] args) throws IOException {
        run(init());
    }

    // 초기화 메소드
    static int init() throws IOException {
        for(int i = 0; i < 9; i++) {
            squares[i] = new HashSet<>();
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int isNotZero = 0;
        for(int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                int curNum = Integer.parseInt(st.nextToken());
                sudoku[i][j] = curNum;
                if(curNum != 0) {
                    isNotZero++;
                    rows[i].add(curNum);
                    cols[j].add(curNum);
                    squares[getSquareNumber(i, j)].add(curNum);
                }
            }
        }
        br.close();
        return isNotZero;
    }

    // 수정 : x, y 를 count 를 이용해서 9로 나눈 몫과 나머지로 구할 수 있음!
    // (x, y) 가 squares Set 에서의 속한 인덱스를 구한다 -> 수정
    static int getSquareNumber(int x, int y) {
        if(x < 3) {
            if(y < 3) return 0;
            if(y < 6) return 1;
            else return 2;
        }
        if(x < 6) {
            if(y < 3) return 3;
            if(y < 6) return 4;
            else return 5;
        }
        if(y < 3) return 6;
        if(y < 6) return 7;
        else return 8;
    }

    // 처리 메소드
    static void run(int isNotZero) {
        fillSudoku(0, 0, isNotZero);
    }

    // 스도쿠 퍼즐을 작은 행 -> 열 좌표 우선으로 채운다
    static void fillSudoku(int x, int y, int cnt) {

        // 81 개의 칸에 숫자가 모두 채워졌다면 종료
        if(cnt == 81) {
            printAnswer();
            // return 문을 사용하면 다음 케이스들을 또 검사하게 됨. 프로그램 종료 필요
            System.exit(0);
        }

        // 다음 칸의 좌표 구하기
        int nx = x, ny = 0;
        if(y == 8) {
            nx = x+1;
        } else {
            ny = y+1;
        }

        // 초기값이 저장된 칸이라면 그 다음 칸으로 넘어가기
        if(sudoku[x][y] > 0) {
            fillSudoku(nx, ny, cnt);
            return;
        }

        int squareNumber = getSquareNumber(x, y);

        // 빈칸에 들어갈 수를 오름차순으로 넣어보기
        for(int i = 1; i <= 9; i++) {
            // (i, j) 가 포함된 3*3 사각형에 이미 존재하는 수라면 넘어가기
            if(squares[squareNumber].contains(i)) continue;
            // i 행에 이미 존재하는 수라면 넘어가기
            if(rows[x].contains(i)) continue;
            // j 열에 이미 존재하는 수라면 넘어가기
            if(cols[y].contains(i)) continue;

            sudoku[x][y] = i;
            squares[squareNumber].add(i);
            rows[x].add(i);
            cols[y].add(i);

            fillSudoku(nx, ny, cnt+1);

            sudoku[x][y] = 0;
            squares[squareNumber].remove(i);
            rows[x].remove(i);
            cols[y].remove(i);
        }
    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}