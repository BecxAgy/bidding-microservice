package com.becxagy.book.api.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.becxagy.book.api.adapters.out.queue.QueuePort;
import com.becxagy.book.api.adapters.out.storage.StoragePort;
import com.becxagy.book.api.core.repository.bidding.BiddingRepository;

import static org.mockito.Mockito.mock;

@TestConfiguration
@Profile("test")
public class TestConfig {

    @Bean
    @Primary
    public StoragePort mockStoragePort() {
        return mock(StoragePort.class);
    }

    @Bean
    @Primary
    public QueuePort mockQueuePort() {
        return mock(QueuePort.class);
    }

    @Bean
    @Primary
    public BiddingRepository mockBiddingRepository() {
        return mock(BiddingRepository.class);
    }
}
