import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 어항 정리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        tankMap = new int[101][101];
        answer = 0;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ptr = 101 - n;
        D = n;
        d = 1;
        h = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tankMap[100][ptr + i] = Integer.parseInt(st.nextToken());
        }

        renewPopulation();
        int dif = getDif();

        while(k < dif) {
            addFish();
            flip1();
            bfs();
            makeTankFlat();
            flip2();
            bfs();
            makeTankFlat();
            renewPopulation();
            dif = getDif();
            answer++;
        }

        System.out.println(answer);
    }

    static final int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static HashMap<Integer, List<Integer>> populationMap;
    static int[][] tankMap;
    static int ptr, D, d, h;
    static int n, k, answer;

    static void renewPopulation() {
        populationMap = new HashMap<>();

        for (int i = ptr; i < ptr + n; i++) {
            int fish = tankMap[100][i];
            populationMap.putIfAbsent(fish, new ArrayList<>());
            populationMap.get(fish).add(i);
        }
    }

    static Integer getDif() {
        Set<Integer> keySet = populationMap.keySet();
        return Collections.max(keySet) - Collections.min(keySet);
    }

    // 1
    static void addFish() {
        int minFish = Collections.min(populationMap.keySet());
        ArrayList<Integer> minFishTanks = new ArrayList<>(populationMap.get(minFish));
        for (int idx : minFishTanks) {
            tankMap[100][idx]++;
        }
    }


    // 2
    static void flip1() {
        int cnt = 0; // 2번 flip 할때마다 d 가 1씩 길어짐
        while(D - d >= h) {
            cnt++;

            for (int i = d-1; i >= 0; i--) {
                int cy = ptr + i;
                int nx = 100 - (d-i);

                for (int j = 0; j < h; j++) {
                    int cx = 100 - j;
                    int ny = ptr + d + j;
                    tankMap[nx][ny] = tankMap[cx][cy];
                    if(cx == nx && cy == ny) {
                        continue;
                    }
                    tankMap[cx][cy] = 0;
                }
            }

            D -= d;
            ptr += d;
            d = cnt % 2 == 0 ? d+1 : d;
            h = cnt % 2 == 1 ? h+1 : h;
        }

    }

    // 3
    static void bfs() {
        int[][] diff = new int[h][100-ptr+1];
        boolean[][] visited = new boolean[h][100-ptr+1];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < diff[i].length; j++) {
                int cx = 100 - h + (i+1);
                int cy = ptr + j;
                int cf = tankMap[cx][cy];

                if(cf == 0) continue;
                visited[i][j] = true;

                for (int l = 0; l < 4; l++) {
                    int nx = cx + dt[l][0];
                    int ny = cy + dt[l][1];

                    if(nx <= 100-h || nx > 100 || ny < ptr || ny > 100) continue;
                    if(tankMap[nx][ny] == 0) continue;
                    if(visited[i + dt[l][0]][j + dt[l][1]]) continue;

                    int nf = tankMap[nx][ny];
                    int q = Math.abs((cf - nf) / 5);

                    if(q < 1) continue;

                    if(cf > nf) {
                        diff[i][j] -= q;
                        diff[i+dt[l][0]][j+dt[l][1]] += q;
                        continue;
                    }
                    diff[i][j] += q;
                    diff[i+dt[l][0]][j+dt[l][1]] -= q;
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < diff[i].length; j++) {
                int cx = 100 - h + (i+1);
                int cy = ptr + j;
                tankMap[cx][cy] += diff[i][j];
            }
        }
    }

    // 4
    static void makeTankFlat() {
        int front = (ptr + d) - (d * h);
        for (int i = ptr; i < ptr + d; i++) {
            for (int j = 100; j > 100-h; j--) {
                tankMap[100][front] = tankMap[j][i];
                if(!(j == 100 && i == front)) {
                    tankMap[j][i] = 0;
                }
                front++;
            }
        }
        ptr = (ptr + d) - (d * h);
        d = 1;
        D = n;
        h = 1;
    }

    // 5
    static void flip2() {
        for (int cnt = 0; cnt < 2; cnt++) {
            for (int i = 0; i < 1 + cnt; i++) {
                int cx = 100 - ((cnt + 2) % 2) + i;
                int tx = 99 - cnt - i;
                for (int j = 0; j < D / 2; j++) {
                    int cy = ptr + j;
                    int ty = 100 - j;
                    tankMap[tx][ty] = tankMap[cx][cy];
                    tankMap[cx][cy] = 0;
                }
            }
            ptr += D/2;
            d = D/2;
            D /= 2;
            h *= 2;
        }
    }

}