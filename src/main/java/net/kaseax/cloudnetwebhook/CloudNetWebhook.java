package net.kaseax.cloudnetwebhook;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.driver.module.driver.DriverModule;
import net.kaseax.cloudnetwebhook.listener.*;
import net.kaseax.cloudnetwebhook.utils.DiscordWebhook;

import java.io.IOException;

/**
 * Class created by Kaseax on 2022
 */
public class CloudNetWebhook extends DriverModule {

    private static CloudNetWebhook instance;

    @ModuleTask(event = ModuleLifeCycle.LOADED)
    public void init() throws IOException {
        instance = this;
        System.out.println("CloudNet-DiscordWebhook Module is successfully loaded");
    }

    @ModuleTask(event = ModuleLifeCycle.STARTED)
    public void initConfig() {
        this.getConfig().getString("WebhookURL", "127.0.0.1");
        this.getConfig().getString("WebhookUsername", "CloudNet");
        this.getConfig().getString("WebhookAvatarURL", "https://cloudnetservice.eu/img/logo.svg");

        this.getConfig().getBoolean("ServiceStartNotification", true);
        this.getConfig().getString("ServiceStartMessage", "%name% is starting...");

        this.getConfig().getBoolean("ServiceStopNotification", true);
        this.getConfig().getString("ServiceStopMessage", "%name% is stopping...");

        this.saveConfig();
    }

    @ModuleTask(event = ModuleLifeCycle.STARTED)
    public void registerListeners() {
        CloudNetDriver.getInstance().getEventManager().registerListener(new ServiceStartListener());
        CloudNetDriver.getInstance().getEventManager().registerListener(new ServiceStopListener());
    }

    @ModuleTask(event = ModuleLifeCycle.STOPPED)
    public void stopModule() {
    }

    @ModuleTask(event = ModuleLifeCycle.UNLOADED)
    public void unloadModule() {
    }

    public static CloudNetWebhook getInstance() {
        return instance;
    }
}
