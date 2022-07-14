package net.kaseax.cloudnetwebhook.listener;

import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.service.CloudServiceStopEvent;
import net.kaseax.cloudnetwebhook.CloudNetWebhook;
import net.kaseax.cloudnetwebhook.utils.DiscordWebhook;

import java.io.IOException;

/**
 * Class created by Kaseax on 2022
 */
public class ServiceStopListener {

    @EventListener
    public void handleServiceStop(CloudServiceStopEvent event) {
        if (CloudNetWebhook.getInstance().getConfig().getBoolean("ServiceStopNotification")) {
            DiscordWebhook discordWebhook = new DiscordWebhook(CloudNetWebhook.getInstance().getConfig().getString("WebhookURL"));
            discordWebhook.setContent(CloudNetWebhook.getInstance().getConfig().getString("ServiceStopMessage").replaceAll("%name%", event.getServiceInfo().getServiceId().getName()));
            try {
                discordWebhook.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
