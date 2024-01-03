package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* 회원을 위한 리포지토리 인터페이스 */
@Repository
public interface MemberRepository {
    /* 리포지토리의 네 가지 기능 */
    Member save(Member member); /* 회원이 저장소에 저장됨 */

    Optional<Member> findById(Long id); /* Optional 사용 이유: NULL 반환 대신 한번 감싼 것 */

    Optional<Member> findByName(String name);

    List<Member> findAll(); /* 저장된 모든 회원 리스트 반환 */

}
