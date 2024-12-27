## 기존 로직
- insert(X) 를 재귀함수로 구현
---
### 결과 : 시간 초과

#### 원인
- N 이 최대일 때 편향 트리인 경우, insert(X) 호출만 1 ~ 299,999 까지 합만큼 실행 : 299,999 * 300,000 / 2
  - insert 호출 횟수 == 현재노드가 자리할 depth - 1
  - 트리를 직접 구현하지 않고 현재노드의 부모노드 depth 를 알아내야함
---
# CoreLogic
```java
depth[k] = Math.max(depth[TreeSet.higher(k)], depth[TreeSet.lower(k)]) + 1;
```

- TreeSet Class 의 higher(x), lower(x) 메소드를 활용해 parent 의 depth 를 알아낼 수 있다.
  - TreeSet.higher(k) : TreeSet 에 들어있는 값 중에 k 보다 크면서 가장 작은 숫자를 반환 (없다면 NullPointerException 발생)
  - TreeSet.lower(k) : TreeSet 에 들어있는 값 중에 k 보다 작으면서 가장 큰 숫자를 반환 (없다면 NullPointerException 발생)
1) BST 에 들어오는 모든 노드는 들어온 직후에는 Leaf 노드이다.
2) 새롭게 들어온 노드 k 의 위치에 따라 분류
```txt
1. k 가 parent 의 left_child 인 경우
     higher(k) = k 의 parent
     lower(k) = k 의 parent 의 parent OR NUL
2. k 가 parent 의 right_child 인 경우
     higher(k) = k 의 parent 의 parent OR NULL
     lower(k) = k 의 parent
```
3) 정리한 내용에 따라 k 가 left, right 어디든 간에 higher(k) 와 lower(k) 값 중에 더 큰 값이 k 의 parent 노드의 depth 임을 알 수 있다.
4) 결과적으로 k 의 depth 는 2) 에서 구한 depth[parent of k] + 1 값이 된다.
