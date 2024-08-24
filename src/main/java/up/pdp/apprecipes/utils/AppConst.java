package up.pdp.apprecipes.utils;

import java.io.File;
import java.nio.file.Path;

public interface AppConst {
    String API_V1 = "/api/v1";
    Path BASE_PATH = Path.of(System.getProperty("user.home")+ File.separator + "recipe-app"+ File.separator + "attachments" + File.separator);
}
