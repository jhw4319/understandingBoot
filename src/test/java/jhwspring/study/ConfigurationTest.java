package jhwspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    @Test
    void configuration() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyConfig.class);
        context.refresh();

        MyConfig.Bean1 bean1 = context.getBean(MyConfig.Bean1.class);
        MyConfig.Bean2 bean2 = context.getBean(MyConfig.Bean2.class);
        // 자바소스코드상, bean1과bean2의 common은 다르다.
        // 하지만, @Configuration(proxyBeanMethods = true)이 붙어있는 경우, 프록식를 만들기때문에 하나의 인스턴스만 생성. bean1과bean2은 같게된다.
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);

    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }

        static class Bean1 {
            private final Common common;


            Bean1(Common common) {
                this.common = common;
            }
        }

        static class Bean2 {
            private final Common common;


            Bean2(Common common) {
                this.common = common;
            }
        }

        static class Common {

        }
    }
}
