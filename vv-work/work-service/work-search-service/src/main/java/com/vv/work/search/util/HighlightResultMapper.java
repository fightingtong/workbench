package com.vv.work.search.util;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.ByteBufferReference;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/21 12:30
 **/
public class HighlightResultMapper extends DefaultResultMapper {
    /***
     * 映射转换，将非高亮数据替换成高亮数据
     * @param response
     * @param clazz
     * @param pageable
     * @param <T>
     * @return
     */
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        //获取及循环所有非高亮数据
        for (SearchHit hit : response.getHits()) {
            //获取当前单条非高亮数据
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            //获取及循环高亮数据
            for (Map.Entry<String, HighlightField> entry : hit.getHighlightFields().entrySet()) {
                // 将非高亮数据替换成高亮数据
                String key = entry.getKey();
                if (sourceMap.containsKey(key)) {
                    // 获取高亮碎片
                    Text[] fragments = entry.getValue().getFragments();
                    // 替换高亮
                    sourceMap.put(key, transTextArrayToString(fragments));
                }
            }
            // 更新hit数据
            hit.sourceRef(new ByteBufferReference(ByteBuffer.wrap(JSONObject.toJSONString(sourceMap).getBytes())));
        }
        return super.mapResults(response, clazz, pageable);
    }

    /***
     * 拼接数据碎片
     */
    private String transTextArrayToString(Text[] fragments) {
        if (null == fragments) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (Text fragment : fragments) {
            buffer.append(fragment.string());
        }
        return buffer.toString();
    }
}
