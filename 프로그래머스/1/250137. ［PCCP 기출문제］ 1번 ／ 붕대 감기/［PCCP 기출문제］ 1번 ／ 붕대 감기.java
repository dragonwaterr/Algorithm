class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {       
        // 시전시간, 초당 회복량, 추가 회복량
        // 최대 체력
        // 공격시간 - 피해량
        
        int answer = 0;
        
        int maxHealth = health; // 최대 체력
        int reqTime = bandage[0]; // 시전 시간
        int healPS = bandage[1]; // 힐 퍼 세크
        int reward = bandage[2]; // 붕대 성공 보상
        int bdcnt = 0; // 연속성공 체크
        
        int attcnt = 0; // 누적 공격 횟수
        int endTime = attacks[attacks.length-1][0]; // 마지막 공격 시간(초)
        
        for(int i = 1; i <= endTime; i++) {
            // 공격받는지 체크
                // 받으면 체력감소, 카운트 초기화
                // 죽었다면 -1 리턴
            if(attacks[attcnt][0] == i) {
                health -= attacks[attcnt][1];
                bdcnt = 0;
                attcnt++;
                
                if(health <= 0) return -1;
                if(i == endTime) return health;
                continue;
            }
            // 체력회복
                // 1 회복
                // 카운트가 t 초 충족했는지 확인하고 보너스 지급, 카운트 초기화
            
            // 붕대감기 성공 1회 추가
            bdcnt++; 
            
            // 최대체력 넘지 않는 선에서 체력 회복
            if(health + healPS <= maxHealth) {
                health += healPS;
            } else {
                health = maxHealth;
            }
            
            // t번 연속 시전 성공 했다면 보상지급
            if(bdcnt == reqTime) {
                if(health + reward <= maxHealth) {
                    health += reward;
                } else {
                    health = maxHealth;
                }
                bdcnt = 0;
            }
            
        }
        return answer;
    }
}