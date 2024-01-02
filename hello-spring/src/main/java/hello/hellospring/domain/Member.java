package hello.hellospring.domain;

/* 회원 클래스 */
public class Member {
    private Long id; // 시스템이 정하는 id, 데이터를 구분하기 위함
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
