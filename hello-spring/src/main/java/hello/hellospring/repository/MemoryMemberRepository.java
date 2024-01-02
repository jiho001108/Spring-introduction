package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/* DB대신할 메모리 구현체 클래스 만들기
* 회원 리포지토리 메모리 구현체 */
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); /* <키, 값>, <key, value>, <id, name> */
    private static long sequence = 0L; /* 키값을 생성: 0, 1, 2 ... */

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 멤버 id값 세팅(단순히 1씩 증가시킴)
        store.put(member.getId(), member); // store(Map)에 멤버 저장
        return member; // 저장된 결과 반환
    }

    @Override
    /* stroe에서 꺼내기 */
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional.ofNullable()로 감싸면 NULL도 클라가 처리 가능해진다
    }

    @Override
    /*  주어진 이름과 일치하는 Member 객체를 찾는다. */
    public Optional<Member> findByName(String name) {
        /* 맴에서 루프를 돌면서 하나 찾으면 바로 반환 */
        return store.values().stream() // store 맵의 값(Member 객체들)들을 스트림으로 변환
                .filter(member -> member.getName().equals(name)) // 스트림의 각 멤버 검사, 같은 경우에만 필터링이 됨
                .findAny(); // 하나 찾고 결과가 Optional로 반환됨
    }

    @Override
    /*  모든 Member 객체의 리스트를 반환한다. */
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 모든 값(모든 Member 객체)을 포함한 새로운 배열 생성
                                                // store에 있는 values는 멤버들이다
    }

    public void clearStore() {
        store.clear(); // store를 비움
    }
}
