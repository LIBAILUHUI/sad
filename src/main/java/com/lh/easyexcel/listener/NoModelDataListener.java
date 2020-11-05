package com.lh.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description TODO
 * @date 2020/11/4
 */
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoModelDataListener.class);
    /**

     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收

     */

    private static final int BATCH_COUNT = 5;

    List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();


    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {

        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        /*String s = JSON.toJSONString(data);
        String[] split = s.split(",");
        for(int i=0;i<split.length;i++){
            LOGGER.info("解析到的数据"+i+"："+split[i]);
        }*/

        list.add(data);

        if (list.size() >= BATCH_COUNT) {

            saveData();

            list.clear();

        }

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        saveData();

        LOGGER.info("所有数据解析完成！");

    }


    /**

     * 加上存储数据库

     */

    private void saveData() {

        LOGGER.info("{}条数据，开始存储数据库！", list.size());

        LOGGER.info("存储数据库成功！");

    }


    /**

     * 这里会一行行的返回头

     *

     * @param headMap

     * @param context

     */

    @Override

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));

    }

}

