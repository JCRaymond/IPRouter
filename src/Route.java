import static java.lang.Math.pow;

public class Route {

    private static long MAX = (long)pow(2, 32) - 1;

    private static long[] OCTET_VALS = {16777216, 65536, 256, 1};

    private static long ipToLong(String IP){
        String[] octets = IP.split("\\.");
        long ip = 0;
        for (int i=0; i<4; i++){
            ip += Long.parseLong(octets[i])* OCTET_VALS[i];
        }
        return ip;
    }

    private static long calcSubnet(int subnet){
        int wildcards = 32-subnet;
        int wildcardsize = (int)pow(2, wildcards) - 1;
        return MAX - wildcardsize;
    }

    private String IP;
    private int subnet;
    private long subnetMask;
    private long network;
    private String dest;

    public Route(String IP, int subnet, String dest){
        this.IP = IP;
        this.subnet = subnet;
        this.subnetMask = calcSubnet(this.subnet);
        this.network = ipToLong(this.IP) & this.subnetMask;
        this.dest = dest;
    }

    public Route(String IP_subnet, String dest){
        String[] ip_subnet_split = IP_subnet.split("/");
        this.IP = ip_subnet_split[0];
        this.subnet = Integer.parseInt(ip_subnet_split[1]);
        this.subnetMask = calcSubnet(this.subnet);
        this.network = ipToLong(this.IP) & this.subnetMask;
        this.dest = dest;
    }

    public String getDest(){
        return this.dest;
    }

    @Override
    public String toString() {
        return this.IP + "/" + this.subnet + " => " + this.dest;
    }

    public boolean containsIP(String IP) {
        long ipNetwork = ipToLong(IP) & this.subnetMask;
        return ipNetwork == this.network;
    }
}
