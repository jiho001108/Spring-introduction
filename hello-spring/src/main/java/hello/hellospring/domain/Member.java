package hello.hellospring.domain;

import jakarta.persistence.*;

/* 회원 클래스 */
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) /* DB가 ID 자동 생성: 아이덴티티 전략 */
    private Long id; // 시스템이 정하는 id, 데이터를 구분하기 위함

    @Column(name = "username") /* DB의 column명과 매핑 */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
