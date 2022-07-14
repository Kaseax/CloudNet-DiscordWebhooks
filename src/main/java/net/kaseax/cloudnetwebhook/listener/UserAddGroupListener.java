package net.kaseax.cloudnetwebhook.listener;

import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.permission.PermissionAddGroupEvent;
import de.dytanic.cloudnet.driver.event.events.permission.PermissionAddUserEvent;

/**
 * Class created by Kaseax on 2022
 */
public class UserAddGroupListener {

    @EventListener
    public void handleAddGroup(PermissionAddUserEvent event) {

    }
}
