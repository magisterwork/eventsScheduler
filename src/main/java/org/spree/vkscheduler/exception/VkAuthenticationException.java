package org.spree.vkscheduler.exception;

public class VkAuthenticationException extends VkException {

    public VkAuthenticationException(String s) {
        super(s);
    }

    public VkAuthenticationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public VkAuthenticationException(Throwable throwable) {
        super(throwable);
    }
}
