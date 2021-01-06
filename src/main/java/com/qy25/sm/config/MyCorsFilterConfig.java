package com.qy25.sm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * corsFilter配置   处理跨域问题
 */
@Configuration
public class MyCorsFilterConfig {


    /**
     * 将corsFilter放入springIOC容器  便于调用
     * @return
     */
    @Bean
    public CorsFilter getCors(){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //对于相应的请求头  方法  进行过滤
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        //设置是否允许携带请求cookie
        corsConfiguration.setAllowCredentials(false);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
