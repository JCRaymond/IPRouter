import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Router {

    private ArrayList<Route> routes;
    private ArrayList<String> dests;
    private String def;

    public Router(String defaultRoute){
        this.def = defaultRoute;
        this.routes = new ArrayList<>();
        this.dests = new ArrayList<>();
        dests.add(def);
    }

    public Router addRoute(Route r){
        this.routes.add(r);
        this.dests.add(r.getDest());
        return this;
    }

    public Router addRoute(String IP, int subnet, String route){
        return this.addRoute(new Route(IP, subnet, route));
    }

    public Router addRoute(String IP_subnet, String route){
        return this.addRoute(new Route(IP_subnet, route));
    }

    public Router addRoutes(Route[] routes){
        this.routes.addAll(Arrays.asList(routes));
        this.dests.addAll(Arrays.asList(routes).stream().map(Route::getDest).collect(Collectors.toList()));
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

    public List<String> getDests() {
        return dests;
    }
}
