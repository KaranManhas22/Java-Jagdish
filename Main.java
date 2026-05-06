import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        // Bind to all network interfaces (IMPORTANT for Docker/EC2)
        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8080), 0
        );

        server.createContext("/", new MyHandler());

        // Use default executor (can be improved later)
        server.setExecutor(null);

        server.start();

        System.out.println("🚀 Server started on port 8080");
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "Hello from Java EC2 Server 🚀";

            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
