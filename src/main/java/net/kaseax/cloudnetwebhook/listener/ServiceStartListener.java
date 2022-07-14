package net.kaseax.cloudnetwebhook.listener;

import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStartEvent;
import net.kaseax.cloudnetwebhook.CloudNetWebhook;
import net.kaseax.cloudnetwebhook.utils.DiscordWebhook;

import java.io.IOException;

/**
 * Class created by Kaseax on 2022
 */
public class ServiceStartListener {

    @EventListener
    public void handleServiceStart(CloudServiceStartEvent event) {
        if (CloudNetWebhook.getInstance().getConfig().getBoolean("ServiceStartNotification")) {
            DiscordWebhook discordWebhook = new DiscordWebhook(CloudNetWebhook.getInstance().getConfig().getString("WebhookURL"));
            discordWebhook.setContent(CloudNetWebhook.getInstance().getConfig().getString("ServiceStartMessage").replaceAll("%name%", event.getServiceInfo().getServiceId().getName()));
            try {
                discordWebhook.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
