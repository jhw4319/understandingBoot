package jhwspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class UnderstandingBootApplication {

    public static void main(String[] args) {
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        // 빈 등록
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        // 컨테이너 초기화(빈 오프젝트 생성)
        applicationContext.refresh();

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext) {
            }).addMapping("/*"); // request요청이 들어오면 익명클래스로 만든 위의 서블릿에 매핑한다.
        });
        // 톰캣 서블릿 컨테이너 동작
        webServer.start();
    }

}
