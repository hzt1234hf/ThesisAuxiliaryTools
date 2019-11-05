package com.paper.auxiliary.config;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class RemoteInfo {

    private Map<String,String> infomap;

    private String server_name;
    private Integer server_port;
    private String remote_address;
    private String remote_host;

    public static RemoteInfo REMOTEINFO;

    public static RemoteInfo getInstance(){
        if(REMOTEINFO == null){
            REMOTEINFO = new RemoteInfo();
        }
        return REMOTEINFO;
    }
    public static RemoteInfo getInstance(HttpServletRequest request){
        if(REMOTEINFO == null){
            REMOTEINFO = new RemoteInfo(request);
        }
        else {
            REMOTEINFO.ServerInfo(request);
        }
        return REMOTEINFO;
    }

    private RemoteInfo(){
        super();
        init();
    }

    private RemoteInfo(HttpServletRequest request){
        super();
        init();
        ServerInfo(request);
    }

    private void init(){
        infomap = new LinkedHashMap<String,String>();
    }

    public void ServerInfo(HttpServletRequest request) {
        this.server_name = request.getServerName();
        this.server_port = request.getServerPort();

        this.remote_address = request.getRemoteAddr();
        this.remote_host = request.getRemoteHost();

        infomap.put("服务器名称",this.server_name);
        infomap.put("服务器端口",this.server_port+"");
    }

    @Override
    public String toString() {
        return "RemoteInfo{" +
                "infomap=" + infomap +
                ", server_name='" + server_name + '\'' +
                ", server_port=" + server_port +
                ", remote_address='" + remote_address + '\'' +
                ", remote_host='" + remote_host + '\'' +
                '}';
    }

    public Map<String, String> getInfomap() {
        return infomap;
    }

    public String getServer_name() {
        return server_name;
    }

    public Integer getServer_port() {
        return server_port;
    }

    public String getRemote_address() {
        return remote_address;
    }

    public String getRemote_host() {
        return remote_host;
    }

    public static RemoteInfo getREMOTEINFO() {
        return REMOTEINFO;
    }
}
