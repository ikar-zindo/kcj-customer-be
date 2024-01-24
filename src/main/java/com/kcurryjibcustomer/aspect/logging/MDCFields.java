package com.kcurryjibcustomer.aspect.logging;

import org.slf4j.MDC;

public enum MDCFields {
   REQUEST_ID("REQUEST_ID"),
   DAO_STEP("DAO_STEP"),
   DAO_METHOD("DAO_METHOD"),
   DAO_TIME("DAO_TIME"),
   SERVICE_STEP("SERVICE_STEP"),
   SERVICE_METHOD("SERVICE_METHOD"),
   SERVICE_TIME("SERVICE_TIME");

   private final String mdcFieldName;

   MDCFields(final String mdcFieldName) {
      this.mdcFieldName = mdcFieldName;
   }

   public void putMdcField(final Object mdcFieldValue) {
      MDC.put(mdcFieldName, String.valueOf(mdcFieldValue));
   }

   public void putMdcFieldWithFieldName(final Object mdcFieldValue) {
      putMdcField("{" + mdcFieldName + "=" + mdcFieldValue + "}");
   }

   public void removeMdcField() {
      MDC.remove(mdcFieldName);
   }
}