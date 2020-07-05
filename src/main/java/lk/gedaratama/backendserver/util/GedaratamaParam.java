package lk.gedaratama.backendserver.util;

import java.util.UUID;

/**
 *  @author Sashini Tharuka on 5/31/2020.
 */
public class GedaratamaParam {
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String JWT_TOKEN_TYPE_ACCESS = "ACCESS";
    public static final String JWT_TOKEN_TYPE_REFRESH = "REFRESH";
    public static final String AUTH_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer";
    public static final String USER_NORMAL = "USER";
    public static final String USER_ADMIN = "ADMIN";
    public static final String USER_SHOP = "SHOP";

    public static final String LOGIN_URL = "/login";

    public static String getUuid(){
        return UUID.randomUUID().toString();
    }
}
