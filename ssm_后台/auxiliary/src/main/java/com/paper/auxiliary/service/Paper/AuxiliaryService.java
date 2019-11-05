package com.paper.auxiliary.service.Paper;

import java.io.IOException;

public interface AuxiliaryService {
    // 相似度分析
    String Similarity(String text1, String text2) throws IOException, InterruptedException;
    // 错别字识别
    String Correction(String text) throws IOException, InterruptedException;
    // 主题提取
    String Theme(String text) throws IOException, InterruptedException;
}
