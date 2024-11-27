
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weather.WeatherRunner;
import weather.app.WeatherServiceManager;
import weather.core.entity.WeatherShot;

import java.io.*;
import java.util.List;

@WebServlet("/weather")
public class GetWeatherServlet extends HttpServlet {
    //http://localhost:8080/weather?location=47.6088285,-122.5046043
    //http://localhost:8080/weather?location=Oslo
    //http://localhost:8080/weather?location=Sidney

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String location = req.getParameter("location");
        WeatherServiceManager weatherServiceManager = WeatherRunner.getInstance();
        List<WeatherShot> weatherShotList = weatherServiceManager.getWeather(location);
        Gson gson = new Gson();
        gson.toJson(weatherShotList);
        String jsonString = gson.toJson(weatherShotList);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}