package com.kcj_customer_be.aspect.logging;

import org.slf4j.Logger;

import java.util.List;


public class LogUtils {

   private LogUtils() {
   }

   public static String getDaoResultLogInfo(final Logger log, final Object result) {
      StringBuilder resultInfo = new StringBuilder();

      if (result instanceof List) {
         resultInfo.append("RESULT_SIZE=").append(((List<?>) result).size());
      }

      if (log.isDebugEnabled() || !(result instanceof List)) {
         if (resultInfo.length() > 0) {
            resultInfo.append(" ");
         }
         resultInfo.append(result);
      }

      return resultInfo.toString();
   }
}