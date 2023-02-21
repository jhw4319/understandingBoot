package jhwspring.helloboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 어노테이션이 언제까지 살아있을것인가
@Retention(RetentionPolicy.RUNTIME)
// 어노테이션을 적용할 대사 지정
@Target(ElementType.TYPE)
public @interface MyComponent {
}
