package baseclient;

import baseclient.gg.dhyeybg.baseclient.BaseClient;

public class Main {

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        initialized = true;

        BaseClient.instance = new BaseClient();
        BaseClient.instance.init();
    }
}