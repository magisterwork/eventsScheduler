package org.spree.vkscheduler.event;

import com.vk.api.sdk.objects.groups.GroupFull;
import org.spree.core.category.Category;
import org.spree.core.event.Event;

import java.util.*;

import static java.util.Collections.emptyList;

public class VkEvent implements Event {

    private static final long MILLIS_IN_SECOND = 1000L;
    public static final String VK_SYSTEM_ID = "VK";
    public static final int DEFAULT_DURATION = 3 * 60 * 60 * 1000;

    private GroupFull group;

    public VkEvent(GroupFull group) {
        this.group = group;
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
        calendar.setTimeInMillis(group.getStartDate() != null
                ? group.getStartDate() * MILLIS_IN_SECOND
                : 0);
        return calendar;
    }

    @Override
    public Calendar getFinishDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(
                group.getFinishDate() != null
                        ? group.getFinishDate() * MILLIS_IN_SECOND
                        : group.getStartDate() * MILLIS_IN_SECOND + DEFAULT_DURATION
        );
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
        return VK_SYSTEM_ID;
    }

    @Override
    public int getMembersCount() {
        return group.getMembersCount();
    }

    @Override
    public List<Category> getCategories() {
        return emptyList();
    }

    @Override
    public double getLatitude() {
        return group.getPlace() != null ? group.getPlace().getLatitude() : 0;
    }

    @Override
    public double getLongitude() {
        return group.getPlace() != null ? group.getPlace().getLatitude() : 0;
    }
}
