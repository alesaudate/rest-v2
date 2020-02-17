package app.car.cap07.interfaces.incoming.errorhandling;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class LocaleResolver extends AcceptHeaderLocaleResolver {

    private static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");

    private static final List<Locale> ACCEPTED_LOCALES = Arrays.asList(

        DEFAULT_LOCALE,
        new Locale("en")
    );

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        final String acceptLanguageHeader = request.getHeader("Accept-Language");
        if (StringUtils.isBlank(acceptLanguageHeader) || acceptLanguageHeader.trim().equals("*")) {
            return DEFAULT_LOCALE;
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(acceptLanguageHeader);
        Locale locale = Locale.lookup(list, ACCEPTED_LOCALES);
        return locale;
    }


}
