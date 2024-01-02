package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 웹 어플리케이션에서 /hello로 들어오면 이 메소드 실행
    @GetMapping("hello") // @GetMapping: http GET 메소드
    public String hello(Model model) {
        // 키: data, 값: hello!!
        model.addAttribute("data", "hello!!");

        /* \resources\templates\hello.html을 랜더링 하라는 의미
         Spring boot는 기본적으로 return이 \resources\templates\* 에서 찾음 */
        return "hello";
    }

    /* @GetMapping("hello-mvc"): 웹 어플리케이션에서 /hello-mvc로 들어오면 이 메소드 실행 */
    @GetMapping("hello-mvc")
    // 외부에서 파라미터를 받음, Model을 담으면 View에서 렌더링 할 때 사용
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; // hello-template 렌더링 해라라는 뜻
    }

    @GetMapping("hello-string")
    /* @ResponseBody: 리턴이 객체가 아님 -> http의 바디의 데이터를 직접 넣겠다는 의미,
       템플릿 엔진과의 차이: API 방식은 문자 그대로 내려간다는 것! */
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        /* http 바디에 이 데이터를 넣음, name = spring이면, 데이터는 hello spring
         * 이렇게 문자만 넘기는 방식은 MVC 방식과 비슷해서 크게 의미는 없다
         * 실제 데이터를 내놓을 때 API 방식을 사용 */
        return "hello " + name;
    }

    @GetMapping("hello-api") //1
    /* @ResponseBody: 객체가 리턴되면
            Spring에서는 Jason 방식으로 데이터를 만들어서 HTTP 응답에 반환 */
    @ResponseBody //2
            //3
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // hello라는 인스턴스(객체)리턴, Jason 방식으로 데이터 만듦
    } //6

    /* static class로 만드는 이유: class HelloController에서 사용하기 위해 */
    static class Hello { //4

        /*  private라 외부에서 바로 못꺼냄
        -> getter/setter 메소드를 통해 접근, 이 메소드는 public
        -> JavaBean 표준 방식 or 프로퍼티 방식(접근 방식) */
        private String name; //4

        //5
        public String getName() { // getter
            return name;
        }

        //5
        public void setName(String name) { // setter
            this.name = name;
        }
    }
}
