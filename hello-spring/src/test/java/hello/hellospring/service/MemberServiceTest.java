package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /* 테스트 케이스 같은 경우는 영어권 사람과 일하는 것이 아니면 한글로 적는 경우도 많다
     * 빌드될 때 테스트 코드는 실제 코드에 포함되지 않음! */

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    /* 각 테스트 전에 실행
    * 의존성 주입 */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    /* 각 테스트 후에 실행 */
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given: 이 데이터를 기반으로 검증한다
        Member member = new Member();
        member.setName("hello");

        //when: 이걸 검증한다
        Long saveId = memberService.join(member);

        //then: 이제 검증한다
        Member findMember = memberService.findOne(saveId).get(); // 레포에서 꺼냄
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2); // 예외 터치를 안 하면 오류임
//            fail(); // 테스트 예외 발생시키기
//        } catch (IllegalStateException e) { // 예외 터치를 함 -> 정상적으로 성공
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 예외가 발생해야 함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}