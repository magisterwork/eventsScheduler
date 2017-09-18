package org.spree.vkscheduler.exception;

public class VkProcedureException extends VkException {

    public VkProcedureException(String s) {
        super(s);
    }

    public VkProcedureException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public VkProcedureException(Throwable throwable) {
        super(throwable);
    }
}
