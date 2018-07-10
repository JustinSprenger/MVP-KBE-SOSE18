package de.htwBerlin.ai.kbe.config;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new DependencyBinder());
        packages(true, "de.htwBerlin.ai.kbe.api");
    }
}
