package appiumserver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumServer {

    static AppiumDriverLocalService server;
    static String mainJSpath = "C:/Users/emin/node_modules/appium/build/lib/main.js";

    public static void start() {
        if (server == null) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withAppiumJS(new File(mainJSpath))
                    .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"));

            server = AppiumDriverLocalService.buildService(builder);
            server.start();
        }
    }

    public static void stop() {
        if (server.isRunning()) {
            server.stop();
        }
    }

}
