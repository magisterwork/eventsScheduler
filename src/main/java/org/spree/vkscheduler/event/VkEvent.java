package org.spree.vkscheduler.event;

import com.vk.api.sdk.objects.groups.GroupFull;
import org.spree.core.event.Event;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class VkEvent implements Event {

    private static final int MILLIS_IN_SECOND = 1000;
    public static final String VK = "VK";

    private GroupFull group;

    public VkEvent(GroupFull group) {
        this.group = group;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getName() {
        return group.getName();
    }

    @Override
    public String getDescription() {
        return group.getDescription();
    }

    @Override
    public Calendar getStartDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(group.getStartDate() * MILLIS_IN_SECOND);
        return calendar;
    }

    @Override
    public Calendar getFinishDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(group.getFinishDate() * MILLIS_IN_SECOND);
        return calendar;
    }

    @Override
    public String getImageUrl() {
        return group.getPhoto200();
    }

    @Override
    public String getExtId() {
        return group.getId().toString();
    }

    @Override
    public String getSystemId() {
        return null;
    }
}
