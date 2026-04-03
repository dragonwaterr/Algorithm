# [Silver I] T 타일링 - 34516 

[문제 링크](https://www.acmicpc.net/problem/34516) 

### 성능 요약

메모리: 14408 KB, 시간: 108 ms

### 분류

해 구성하기, 홀짝성

### 제출 일자

2026년 4월 3일 18:30:28

### 문제 설명

<p>테트리스 게임에 막 입문한 유틸은 T 스핀 기술을 연습하고 있었다. 여러 번 T 모양 블록을 돌려 맞추다 보니, 문득 이런 생각이 들었다.</p>

<p style="text-align: center;">"T 블록만으로 커다란 격자판을 빈칸 없이 채울 수 있을까?"</p>

<p>그래서 유틸은 실제 게임처럼 블록을 쌓는 대신, 순수하게 T 모양 타일만으로 $N \times N$ 크기의 정사각 격자판을 빈칸 없이 채워보기로 했다.</p>

<p>T 타일은 아래 그림과 같이 T 모양을 기본으로 하며, 시계 방향으로 $0^\circ$, $90^\circ$, $180^\circ$, $270^\circ$ 회전하여 사용할 수 있다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/2c76e413-ef92-4ac6-9022-71942d37f3c0/-/preview/" style="width: 75%; margin-left: auto; margin-right: auto; display: block;"></p>

<p>T 타일은 겹치거나 격자판 밖으로 나가면 안 된다. T 타일을 이용해 격자판을 빈칸 없이 채우는 방법을 구해보자.</p>

### 입력 

 <p>첫 번째 줄에 정수 $N$이 주어진다.</p>

### 출력 

 <p>격자판을 빈칸 없이 채우는 것이 가능하다면, $N$개의 줄에 걸쳐 격자판의 배치를 다음과 같이 출력한다.</p>

<ul>
<li>각 줄에는 길이 $N$의 문자열을 출력한다. 출력할 수 있는 문자는 다음과 같다.
<ul>
<li><span style="color:#e74c3c;"><code>a</code></span>: 타일이 시계 방향으로 $0^\circ$ 회전 상태인 칸</li>
<li><span style="color:#e74c3c;"><code>b</code></span>: 타일이 시계 방향으로 $90^\circ$ 회전 상태인 칸</li>
<li><span style="color:#e74c3c;"><code>c</code></span>: 타일이 시계 방향으로 $180^\circ$ 회전 상태인 칸</li>
<li><span style="color:#e74c3c;"><code>d</code></span>: 타일이 시계 방향으로 $270^\circ$ 회전 상태인 칸</li>
</ul>
</li>
</ul>

<p>격자판을 빈칸 없이 채우는 것이 불가능하다면 첫 번째 줄에 <span style="color:#e74c3c;"><code>-1</code></span>을 출력한다.</p>

