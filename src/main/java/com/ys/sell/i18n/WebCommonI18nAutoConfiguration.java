package com.ys.sell.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

/**
 * @author HD
 * @date 2018/11/12 19:20
 */
@Configuration
public class WebCommonI18nAutoConfiguration
{
    @Value("${global.basename}")
    private String basename;

    @Bean
    public MessageSource webCommonMessageSource() {
        MessageSourceProperties properties = new MessageSourceProperties();
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        if (StringUtils.hasText(basename)) {
            messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
                    StringUtils.trimAllWhitespace(basename)));
        }
        if (properties.getEncoding() != null) {
            messageSource.setDefaultEncoding(properties.getEncoding().name());
        }
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        messageSource.setCacheMillis(60000);
        messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        return messageSource;
    }
}
