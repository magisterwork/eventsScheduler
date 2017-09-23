package org.spree.vkscheduler.parameter;

import org.spree.core.parameter.Parameter;
import org.spree.core.parameter.ConfigStorage;

public class VkParameter implements Parameter<String>{

    private final VkParameterName name;
    private final ConfigStorage storage;

    public VkParameter(VkParameterName name, ConfigStorage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public String value() {
        return storage.getString(name);
    }

    @Override
    public void setValue(String value) {
        storage.save(name, value);
    }
}
