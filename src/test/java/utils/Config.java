package utils;

public class Config {
    public static final String BASE_URL = "https://www.linkedin.com";

    public static String username() { return require("LI_USER"); }
    public static String password() { return require("LI_PASS"); }

    private static String require(String key) {
        String v = System.getenv(key);
        if (v == null || v.isBlank())
            throw new IllegalStateException("Set environment variable " + key);
        return v;
    }
}