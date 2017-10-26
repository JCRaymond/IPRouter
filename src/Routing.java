public class Routing {

    public static String route(String IP, Route[] routes, String defaultRoute){
        Router r = new Router(defaultRoute).addRoutes(routes);
        return r.getDest(IP);
    }

    public static void main(String[] args) {
        Router r = new Router("Router 2")
                .addRoute("135.46.56.0/22", "Interface 0")
                .addRoute("135.46.60.0/22", "Interface 1")
                .addRoute("192.53.40.0/23", "Router 1");

        String[] ips = {"135.46.63.10", "135.46.57.14", "135.46.52.2", "192.53.40.7", "192.53.56.7"};

        for (String ip : ips) {
            System.out.println(ip + " => " + r.getDest(ip));
        }
    }

}
