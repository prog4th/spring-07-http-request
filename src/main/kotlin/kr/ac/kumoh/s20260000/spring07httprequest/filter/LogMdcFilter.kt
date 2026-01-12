package kr.ac.kumoh.s20260000.spring07httprequest.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*

@Component
class LogMdcFilter : Filter {
    companion object {
        private val log = LoggerFactory.getLogger(LogMdcFilter::class.java)
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val traceId = UUID.randomUUID().toString().substring(0, 8)
        MDC.put("traceId", traceId)

        log.info(">>> [START] Request Received")

        try {
            chain.doFilter(request, response)
        } finally {
            log.info(">>> [END] Request Finished")

            // 반드시 clear 해야 함
            MDC.clear()
        }
    }
}