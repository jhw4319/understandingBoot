package jhwspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {
}
@Retention(RetentionPolicy.RUNTIME)
// UnitTest 어노테이션을 메타어노테이션으로 사용할수있도록 설정
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest {
}

public class HelloServiceTest {

    @FastUnitTest
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        String sayHello = simpleHelloService.sayHello("Spring!!");

        Assertions.assertThat(sayHello).isEqualTo("HelloSpring!!");
    }

    @UnitTest
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Test Decorator");

        Assertions.assertThat(ret).isEqualTo("*Test Decorator*");
    }

}
