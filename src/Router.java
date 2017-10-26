import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Router {

    List<Route> routes;
    String def;

    public Router(String defaultRoute){
        this.def = defaultRoute;
        this.routes = new ArrayList<>();
    }

    public Router addRoute(Route r){
        this.routes.add(r);
        return this;
    }

    public Router addRoute(String IP, int subnet, String route){
        this.routes.add(new Route(IP, subnet, route));
        return this;
    }

    public Router addRoute(String IP_subnet, String route){
        this.routes.add(new Route(IP_subnet, route));
        return this;
    }

    public Router addRoutes(Route[] routes){
        this.routes.addAll(Arrays.asList(routes));
        return this;
    }

    public String getDest(String IP){
        for (Route r: this.routes){
            if (r.containsIP(IP)) {
                return r.getDest();
            }
        }
        return def;
    }
}
