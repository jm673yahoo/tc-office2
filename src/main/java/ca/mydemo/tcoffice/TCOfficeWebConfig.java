package ca.mydemo.tcoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//@EnableWebMvc
@Configuration
@ComponentScan({"ca.mydemo.tcoffice"})
public class TCOfficeWebConfig extends WebMvcConfigurerAdapter {

    @Value("#{systemProperties['spring.profiles.active']}")
    private String springProfilesActive;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ContentNegotiatingViewResolver contentNegotiationgViewResolver() {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(contentNegotiationManager());


        List<ViewResolver> viewResolvers = new ArrayList<>();
        viewResolvers.add(internalResourceViewResolver());
        viewResolver.setViewResolvers(viewResolvers);

        List<View> defaultViews = new ArrayList<>();
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setExtractValueFromSingleKeyModel(true);
        mappingJackson2JsonView.setModelKey("result");
        defaultViews.add(mappingJackson2JsonView);

        viewResolver.setDefaultViews(defaultViews);
        return viewResolver;
    }

    @Bean
    public ContentNegotiationManager contentNegotiationManager() {
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("html", MediaType.TEXT_HTML);
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        ContentNegotiationStrategy contentNegotiationStrategy = new PathExtensionContentNegotiationStrategy(mediaTypes);

        return new ContentNegotiationManager(contentNegotiationStrategy);
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    /*
     * Configure MessageSource to lookup any validation/error message in internationalized
     * property files
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/tcoffice");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

        if (!Arrays.asList(applicationContext.getEnvironment().getActiveProfiles()).contains("live")) {
            messageSource.setCacheSeconds(0);
        }

        return messageSource;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("favicon.ico").addResourceLocations("/resources/images/favicon.ico")
                .setCachePeriod(31556926);
        registry.addResourceHandler("css/**").addResourceLocations("/resources/css/").setCachePeriod(31556926);
        registry.addResourceHandler("images/**").addResourceLocations("/resources/images/").setCachePeriod(31556926);
        registry.addResourceHandler("js/**").addResourceLocations("/resources/js/").setCachePeriod(31556926);
        registry.addResourceHandler("fonts/**").addResourceLocations("/resources/fonts/").setCachePeriod(31556926);

        registry.addResourceHandler("font-awesome/**").addResourceLocations("/resources/font-awesome/")
                .setCachePeriod(31556926);
        registry.addResourceHandler("fonts/**").addResourceLocations("/resources/fonts/").setCachePeriod(31556926);


    }

    /*
     * Optional. It's only required when handling '.'
     * in @PathVariables which otherwise ignore everything after last '.' in
     * @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("siteLang");
        registry.addInterceptor(interceptor);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.CANADA);
        resolver.setCookieName("tcoffice");
        resolver.setCookieMaxAge(4800);
        return resolver;
    }


}

