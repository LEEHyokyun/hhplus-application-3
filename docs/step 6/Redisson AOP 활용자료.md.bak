## 개요
- Redis AOP 개념이나 구현은 거의 비슷하였으나, 최대한 공통적으로 활용된 개념을 집중적으로 분석하여 Redisson을 구현할 수 있는 다양한 방안들을 고민해보았습니다.

## 자료
- AOP
[Redis AOP 자료 #1](https://velog.io/@meong/SpringBoot-Redisson-AOP-%EC%A0%81%EC%9A%A9%EA%B8%B0-%EC%9E%AC%EA%B3%A0-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%A0%9C%EC%96%B4)
[Redis AOP 자료 #2](https://velog.io/@daehoon12/%EC%A3%BC%EB%AC%B8-%EC%8B%9C-%EC%9D%BC%EC%96%B4%EB%82%98%EB%8A%94-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0)
[Redis AOP 자료 #3](https://helloworld.kurly.com/blog/distributed-redisson-lock/)
[Redis AOP 자료 #4](https://jongmin4943.tistory.com/entry/Spring-Redisson-%EB%B6%84%EC%82%B0%EB%9D%BDDistribute-Lock-%EC%A2%80-%EB%8D%94-%EC%9E%98-%EC%8D%A8%EB%B3%B4%EA%B8%B0-23-AOP)

- Advice
[Redis AOP Around Advice 원리](https://developer-joe.tistory.com/221)
[AOP/pointcut/JointPoint](https://velog.io/@solar/AOP-%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-%EB%A1%9C%EA%B7%B8-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%82%A8%EA%B8%B0%EA%B8%B0)

- Customized 어노테이션
[어노테이션 구성하기](https://velog.io/@iniestar/Java-interface-class)
[서비스 단위의 key값 전달](https://velog.io/@meong/SpringBoot-Redisson-AOP-%EC%A0%81%EC%9A%A9%EA%B8%B0-%EC%9E%AC%EA%B3%A0-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%A0%9C%EC%96%B4)
[매개변수 및 jointpoint의 args를 파싱하여 key값 전달](https://velog.io/@solar/AOP-%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-%EB%A1%9C%EA%B7%B8-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%82%A8%EA%B8%B0%EA%B8%B0)
[SPEL을 통한 key값 전달](https://velog.io/@daehoon12/%EC%A3%BC%EB%AC%B8-%EC%8B%9C-%EC%9D%BC%EC%96%B4%EB%82%98%EB%8A%94-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0)

- 트랜잭션 분리(joint - Process)
[args](https://www.google.com/search?q=joinPoint.getArgs%28%29%3B&sca_esv=3645c0c05a1f51fb&sxsrf=AHTn8zpN0RNrmqaDeAMZ0PExb6t1MF8ABw%3A1746020102202&source=hp&ei=BicSaOvSCcPP1e8PkLCCkAk&iflsig=ACkRmUkAAAAAaBI1FhjCxL6HT4b1a6tctIgG-fX67NlI&ved=0ahUKEwjr7dbf7_-MAxXDZ_UHHRCYAJIQ4dUDCBk&uact=5&oq=joinPoint.getArgs%28%29%3B&gs_lp=Egdnd3Mtd2l6IhRqb2luUG9pbnQuZ2V0QXJncygpO0jpA1AAWABwAHgAkAEAmAEAoAEAqgEAuAEDyAEA-AEC-AEBmAIAoAIAmAMAkgcAoAcAsgcAuAcA&sclient=gws-wiz)
[process](https://developer-joe.tistory.com/221)
