package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service // 회원 서비스 스프링 빈 등록
public class MemberService {
    /* 회원 서비스를 만드려면 회원 리포지토리가 있어야겠지? */
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입 서비스
     * 비즈니스 로직: 같은 이름을 가진 회원이 있으면 안됨! */
    public Long join(Member member) {
        /* ifPresent: null이 아니면 동작, Optional이라 가능 한 것
         * Optional 통한 여러 메소드가 있음 -> 안에 멤버 객체 존재
         * orElseGet(): 값이 있으면 꺼내고, 없으면 어떤 메소드 실행, 자주 사용함 */
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 검증 통과하면 멤버 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /* 회원 한 명 찾기 */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
