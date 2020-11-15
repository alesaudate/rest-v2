package app.car.car09.interfaces.incoming.errorhandling

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class DefaultErrorHandler {
    @Autowired
    private val messageSource: MessageSource? = null

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponses(ApiResponse(responseCode = "400", content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))]))
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException): ErrorResponse {
        val messages = ex.bindingResult.fieldErrors.map { getMessage(it) }
        return ErrorResponse(messages)
    }

    private fun getMessage(fieldError: FieldError): ErrorData {
        return ErrorData(messageSource!!.getMessage(fieldError, LocaleContextHolder.getLocale()))
    }
}