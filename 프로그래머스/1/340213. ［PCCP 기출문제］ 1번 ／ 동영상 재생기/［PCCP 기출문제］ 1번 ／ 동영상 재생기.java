class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int cur = timeToNumber(pos);
        
        for(String command : commands) {
            cur = checkOpening(cur, op_start, op_end);
            
            if(command.equals("next")) {
                cur += 10;
                cur = secOverflowCheck(cur); // 205
                cur = videoLenOverflowCheck(video_len, cur);
                continue;
            }
            
            // 여기서 -10 까지 처리
            cur = secUnderflowCheck(cur);
        }
        
        cur = checkOpening(cur, op_start, op_end);
        return makeAnswer(cur);
        
    }
    
    // 문자열 시간숫자로 바꾸기
    static int timeToNumber(String time) {
        String tStr = String.join("", time.split(":"));
        return Integer.parseInt(tStr);
    }
    
    // 60 초 안넘게 숫자 조절
    static int secOverflowCheck(int time) { 
        int jumpMinute = (time % 100) / 60; // 1
        int remainSecond = (time % 100) % 60; // 5
        return (time / 100) * 100 + jumpMinute * 100 + remainSecond;
    }
    
    // 비디오 전체 시간을 넘기지 않게 처리
    static int videoLenOverflowCheck(String videoLen, int cur) {
        int vdLen = timeToNumber(videoLen);
        if(vdLen < cur) return vdLen;
        return cur;
    }
    
    // -10 을 했을때 초가 00 ~ 59 사이를 유지하게 조절
    // pos 가 00:10 미만이면 마이너스로 떨어지지 않게 조절
    // -10 한 결과까지 반영해서 리턴
    static int secUnderflowCheck(int time) {
        if(time <= 10) return 0;
        if((time % 100) < 10) {
            return ((time / 100) - 1) * 100 + (time % 100 + 50);
        } 
        return time-10;
    }
    
    // 정답 문자열 구하기
    static String makeAnswer(int time) { // 205 -> 02:50 ? ㅋㅋ
        String answer = "";
        
        if(time < 10) {
            return "00:0" + (time + "");
        }
        
        if(time < 100) {
            return "00:" + (time + "");
        }
        
        if(time < 1000) { // 205 
            answer += "0"; // answer = "0"
        }
        
        answer += (time / 100) + ":";
        
        if(time % 100 < 10) {
            answer += "0" + (time % 100);
            return answer;
        } 

        // if(time % 10 == 0) { // 12:50
        //     answer += (time % 100) + "0";
        //     return answer;
        // }
        
        answer += (time % 100) + "";
        return answer;
    }
    
    // 오프닝 시간 건너뛰기
    static int checkOpening(int time, String start, String end) {
        int stTime = Integer.parseInt(String.join("", start.split(":")));
        int endTime = Integer.parseInt(String.join("", end.split(":")));
        
        if(stTime <= time && time < endTime) {
            time = endTime;
        }
        
        return time;
    }
    
}  