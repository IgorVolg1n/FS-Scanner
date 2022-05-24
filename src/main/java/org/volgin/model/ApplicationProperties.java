package org.volgin.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application-settings")
public class ApplicationProperties {

    private int workerThreadsCount;

    public int getWorkerThreadsCount() {
        return workerThreadsCount;
    }

    public void setWorkerThreadsCount(int workerThreadsCount) {
        this.workerThreadsCount = workerThreadsCount;
    }
}
