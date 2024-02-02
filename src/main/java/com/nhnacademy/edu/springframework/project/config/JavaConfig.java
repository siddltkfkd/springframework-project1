package com.nhnacademy.edu.springframework.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:property/setting.property", encoding = "utf-8")
@ComponentScan("com.nhnacademy.edu.springframework.project")
public class JavaConfig {
}
