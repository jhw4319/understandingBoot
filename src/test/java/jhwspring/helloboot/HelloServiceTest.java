package jhwspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        String sayHello = simpleHelloService.sayHello("Spring!!");

        Assertions.assertThat(sayHello).isEqualTo("HelloSpring!!");
    }

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Test Decorator");

        Assertions.assertThat(ret).isEqualTo("*Test Decorator*");
    }

}
