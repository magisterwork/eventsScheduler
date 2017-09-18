package org.spree.vkscheduler.exception;

import org.spree.core.exception.ExternalSystemException;

public class VkException extends ExternalSystemException {
    public VkException(String s) {
        super(s);
    }

    public VkException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public VkException(Throwable throwable) {
        super(throwable);
    }
}
