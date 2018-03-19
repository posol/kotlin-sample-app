package ru.posol.sample.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * class for custom error response if User does not have UNAUTHORIZED
 */
class Http401AuthenticationEntryPoint() : AuthenticationEntryPoint {

    val msg = "Access denied"

    override fun commence(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: AuthenticationException?) {
        p1?.status = HttpServletResponse.SC_UNAUTHORIZED
        p1?.contentType = "application/json"
        p1?.outputStream?.println("{ \"message\": \"" + msg + "\" }") // FIXME заменить на обьект
    }
}