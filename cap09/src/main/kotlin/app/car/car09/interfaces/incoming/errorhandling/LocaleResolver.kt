package app.car.car09.interfaces.incoming.errorhandling

import org.springframework.stereotype.Component
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.Locale
import java.util.Locale.LanguageRange
import javax.servlet.http.HttpServletRequest


@Component
class LocaleResolver : AcceptHeaderLocaleResolver() {

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val acceptLanguageHeader = request.getHeader("Accept-Language")
        if (acceptLanguageHeader.isBlank() || acceptLanguageHeader.trim() == "*") {
            return DEFAULT_LOCALE
        }
        val list = LanguageRange.parse(acceptLanguageHeader)
        val locale = Locale.lookup(list, ACCEPTED_LOCALES)
        return locale ?: DEFAULT_LOCALE
    }

    companion object {
        private val DEFAULT_LOCALE = Locale("pt", "BR")
        private val ACCEPTED_LOCALES = listOf(DEFAULT_LOCALE,Locale("en"))
    }
}
