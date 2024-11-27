
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weather.WeatherRunner;
import weather.app.WeatherServiceManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*


{
"meteostation_id":3
}
*/


@WebServlet("/removeMeteoStation")
public class RemoveMeteoStationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int meteoStationId = Integer.parseInt(req.getParameter("meteostation_id"));
        WeatherServiceManager weatherServiceManager = WeatherRunner.getInstance();
        boolean isRemoved = weatherServiceManager.removeMeteoStation(meteoStationId);
        Object result;
        if (isRemoved) {
            result = new HashMap<>();
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "An error occurred while removing meteostation_id:" + meteoStationId);
            result = error;
        }
        Gson gson = new Gson();
        gson.toJson(result);
        String jsonString = gson.toJson(result);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}