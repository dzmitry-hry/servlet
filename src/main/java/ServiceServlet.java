
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import weather.WeatherService;
import weather.WeatherServiceEngine;
import weather.WeatherServiceRequest;
import weather.WeatherServiceResponse;
import weather.exception.BadInputException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet("/weather")
public class ServiceServlet extends HttpServlet {

    private WeatherService weatherService;

    //http://localhost:8080/weather?location=47.6088285,-122.5046043
    //http://localhost:8080/weather?location=Oslo
    //http://localhost:8080/weather?location=Sidney

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String location = req.getParameter("location");
        WeatherService service = WeatherServiceOwm.getInstance();
        String jsonString = (String) service.weatherByLocation(location);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
    }
}