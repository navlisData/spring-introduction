package de.doubleslash.spring.introduction.spring.configuration.control;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of Http Server.
 */
@Configuration
public class HttpServerConfig {

    /**
     * configure httpPort on tomcat
     *
     * @param httpPort httpPort
     * @return ServletWebServerFactory
     */
    @Bean
    public ServletWebServerFactory servletContainer(@Value("${server.http.port}") final int httpPort) {
        final Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);

        final TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
