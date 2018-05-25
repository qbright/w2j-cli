package top.thinkin.wjcli.script;

import top.thinkin.wjcli.util.FileUtil;
import top.thinkin.wjcli.util.ScanTool;
import top.thinkin.wjcli.util.StrUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptKits {
    public static final Map<String, String> SCRIPTS = new HashMap<String, String>();

    public static void init() throws Exception {
        String url = FileUtil.getAbsolutePath("", null);
        List<File> files = ScanTool.getFiles(url, "script", true, ".script");
        for (File file : files) {
            String fileName = file.getName();
            String key = fileName.substring(0, fileName.lastIndexOf("."));
            SCRIPTS.put(key, FileUtil.readToString(file));
        }
    }

    public static String LOOP_CLI(String cli, String stopPrefix) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cli", cli);
        map.put("stop_prefix", stopPrefix);
        return getScript("loop_cli", map);
    }

    public static <T> String getScript(String key, Map<String, T> map) {
        String temp = SCRIPTS.get(key);
        if (StrUtil.isEmpty(temp)) {
            return "";
        }
        return StrUtil.format(temp, map);
    }
}
