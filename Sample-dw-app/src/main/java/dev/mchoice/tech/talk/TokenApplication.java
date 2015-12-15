package dev.mchoice.tech.talk;

import dev.mchoice.tech.talk.api.impl.ChatLoginControllerImpl;
import dev.mchoice.tech.talk.core.JoseAuthentication;
import dev.mchoice.tech.talk.resources.CrossDomainFilter;
import dev.mchoice.tech.talk.resources.StudentLinkBuilder;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class TokenApplication extends Application<TokenConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TokenApplication().run(args);
    }

    @Override
    public String getName() {
        return "Token";
    }

    @Override
    public void initialize(final Bootstrap<TokenConfiguration> bootstrap) {
    }

    @Override
    public void run(final TokenConfiguration configuration,
                    final Environment environment) {

        float expiryTime = 0.1f;
        int checkBeforeTime = 1;
        String subject = "Sub";
        String baseUrl = "http://127.0.0.1/9000";
        JoseAuthentication apacheCxfAuthentication = new JoseAuthentication(subject, expiryTime, checkBeforeTime);
        StudentLinkBuilder studentLinkBuilder = new StudentLinkBuilder(baseUrl);
        environment.jersey().register(new ChatLoginControllerImpl(apacheCxfAuthentication, studentLinkBuilder));
        environment.getApplicationContext().addFilter(CrossDomainFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
    }

}
