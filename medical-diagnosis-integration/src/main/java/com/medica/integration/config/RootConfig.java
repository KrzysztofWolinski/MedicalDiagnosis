package com.medica.integration.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.auth.TokenGeneratorService;
import com.medica.integration.service.auth.impl.AuthServiceImpl;
import com.medica.integration.service.auth.impl.TokenGeneratorServiceImpl;
import com.medica.integration.service.diagnosis.domain.ChoiceFieldValue;
import com.medica.integration.service.diagnosis.domain.SimpleFieldValue;
import com.medica.integration.service.history.HistoryService;
import com.medica.integration.service.history.impl.HistoryServiceImpl;
import com.medica.integration.service.user.UserService;
import com.medica.integration.service.user.impl.UserServiceImpl;

@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = {"com.medica.integration.controller", "com.medica.integration.service"})
@Import({JpaConfig.class, DiagnosisCoreConfig.class})
@PropertySources({@PropertySource("classpath:properties/medical-diagnosis.properties")})
class RootConfig extends WebMvcConfigurerAdapter {

	@Bean
	public AuthService authService() {
		return new AuthServiceImpl();
	}
	
	@Bean
	public TokenGeneratorService tokenGeneratorService() {
		return new TokenGeneratorServiceImpl();
	}
	
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public HistoryService historyService() {
		return new HistoryServiceImpl();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);

		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setObjectMapper(this.objectMapper());

		converters.add(stringConverter);
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new ResourceHttpMessageConverter());
		converters.add(jsonConverter);
	}
	
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		final ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
		mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		mapper.registerSubtypes(
				new NamedType(SimpleFieldValue.class, "SimpleFieldValue"),
				new NamedType(ChoiceFieldValue.class, "ChoiceFieldValue"));
		
		return mapper;
	}
	
}