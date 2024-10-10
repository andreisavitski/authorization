package eu.senla.security.constant;

public class AppConstants {

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String TOKEN_KEY = "${token.signing.key}";

    public static final String ID = "id";

    public static final String LOGIN = "login";

    public static final String ROLE = "role";

    public static final String CLIENT_TABLE_NAME = "client";

    public static final String CLIENT_COLUMN_NAME_ID = "id";

    public static final String CLIENT_COLUMN_NAME_LOGIN = "login";

    public static final String CLIENT_COLUMN_NAME_PASSWORD = "password";

    public static final String CLIENT_COLUMN_NAME_ROLE = "role";

    public static final Long TOKEN_VALIDITY_DURATION_MILLIS = 100000L * 60L * 24L;

}
