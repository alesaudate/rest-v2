package app.car.cap07.interfaces.incoming.errorhandling;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class LocaleResolver extends AcceptHeaderLocaleResolver {

    private static final Locale DEFAULT_LOCALE = Locale.of("pt", "BR");

    private static final List<Locale> ACCEPTED_LOCALES = List.of(DEFAULT_LOCALE, Locale.of("en"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        final String acceptLanguageHeader = request.getHeader("Accept-Language");
        if (StringUtils.isBlank(acceptLanguageHeader) || acceptLanguageHeader.trim().equals("*")) {
            return DEFAULT_LOCALE;
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(acceptLanguageHeader);
        Locale locale = Locale.lookup(list, ACCEPTED_LOCALES);
        return Optional.ofNullable(locale).orElse(DEFAULT_LOCALE);
    }


}
