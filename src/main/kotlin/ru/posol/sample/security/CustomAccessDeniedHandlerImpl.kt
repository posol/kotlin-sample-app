package ru.posol.sample.security

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.access.AccessDeniedHandlerImpl

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

/**
 * class for custom error response if User does not have access
 */
class CustomAccessDeniedHandlerImpl : AccessDeniedHandler {
    val errorPage: String? = null

    @Throws(IOException::class, ServletException::class)
    override fun handle(request: HttpServletRequest, response: HttpServletResponse,
                        accessDeniedException: AccessDeniedException) {
        if (!response.isCommitted) {
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.outputStream.println("{ \"message\": \"$MESSAGE\" }")
        }

    }

    companion object {

        protected val logger = LogFactory.getLog(AccessDeniedHandlerImpl::class.java)
        private val MESSAGE = "User does not have access"
    }


}