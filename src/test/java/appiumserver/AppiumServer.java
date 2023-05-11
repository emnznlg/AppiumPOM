package appiumserver;

import com.beust.ah.A;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServer {

    static AppiumDriverLocalService server;
    static String mainJSpath = "C:/Users/emin/node_modules/appium/build/lib/main.js";

    public static void start() {
        if (server == null) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withAppiumJS(new File(mainJSpath)).
                    usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe")).
                    usingPort(4723).
                    withIPAddress("127.0.0.1");

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
