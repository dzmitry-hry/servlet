import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weather.WeatherRunner;
import weather.app.WeatherServiceManager;
import weather.core.entity.MeteoStation;

import java.io.*;
        import java.util.HashMap;
import java.util.Map;

/*

{
"location":"Tokyo"
"meteoStation":"meteostation_tokyo"
}
*/
/*
http://localhost:8080/createMeteoStationWithLocation?location=Tokyo&meteoStation=meteoStation_Tokyo
http://localhost:8080/createMeteoStationWithLocation?location=Oslo&meteoStation=meteoStation_Oslo
http://localhost:8080/createMeteoStationWithLocation?location=Sydney&meteoStation=meteoStation_Sydney
http://localhost:8080/createMeteoStationWithLocation?location=Moscow&meteoStation=meteoStation_Moscow
http://localhost:8080/createMeteoStationWithLocation?location=London&meteoStation=meteoStation_London
http://localhost:8080/createMeteoStationWithLocation?location=Mexico&meteoStation=meteoStation_Mexico
http://localhost:8080/attachMeteoStationToProvider?meteoprovider_id=1&meteostation_id=1
http://localhost:8080/attachMeteoStationToProvider?meteoprovider_id=1&meteostation_id=2
http://localhost:8080/attachMeteoStationToProvider?meteoprovider_id=1&meteostation_id=3
http://localhost:8080/attachMeteoStationToProvider?meteoprovider_id=2&meteostation_id=1
http://localhost:8080/attachMeteoStationToProvider?meteoprovider_id=2&meteostation_id=4
*/


@WebServlet("/createMeteoStationWithLocation")
public class CreateMeteoStationWithLocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String meteoStationStr = req.getParameter("meteoStation");
        String locationStr = req.getParameter("location");

        WeatherServiceManager weatherServiceManager = WeatherRunner.getInstance();
        MeteoStation meteoStation = weatherServiceManager.createMeteoStationWithLocation(meteoStationStr,locationStr);
        Object result;
        if (meteoStation != null) {
            result = meteoStation;
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "An error occurred while creation of meteoStation and Location");
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