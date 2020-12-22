package com.devil.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// Spring WebMVC 프레임워크에서 ContextLoaderListener 가 사용할 Java Config 이다.
// 웹 컴포넌트가 공유할 객체를 선언한다.
// 예) DAO, Service 등
@ComponentScan(
    value="com.devil",
    excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "com.devil.web.*")
    })

@EnableAsync
@EnableScheduling
public class RootConfig {

}
