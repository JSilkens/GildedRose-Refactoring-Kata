package com.gildedrose.adapter.config;

import com.gildedrose.adapter.controller.TestFixtures;
import com.gildedrose.adapter.event.SpringEventPublisher;
import com.gildedrose.usecase.UpdateInventoryUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherConfig {

    @Bean
    public SpringEventPublisher springEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new SpringEventPublisher(applicationEventPublisher);
    }

    @Bean
    public UpdateInventoryUseCase updateInventoryUseCase(SpringEventPublisher springEventPublisher) {
        return new UpdateInventoryUseCase(TestFixtures.itemsTestFixtures(), springEventPublisher);
    }

}
