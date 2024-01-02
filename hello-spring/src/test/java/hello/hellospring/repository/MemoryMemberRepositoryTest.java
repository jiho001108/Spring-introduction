package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    /* 각 테스트가 끝날 때마다 실행되는 메소드
    * 저장소를 계속 사용하므로 각 테스트마다 클리어 해줘야 함
    * 그래야 각 테스트를 제대로 확인 가능 */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test /* main 메소드 사용과 비슷하다 */
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        /* Optional 값을 꺼낼 때는 get()으로 꺼낼 수 있음
         * -> 좋은 방법은 아니나, 테스트 코드에서 상관없음 */
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member)); // 그냥 프린트하기
        assertEquals(member, result); // 검증 방법1
        assertThat(result).isEqualTo(member); // 검증 방법2: 제일 많이 쓰는 검증 방법
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
