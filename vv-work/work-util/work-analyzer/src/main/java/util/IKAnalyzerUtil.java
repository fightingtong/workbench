package util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/20 13:54
 **/
public class IKAnalyzerUtil {

    /**
     * 分词
     * @param msg
     * @return
     * @throws IOException
     */
    public static List<String> cut(String msg) throws IOException {
        StringReader sr = new StringReader(msg);
        IKSegmenter ik = new IKSegmenter(sr, true);
        Lexeme lex = null;
        List<String> list = new ArrayList<>();
        while ((lex = ik.next()) != null) {
            list.add(lex.getLexemeText());
        }
        return list;
    }
}
