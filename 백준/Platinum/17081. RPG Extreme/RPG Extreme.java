import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // RPG Extreme
    
    static final int[][] dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 

    static int T, n, m;
    static char[][] grid;
    static String exitCode = "";
    static String result = "";
    static boolean usedRE = false;
    static StringBuilder sb = new StringBuilder();
    static Queue<Character> q = new ArrayDeque<>();
    static HashMap<Integer, Monster> monsterBook = new HashMap<>();
    static HashMap<Integer, String[]> itemBook = new HashMap<>();

    static class TaekHee {
        int sx, sy, cx, cy, lv, hp, maxHp, att, aAtt, def, aDef, exp, nExp, oparts;
        Set<String> itemSet = new HashSet<>();
        TaekHee(int sx, int sy, int cx, int cy, int lv, int hp, int maxHp, int att, int aAtt, int def, int aDef, int exp, int nExp, int oparts) {
            this.sx = sx;
            this.sy = sy;
            this.cx = cx;
            this.cy = cy;
            this.lv = lv;
            this.hp = hp;
            this.maxHp = maxHp;
            this.att = att;
            this.aAtt = aAtt;
            this.def = def;
            this.aDef = aDef;
            this.exp = exp;
            this.nExp = nExp;
            this.oparts = oparts;
        }
        void levelUpCheck() { 
            if (exp >= nExp) {
                lv += 1;
                exp = 0;
                nExp = lv * 5;
                maxHp += 5;
                hp = maxHp;
                att += 2;
                def += 2;
            }
        }
        void getItem(int x, int y) { 
            grid[x][y] = '.';
            int pos = getIntPos(x, y);
            String[] itemInfo = itemBook.get(pos);
            String item = itemInfo[0];
            String detail = itemInfo[1];
            if (item.equals("W")) {
                itemSet.add("W");
                aAtt = Integer.parseInt(detail);
                itemBook.remove(pos);
                return;
            }
            if (item.equals("A")) {
                itemSet.add("A");
                aDef = Integer.parseInt(detail);
                itemBook.remove(pos);
                return;
            }
            if (oparts < 4) {
                if (!itemSet.contains(detail)) oparts++;
                itemSet.add(detail);
                itemBook.remove(pos);
            }
        }
        void trapped() { 
            if (itemSet.contains("DX")) {
                hp -= 1;
                return;
            }
            hp -= 5;
        }
        boolean isDeadByTrap() { 
            if (hp > 0) return false;
            if (itemSet.contains("RE")) {
                hp = maxHp;
                cx = sx;
                cy = sy;
                itemSet.remove("RE");
                oparts--;
                return false;
            }
            return true;
        }
        void fightWithMonster(Monster monster) {
            int attack = att + aAtt;
            int firstAttack =
                itemSet.contains("CO") ?
                    itemSet.contains("DX") ?
                        attack * 3 : attack * 2
                    : attack;
            
            if (monster.isBoss && itemSet.contains("HU")) {
                hp = maxHp;
                monster.hp -= Math.max(1, firstAttack - monster.def);
                firstAttack = attack; 
                if(monster.hp < 1) return;
            }
            monster.hp -= Math.max(1, firstAttack - monster.def);
            if(monster.hp < 1) return;

            while(true) {
                hp -= Math.max(1, monster.att - (def + aDef));
                if(hp < 1) return;
                monster.hp -= Math.max(1, attack - monster.def);
                if(monster.hp < 1) return;
            }
        }
        boolean isDeadByMonster() { 
            if (hp > 0) return false;
            if (itemSet.contains("RE")) {
                usedRE = true;
                return false;
            }
            hp = 0;
            return true;
        }
        void getRewardForVictory(int pos) {
            int expReward = monsterBook.get(pos).exp;
            monsterBook.remove(pos);
            grid[cx][cy] = '.'; 
            
            if (itemSet.contains("HR")) {
                hp = Math.min(hp + 3, maxHp);
            }
            if (itemSet.contains("EX")) { 
                exp += (int) ((double) expReward * 1.2);
                return;
            }
            exp += expReward;
        }
    }

    static class Monster {
        String name;
        int att, def, hp, maxHp, exp;
        boolean isBoss;
        Monster(String name, int att, int def, int hp, int maxHp, int exp, boolean isBoss) {
            this.name = name;
            this.att = att;
            this.def = def;
            this.hp = hp;
            this.maxHp = maxHp;
            this.exp = exp;
            this.isBoss = isBoss;
        }
    }
    
    static int getIntPos(int x, int y) {
        return (x * 1000) + y;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new char[n + 1][m + 1];
        int sx = 0;
        int sy = 0;
        int monsters = 0;
        int items = 0;
        for (int i = 1; i <= n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                char c = input[j - 1];
                grid[i][j] = c;
                if (c == '&' || c == 'M') monsters++;
                if (c == 'B') items++;
                if (c == '@') {
                    sx = i;
                    sy = j;
                }
            }
        }
        char[] seq = br.readLine().toCharArray();
        for (char c : seq) q.add(c);
        for (int i = 0; i < monsters; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int att = Integer.parseInt(st.nextToken());
            int def = Integer.parseInt(st.nextToken());
            int maxHp = Integer.parseInt(st.nextToken());
            int exp = Integer.parseInt(st.nextToken());
            boolean isBoss = grid[x][y] == 'M';
            int pos = getIntPos(x, y);
            monsterBook.put(pos, new Monster(name, att, def, maxHp, maxHp, exp, isBoss));
        }
        for (int i = 0; i < items; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            String detail = st.nextToken();
            int pos = getIntPos(x, y);
            itemBook.put(pos, new String[]{name, detail});
        }
        startGame(sx, sy);
        System.out.println(sb);
    }

    static void startGame(int sx, int sy) {
        T = 0;
        grid[sx][sy] = '.';
        TaekHee tk = new TaekHee(sx, sy, sx, sy, 1, 20, 20, 2, 0, 2, 0, 0, 5, 0);

        game:
        while (true) {
            T++;
            int dir = getDirection(q.poll());
            int nx = tk.cx + dt[dir][0];
            int ny = tk.cy + dt[dir][1];
            if (!(nx > n || nx < 1 || ny > m || ny < 1 || grid[nx][ny] == '#')) { 
                tk.cx = nx;
                tk.cy = ny;
            }
            switch (grid[tk.cx][tk.cy]) {
                case '.':
                    break;
                case '^':
                    tk.trapped();
                    if (tk.isDeadByTrap()) {
                        exitCode = "SPIKE TRAP";
                    }
                    break;
                case 'B':
                    tk.getItem(tk.cx, tk.cy);
                    break;
                default:
                    int monsterPos = getIntPos(tk.cx, tk.cy);
                    Monster monster = monsterBook.get(monsterPos);
                    tk.fightWithMonster(monster);
                    if (tk.isDeadByMonster()) {
                        exitCode = "KILLED";
                        break;
                    }
                    if(usedRE) { 
                        tk.hp = tk.maxHp;
                        tk.cx = sx;
                        tk.cy = sy;
                        tk.itemSet.remove("RE");
                        tk.oparts--;
                        monster.hp = monster.maxHp; 
                        usedRE = false;
                        break;
                    }
                    if(monster.isBoss) {
                        exitCode = "WIN";
                    }
                    tk.getRewardForVictory(monsterPos);
                    tk.levelUpCheck();
            }
            if (exitCode.isEmpty() && q.isEmpty()) {
                exitCode = "FINISHED";
            }
            switch (exitCode) {
                case "FINISHED":  
                    result = "Press any key to continue.";
                    grid[tk.cx][tk.cy] = '@';
                    break game;
                case "WIN": 
                    result = "YOU WIN!";
                    grid[tk.cx][tk.cy] = '@';
                    break game;
                case "KILLED": 
                    int monsterPos = getIntPos(tk.cx, tk.cy);
                    Monster monster = monsterBook.get(monsterPos);
                    String monsterName = monster.name;
                    result = "YOU HAVE BEEN KILLED BY " + monsterName + "..";
                    break game;
                case "SPIKE TRAP": 
                    result = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
                    break game;
            }
        }
        makeAnswer(T, tk, result);
    }

    static int getDirection(char dir) {
        switch (dir) {
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'L':
                return 2;
            default:
                return 3;
        }
    }
    static void makeAnswer(int T, TaekHee tk, String result) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(grid[i][j]);
            }
            sb.append("\n");
        }
        sb.append("Passed Turns : ").append(T).append("\n");
        sb.append("LV : ").append(tk.lv).append("\n");
        sb.append("HP : ").append(tk.hp).append("/").append(tk.maxHp).append("\n");
        sb.append("ATT : ").append(tk.att).append("+").append(tk.aAtt).append("\n");
        sb.append("DEF : ").append(tk.def).append("+").append(tk.aDef).append("\n");
        sb.append("EXP : ").append(tk.exp).append("/").append(tk.nExp).append("\n");
        sb.append(result);
    }
}