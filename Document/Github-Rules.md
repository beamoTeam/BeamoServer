# Github Rules

## **Git**

### **Branch 전략**

항상 유지되는 메인 브랜치(master, develop, docs)과 일정 기간 동안만 유지되는 보조 브랜치 feature, release, hotfix) 크게 두가지의 브랜치로 분류해 사용할 예정이다.  참고 [Git-Flow](https://nvie.com/posts/a-successful-git-branching-model/)



작업은 보조 브랜치에서 하고 메인 브랜치에 머지하는 방식이다.

예를 들어 feature 브랜치로 기능을 구현하면서 커밋을 하고, 하나의 기능 구현이 완성 되면 develop으로 스쿼시 커밋을 해서 묶는다. develop에서 봤을 땐 하나의 기능의 추가만 기록만 보이게 된다.

추가로 머지하면 하위 브랜치는 즉시 삭제한다. 풀리퀘스트 하면서 바로 삭제하면 편하다. 서버를 혼자서 개발하기 때문에 풀리퀘스트를 자주 하지 않지만.



### **Type**

| **제목**    | **내용**                                             |
| ----------- | ---------------------------------------------------- |
| **master**  | 배포 관리 브랜치                                     |
| **develop** | 개발 관리 브랜치                                     |
| **docs**    | 문서 관리 브랜치                                     |
| **release** | 버전 관리                                            |
| ---         | ---                                                  |
| **feature** | develop의 보조 브랜치로 기능 코드 추가 및 업데이트   |
| **test**    | develop의 보조 브랜치로 테스트 코드 추가 및 업데이트 |
| **hotfix**  | 긴급 버그 고침                                       |
| **refact**  | 리팩토링                                             |


## **Branch Naming**

- format
    - 슬래시(/)로 카테고리화, 뒤에 붙는 기능 및 내용을 대표하는 문구는 대시(-)로 연결

```
<제목>-<내용>
```

- example

```
feat-login-screen  # 새로운 기능/로그인 화면 개발
```

## **commit message**

- format
    - 명령형으로 적어 명확하게 내용을 표기

```
<형상 관리 규칙 제목>: <comment>
```

- example
    - `feat: getService 기능 추가`
    - `fix: getService 문제 제거`
    - `refactor: logic refactoring`
    - `style: 탭을 띄어쓰기로 바꿈`
    - `test: getService에 대한 Mock Test`
    - `doc: README.md에 대한 설명 추가`
    - `chore: 빌드 스크립트 추가`
    - `deps: mvc dependency 버전 변경`

### **Branch Rules**

---

- **mater** : 이 브랜치는 실제로 서비스 제공
- **release branch** : Release를 위해 사용되는 브랜치
- **develop** : 기능 및 하위 단위 브랜치. 실제 배포 전에 이 브랜치에서 확인 및 테스트, 해당 브랜치에 개발한 내역들이 쌓임.

### **Merge**

---

- **master ← release branch** : release branch 를 통해서 merge
- **release branch ← develop** : Squash Commit을 남기는 방법으로 작업하기. release 에는 구체적인 변동 사항을 깔끔하게 정리
- **develop ← 하위** : Squash Commit을 남기는 방법으로 작업하기. develop에는 구체적인 모든 사항을 깔끔하게 정리

