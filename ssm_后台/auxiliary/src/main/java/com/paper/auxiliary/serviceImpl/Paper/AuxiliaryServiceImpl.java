package com.paper.auxiliary.serviceImpl.Paper;

import com.paper.auxiliary.config.EnvironmentConfig;
import com.paper.auxiliary.service.Paper.AuxiliaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

@Service
public class AuxiliaryServiceImpl implements AuxiliaryService {
    @Autowired
    private EnvironmentConfig environmentConfig;

    public AuxiliaryServiceImpl() throws IOException, InterruptedException {
    }

    private String getResult(Process process) throws IOException, InterruptedException {
        BufferedReader d = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String tmp = d.readLine() + '\n';
        String str = "";
        while(tmp!=null)
        {
            str = str + tmp;
            tmp = d.readLine();
        }
        process.waitFor();
        return str;
    }
    @Override
    public String Similarity(String text1, String text2) throws IOException, InterruptedException {
        String exe = environmentConfig.getPyinterpreter();
        String command = environmentConfig.getSimilarity();
        String[] cmdArr = new String[] {exe, command, text1, text2};
//        String outstr = getResult(Runtime.getRuntime().exec(cmdArr));
//        String result = new String("相似度结果：" + text1 + "--" + text2 + "--" + outstr);
        String result = getResult(Runtime.getRuntime().exec(cmdArr));
        return result;
    }

    @Override
    public String Correction(String text) throws IOException, InterruptedException {
        String exe = environmentConfig.getPyinterpreter();
        String command = environmentConfig.getCorrection();
        String[] cmdArr = new String[] {exe, command, text};
//        String outstr = getResult(Runtime.getRuntime().exec(cmdArr));
//        String result = new String("错字结果：" + text + "---" + outstr);
        String result = getResult(Runtime.getRuntime().exec(cmdArr));
        return result;
    }

    @Override
    public String Theme(String text) throws IOException, InterruptedException {
        String exe = environmentConfig.getPyinterpreter();
        String command = environmentConfig.getTheme();
        String[] cmdArr = new String[] {exe, command, text};
//        String outstr = getResult(Runtime.getRuntime().exec(cmdArr));
//        String result = new String("主题：" + text + "---" + outstr);
        String result = getResult(Runtime.getRuntime().exec(cmdArr));
        return result;
    }

}
