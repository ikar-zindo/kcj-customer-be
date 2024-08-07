package com.kcj_customer_be.aspect.logging;

import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
public class AspectLoggingController {

   private static final Logger LOGGER = LoggerFactory.getLogger(AspectLoggingController.class);

   @Around("execution(* com.kcj_customer_be.controller..*(..)))")
   public Object mdcServiceController(@NotNull final ProceedingJoinPoint joinPoint) throws Throwable {
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

      LOGGER.info("IP : {}\n" +
                  "URL : {}\n" +
                  "HTTP_METHOD : {}\n" +
                  "CONTROLLER_METHOD : {}.{}",
            request.getRemoteAddr(),
            request.getRequestURL().toString(),
            request.getMethod(),
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName());

      return joinPoint.proceed();
   }
}
