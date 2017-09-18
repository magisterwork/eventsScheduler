package org.spree.vkscheduler.parameter;

import org.spree.core.parameter.Parameter;
import org.spree.core.parameter.ParameterStorage;

public class VkParameter implements Parameter<String>{

    private final VkParameterName name;
    private final ParameterStorage storage;

    private String value;

    public VkParameter(VkParameterName name, ParameterStorage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public String getValue() {
        return storage.getValue(name.name());
    }

    @Override
    public void setValue(String value) {
        storage.updateValue(name.name(), value);
    }
}
