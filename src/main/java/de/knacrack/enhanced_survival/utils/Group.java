package de.knacrack.enhanced_survival.utils;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

// TODO: Give permissions.
public enum Group {

    PLAYER(Character.toString('\uE006') + " §7", "", "999", null, Collections.emptyList()), // We don't have to set permissions for this group. If everyone should access certain actions then we don't even have to set permissions in the first place.
    VIP(Character.toString('\uE005') + " §6", "", "998", PLAYER, Lists.newArrayList()),

    TWITCH(Character.toString('\uE004') + " " + ChatColor.of(new Color(128, 0, 255)), "", "881", VIP, Lists.newArrayList()),
    YT(Character.toString('\uE003') + " §c", "", "800", TWITCH, Lists.newArrayList()),

    CONTENT(Character.toString('\uE002') + " §a", "", "002", YT, Lists.newArrayList()),
    STAFF(Character.toString('\uE001') + " §9", "", "001", CONTENT, Lists.newArrayList()),
    ADMIN(Character.toString('\uE000') + " §4", "", "000", STAFF, Lists.newArrayList());

    private final String prefix, suffix;
    private final List<String> permissions;
    private final Group parentGroup;
    private final String priority;

    Group(String prefix, String suffix, String priority, Group parentGroup, List<String> permissions) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.permissions = permissions;
        this.parentGroup = parentGroup;
        this.priority = priority;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public List<String> getPermissions() {
        List<String> permissions = this.permissions;

        if (parentGroup != null) {
            permissions.addAll(parentGroup.getPermissions());
        }

        return permissions;
    }

    public Group getParentGroup() {
        return this.parentGroup;
    }

    public String getPriority() {
        return this.priority;
    }

    public String getTablistGroupName() {
        return this.priority + name().toLowerCase(Locale.ROOT);
    }

    public boolean hasPermission(String permission) {
        return getPermissions().contains(permission);
    }
}