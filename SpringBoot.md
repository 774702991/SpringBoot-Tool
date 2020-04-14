SpringBoot

## 零配置

## 内嵌容器的原理

## 启动流程的分析

## 核心注解

@SpringBootApplication：SpringBoot核心注解，用来表示这是一个SpringBoot程序，该注解中包括三个主要注解

1、@SpringBootConfiguration：Springboot中的配置类

​		其中包含@Configuration：Spring中的配置类

2、@EnableAutoConfiguration：开启自动配置功能

​		其中包含注解：

​		@AutoConfigurationPackage：自动配置包，即将主配置类（由SpringBootApplication注解标注的类）所在包以及其子包下所有组件扫描到Spring容器中。**==注意：是通过该注解下的@Import({Registrar.class})注解完成，调用Registrar下的registerBeanDefinitions()方法==**

​		@Import({AutoConfigurationImportSelector.class})：给容器中导入自动配置类（xxxAutoConfiguration）。==在SpringBoot启动的时候会在类路径下（spring-boot-autoconfigure-2.2.1.RELEASE.jar）的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，帮助我们完成工作。==





