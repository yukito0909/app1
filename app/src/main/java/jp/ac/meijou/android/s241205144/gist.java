package jp.ac.meijou.android.s241205144;

import java.util.Map;

/**
 * GistのJSONレスポンス
 */
public class gist {
    public Map<String, GistFile> files;

    public static class GistFile {
        public String content;
    }
}