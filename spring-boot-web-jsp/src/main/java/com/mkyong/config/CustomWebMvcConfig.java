package com.mkyong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class CustomWebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		final InternalResourceViewResolver coderViewResolver = new InternalResourceViewResolver();
        coderViewResolver.setOrder(1);
        coderViewResolver.setPrefix("/WEB-INF/jsp/");
        coderViewResolver.setSuffix(".jsp");
        registry.viewResolver(coderViewResolver);
	}

	
}
