package som.make.mock.server.extend;

import java.util.UUID;

public class UuidUtils {

    public static String simpleUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
