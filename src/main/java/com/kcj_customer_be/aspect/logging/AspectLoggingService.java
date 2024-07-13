package com.kcj_customer_be.aspect.logging;

import com.kcj_customer_be.exception.list.LogException;
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

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class AspectLoggingService {

   private static final Logger LOGGER = LoggerFactory.getLogger(AspectLoggingService.class);

   @Around("execution(* com.kcj_customer_be.service..*(..)))")
   public Object mdcServiceController(@NotNull final ProceedingJoinPoint joinPoint) throws Throwable {
      String queryMethod = joinPoint.getSignature().getName();
      Object[] args = joinPoint.getArgs();

      logBeforeServiceQuery(queryMethod, args);
      long startTime = System.currentTimeMillis();

      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

      try {
         Object result = joinPoint.proceed();
         logAfterServiceQuery(queryMethod, args, result, startTime);

         return result;

      } catch (Exception ex) {
         logAndGetErrorMessage(queryMethod, args, ex, startTime);
         throw ex;
      }
   }

   private void logBeforeServiceQuery(final String queryMethod, final Object[] args) {
      MDCFields.SERVICE_STEP.putMdcField("SERVICE_IN");
      MDCFields.SERVICE_METHOD.putMdcFieldWithFieldName(queryMethod);

      String argsAsString = Arrays.toString(args);

      LOGGER.info("args={};", argsAsString);

      MDCFields.SERVICE_METHOD.removeMdcField();
      MDCFields.SERVICE_STEP.removeMdcField();
   }

   private void logAfterServiceQuery(final String queryMethod, final Object[] args, final Object result, final long startTime) {
      long callTime = System.currentTimeMillis() - startTime;

      String resultInfo = LogUtils.getDaoResultLogInfo(LOGGER, result);

      MDCFields.SERVICE_STEP.putMdcField("SERVICE_OUT");
      MDCFields.SERVICE_METHOD.putMdcFieldWithFieldName(queryMethod);
      MDCFields.SERVICE_TIME.putMdcFieldWithFieldName(callTime);

      String argsAsString = Arrays.toString(args);

      LOGGER.info(
              "args={}; RESULT: [{}]",
              argsAsString,
              resultInfo
      );

      MDCFields.SERVICE_TIME.removeMdcField();
      MDCFields.SERVICE_METHOD.removeMdcField();
      MDCFields.SERVICE_STEP.removeMdcField();
   }

   private void logAndGetErrorMessage(final String queryMethod, final Object[] args, final Exception ex, final long startTime) {
      long callTime = System.currentTimeMillis() - startTime;

      String errorMsg = String.format(
              "args=%s;",
              Arrays.toString(args)
      );

      MDCFields.DAO_STEP.putMdcField("DAO_ERROR");
      MDCFields.DAO_METHOD.putMdcFieldWithFieldName(queryMethod);
      MDCFields.DAO_TIME.putMdcFieldWithFieldName(callTime);

      LOGGER.error(errorMsg, ex);

      MDCFields.DAO_TIME.removeMdcField();
      MDCFields.DAO_METHOD.removeMdcField();
      MDCFields.DAO_STEP.removeMdcField();

      throw new LogException(ex.getMessage(), ex);
   }
}
