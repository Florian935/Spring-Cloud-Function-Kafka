package com.florian935.cloud.function.producer.configuration;

import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartitionSelectorStrategyImpl implements PartitionSelectorStrategy {

    /**
     * Retrieve the key extracted (cf application.yml) and return in which partition we
     * want to write
     *
     * @param key            the extracted key
     * @param partitionCount the number of partition
     * @return the partition count in which to produce
     */
    @Override
    public int selectPartition(Object key, int partitionCount) {

        // The key selected by the expression in application.yml file
        System.out.println("====== Key = " + key);

        if (key instanceof String && key.equals("create")) {
            return 1;
        } else {
            return 2;
        }
    }
}
