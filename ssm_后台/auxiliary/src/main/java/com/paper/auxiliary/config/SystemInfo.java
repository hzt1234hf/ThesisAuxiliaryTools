package com.paper.auxiliary.config;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;


public class SystemInfo {
    private static SystemInfo SYSTEMINFO;

    private Map<String,String> infomap;

    private String os_name;
    private String os_arch;
    private String os_version;
    private int os_cpus;
    private long os_free_memory;
    private long os_total_memory;
    private long os_max_memory;

    private String ip_address;
    private String mac_address;

    private String user_name;
    private String user_dir;
    private String user_home;
    private String user_timezone;
    private String user_country;
    private String user_language;

    private String java_version;
    private String java_home;
    private String java_io_tmpdir;

    private String server_PID;



    public static SystemInfo getInstance()
    {
        if(SYSTEMINFO == null)
        {
            SYSTEMINFO = new SystemInfo();
        }
        return SYSTEMINFO;
    }

    private SystemInfo()
    {
        super();
        init();
    }

    private void getIpAndMac() throws UnknownHostException, SocketException {
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        ni.getInetAddresses().nextElement().getAddress();
        byte[] mac = ni.getHardwareAddress();
        String sIP = address.getHostAddress();
        String sMAC = "";
        Formatter formatter = new Formatter();
        for (int i = 0; i < mac.length; i++) {
            sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i], (i < mac.length - 1) ? "-" : "").toString();
        }
        this.ip_address = sIP;
        this.mac_address = sMAC;
        infomap.put("本服务器IP地址",sIP);
        infomap.put("本服务器MAC地址",sMAC);
    }
//    private void getMemInfo() throws SigarException {
//        Sigar sigar = new Sigar();
//        Mem mem = sigar.getMem();
//        Swap swap = sigar.getSwap();
//        infomap.put("系统内存总量",mem.getTotal()/1024L/1024L + " MB");
//        infomap.put("系统已使用内存",mem.getUsed()/1024L/1024L + " MB");
//        infomap.put("系统可用内存",mem.getFree()/1024L/1024L + " MB");
//
//        infomap.put("系统交换分区总量",swap.getTotal()/1024L/1024L + " MB");
//        infomap.put("系统已使用交换分区",swap.getUsed()/1024L/1024L + " MB");
//        infomap.put("系统可用交换分区",swap.getFree()/1024L/1024L + " MB");
//    }
//
//    private void getCpuInfo() throws SigarException {
//        Sigar sigar = new Sigar();
//
//        CpuInfo infos[] = sigar.getCpuInfoList();
//        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
//            CpuInfo info = infos[i];
//            infomap.put("第" + (i + 1) + "块CPU频率",info.getMhz() + "Mhz");
//            infomap.put("第" + (i + 1) + "块CPU类型",info.getVendor() + "  " +info.getModel() );
//            infomap.put("第" + (i + 1) + "块CPU缓存器数量",String.valueOf(info.getCacheSize()));
//        }
//    }
    private void init()
    {
        infomap = new LinkedHashMap<String,String>();

        Properties props = System.getProperties();
        this.os_name = props.getProperty("os.name");
        this.os_arch = props.getProperty("os.arch");
        this.os_version = props.getProperty("os.version");
        this.os_cpus = Runtime.getRuntime().availableProcessors();
        this.os_free_memory = Runtime.getRuntime().freeMemory();
        this.os_total_memory = Runtime.getRuntime().totalMemory();
        this.os_max_memory = Runtime.getRuntime().maxMemory();

        this.user_name = props.getProperty("user.name");
        this.user_home = props.getProperty("user.home");
        this.user_dir = props.getProperty("user.dir");
        this.user_timezone = props.getProperty("user.timezone");
        this.user_country = props.getProperty("user.country");
        this.user_language = props.getProperty("user.language");

        this.java_version = props.getProperty("java.version");
        this.java_home = props.getProperty("java.home");
        this.java_io_tmpdir = props.getProperty("java.io.tmpdir");

        this.server_PID = props.getProperty("PID");

        infomap.put("操作系统的名称",this.os_name);
        infomap.put("操作系统的构架",this.os_arch);
        infomap.put("操作系统的版本",this.os_version);

        infomap.put("系统可用处理器核心数",this.os_cpus+"");
        infomap.put("系统内存总大小",this.os_total_memory+"");
        infomap.put("系统可用内存大小",this.os_free_memory+"");
        infomap.put("系统最大内存大小",this.os_max_memory+"");

//        try {
//            this.getCpuInfo();
//        }catch (Exception e)
//        {
//            System.out.println(e);
//        }
//
//        try {
//            this.getMemInfo();
//        }catch(Exception e)
//        {
//            System.out.println(e);
//        }

        try{
            this.getIpAndMac();
        }catch(Exception e)
        {
            System.out.println(e);
            this.ip_address = "";
            this.mac_address = "";
            infomap.put("本服务器IP地址","");
            infomap.put("本服务器MAC地址","");
        }

        infomap.put("当前用户账户名称",this.user_name);
        infomap.put("当前用户主目录",this.user_home );
        infomap.put("用户当前工作目录",this.user_dir);
        infomap.put("所在时区",this.user_timezone);
        infomap.put("所在国家",this.user_country);
        infomap.put("使用语言",this.user_language);

        infomap.put("Java版本",this.java_version);
        infomap.put("Java目录",this.java_home);
        infomap.put("Java临时文件路径",this.java_io_tmpdir);

        infomap.put("服务器PID",this.server_PID);

    }


    public static Map<?, ?> cpuInfo() {
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/stat"));
            buffer = new BufferedReader(inputs);
            String line = "";
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("cpu")) {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    List<String> temp = new ArrayList<String>();
                    while (tokenizer.hasMoreElements()) {
                        String value = tokenizer.nextToken();
                        temp.add(value);
                    }
                    map.put("user", temp.get(1));
                    map.put("nice", temp.get(2));
                    map.put("system", temp.get(3));
                    map.put("idle", temp.get(4));
                    map.put("iowait", temp.get(5));
                    map.put("irq", temp.get(6));
                    map.put("softirq", temp.get(7));
                    map.put("stealstolen", temp.get(8));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }

    public static List<Integer> cpuInfoMin() {
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/stat"));
            buffer = new BufferedReader(inputs);
            String line = "";
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("cpu")) {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    tokenizer.nextToken();
                    while (tokenizer.hasMoreElements()) {
                        String value = tokenizer.nextToken();
                        list.add(Integer.parseInt(value));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return list;
    }

    public static int cpuUsage() {
        try {
            Map<?, ?> map1 = SystemInfo.cpuInfo();
            Thread.sleep(5 * 1000);
            Map<?, ?> map2 = SystemInfo.cpuInfo();

            long user1 = Long.parseLong(map1.get("user").toString());
            long nice1 = Long.parseLong(map1.get("nice").toString());
            long system1 = Long.parseLong(map1.get("system").toString());
            long idle1 = Long.parseLong(map1.get("idle").toString());

            long user2 = Long.parseLong(map2.get("user").toString());
            long nice2 = Long.parseLong(map2.get("nice").toString());
            long system2 = Long.parseLong(map2.get("system").toString());
            long idle2 = Long.parseLong(map2.get("idle").toString());

            long total1 = user1 + system1 + nice1;
            long total2 = user2 + system2 + nice2;
            float total = total2 - total1;

            long totalIdle1 = user1 + nice1 + system1 + idle1;
            long totalIdle2 = user2 + nice2 + system2 + idle2;
            float totalidle = totalIdle2 - totalIdle1;

            float cpusage = (total / totalidle) * 100;
            return (int) cpusage;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int cpuUsageMin() {
        try {
            List<Integer> rec1 = SystemInfo.cpuInfoMin();
            Thread.sleep(2 * 1000);
            List<Integer> rec2 = SystemInfo.cpuInfoMin();

            if(rec1.size()<3)
            {
                System.out.println(rec1.size());
                return 0;
            }
            if(rec2.size()<3)
            {
                System.out.println(rec2.size());
                return 0;
            }
            long total1 = rec1.get(0) + rec1.get(1) + rec1.get(2);
            long total2 = rec2.get(0) + rec2.get(1) + rec2.get(2);
            float total = total2 - total1;

            long totalIdle1 = total1 + rec1.get(3);
            long totalIdle2 = total2 + rec2.get(3);
            float totalidle = totalIdle2 - totalIdle1;

            float cpuusage = (total / totalidle) * 100;
            return (int) cpuusage;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int memoryUsage() {
        Map<String, Object> map = new HashMap<String, Object>();
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/meminfo"));
            buffer = new BufferedReader(inputs);
            String line = "";
            while (true) {
                line = buffer.readLine();
                if (line == null)
                    break;
                int beginIndex = 0;
                int endIndex = line.indexOf(":");
                if (endIndex != -1) {
                    String key = line.substring(beginIndex, endIndex);
                    beginIndex = endIndex + 1;
                    endIndex = line.length();
                    String memory = line.substring(beginIndex, endIndex);
                    String value = memory.replace("kB", "").trim();
                    map.put(key, value);
                }
            }

            long memTotal = Long.parseLong(map.get("MemTotal").toString());
            long memFree = Long.parseLong(map.get("MemFree").toString());
            long memused = memTotal - memFree;
            long buffers = Long.parseLong(map.get("Buffers").toString());
            long cached = Long.parseLong(map.get("Cached").toString());

            double usage = (double) (memused - buffers - cached) / memTotal * 100;
            return (int) usage;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public static int memoryUsageMin() {
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        List<Long> list = new ArrayList<Long>();
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/meminfo"));
            buffer = new BufferedReader(inputs);
            String line = "";
            Integer count = 0;
            while (true) {
                line = buffer.readLine();
                if (line == null)
                    break;
                int beginIndex = 0;
                int endIndex = line.indexOf(":");
                if (endIndex != -1) {
                    String key = line.substring(beginIndex, endIndex);
                    beginIndex = endIndex + 1;
                    endIndex = line.length();
                    String memory = line.substring(beginIndex, endIndex);
                    String value = memory.replace("kB", "").trim();
                    list.add(Long.parseLong(value));
                    count++;
                    if(count>=5) break;
                }
            }

            double usage = (double) (list.get(0) - list.get(1) - list.get(3) - list.get(4)) / list.get(0) * 100;
            return (int) usage;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "SystemInfo{" +
                "os_name='" + os_name + '\'' +
                ", os_arch='" + os_arch + '\'' +
                ", os_version='" + os_version + '\'' +
                ", os_cpus=" + os_cpus +
                ", os_free_memory=" + os_free_memory +
                ", os_total_memory=" + os_total_memory +
                ", os_max_memory=" + os_max_memory +
                ", ip_address='" + ip_address + '\'' +
                ", mac_address='" + mac_address + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_dir='" + user_dir + '\'' +
                ", user_home='" + user_home + '\'' +
                ", user_timezone='" + user_timezone + '\'' +
                ", user_country='" + user_country + '\'' +
                ", user_language='" + user_language + '\'' +
                ", java_version='" + java_version + '\'' +
                ", java_home='" + java_home + '\'' +
                ", java_io_tmpdir='" + java_io_tmpdir + '\'' +
                ", server_PID='" + server_PID + '\'' +
                ", infomap=" + infomap +
                '}';
    }

    public String getOs_name() {
        return os_name;
    }

    public String getOs_arch() {
        return os_arch;
    }

    public String getOs_version() {
        return os_version;
    }

    public int getOs_cpus() {
        return os_cpus;
    }

    public long getOs_free_memory() {
        return os_free_memory;
    }

    public long getOs_total_memory() {
        return os_total_memory;
    }

    public long getOs_max_memory() {
        return os_max_memory;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getMac_address() {
        return mac_address;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_dir() {
        return user_dir;
    }

    public String getUser_home() {
        return user_home;
    }

    public String getUser_timezone() {
        return user_timezone;
    }

    public String getUser_country() {
        return user_country;
    }

    public String getUser_language() {
        return user_language;
    }

    public String getJava_version() {
        return java_version;
    }

    public String getJava_home() {
        return java_home;
    }

    public String getJava_io_tmpdir() {
        return java_io_tmpdir;
    }

    public String getServer_PID() {
        return server_PID;
    }

    public Map<String, String> getInfomap() {
        return infomap;
    }

    public static SystemInfo getSYSTEMINFO() {
        return SYSTEMINFO;
    }
}

