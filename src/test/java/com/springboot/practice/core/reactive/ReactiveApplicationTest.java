package com.springboot.practice.core.reactive;

import com.springboot.practice.core.service.VaccineProvider;
import com.springboot.practice.core.service.VaccineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveApplicationTest {
    @Autowired
    private VaccineProvider vaccineProvider;
    @Autowired
    private VaccineService vaccineService;

    @Test
    public void testMono() {
        Mono<String> mono = Mono.just("Windows");
        mono.log().map(data -> data.toUpperCase()).subscribe(data -> System.out.println(data));

    }


    @Test
    public void testFlux() {
        Flux<String> flux = Flux.just("Windows", "Mac", "Linux");
        flux.log().map(data -> data.toUpperCase()).subscribe(data -> System.out.println(data));

    }

    @Test
    public void testFluxWithConsumer() {
        Flux<String> flux = Flux.just("Windows", "Mac", "Linux");
        flux.log().map(data -> data.toUpperCase()).subscribe(new OrderConsumer());

    }

    @Test
    public void testFluxWithSubscriber() throws InterruptedException {
        Flux<String> flux = Flux.just("Windows", "Mac", "Linux").delayElements(Duration.ofSeconds(2));
        flux.log().map(data -> data.toUpperCase()).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("In Complete");
            }
        });

        Thread.sleep(8000);

    }


    @Test
    public void testFluxWithSubscriberBatching() {
        Flux<String> flux = Flux.just("Windows", "Mac", "Linux", "Fedora");
        flux.log().map(data -> data.toUpperCase()).subscribe(new Subscriber<String>() {
            private Subscription subscription;
            private long count = 0;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(2);
            }

            @Override
            public void onNext(String s) {
                count++;
                if (count >= 2) {
                    count = 0;
                    subscription.request(2);
                }
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("In Complete");
            }
        });
    }

    @Test
    public void testVaccineProvider() {
        vaccineService.getVaccines().subscribe(new VaccineConsumer());
        vaccineProvider.provideVaccines().subscribe(new VaccineConsumer());
    }

}

