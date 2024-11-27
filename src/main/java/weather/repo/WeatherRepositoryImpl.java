package weather.repo;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import weather.repo.entity.Location;
import weather.repo.entity.MeteoProvider;
import weather.repo.entity.MeteoStation;
import weather.repo.entity.WeatherShot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class WeatherRepositoryImpl implements WeatherRepository {

    private final SessionFactory sessionFactory;

    public WeatherRepositoryImpl() throws Exception {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Location.class)
                .addAnnotatedClass(MeteoProvider.class)
                .addAnnotatedClass(MeteoStation.class)
                .addAnnotatedClass(WeatherShot.class)
                .buildSessionFactory();
    }

    public WeatherRepositoryImpl(Configuration conf) throws Exception {
        this.sessionFactory = conf
                .addAnnotatedClass(Location.class)
                .addAnnotatedClass(MeteoProvider.class)
                .addAnnotatedClass(MeteoStation.class)
                .addAnnotatedClass(WeatherShot.class)
                .buildSessionFactory();
    }


    @Override
    public void saveWeatherShot(WeatherShot weatherShot) {
        sessionFactory.inTransaction(session -> {
            session.persist(weatherShot);
        });
    }

    @Override
    public List<MeteoProvider> getMeteoProvidersByLocation(Location location) {
        List<MeteoProvider> meteoProviderList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String queryStr = "from MeteoProvider as mp join fetch mp.meteoStations as ms where ms.location.id = " + location.getId();
            Query<MeteoProvider> query = session.createQuery(queryStr, MeteoProvider.class);
            meteoProviderList = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return meteoProviderList;
    }
    @Override
    public Location locationByName(Location loc) {
        Location location = null;
        try (Session session = sessionFactory.openSession()) {
            String queryStr = "from Location where name = '" + loc.getName() + "'";
            Query<Location> query = session.createQuery(queryStr, Location.class);
            List<Location> list = query.list();
            location = !list.isEmpty() ? list.get(0) : null;
        } catch (Exception e) {

            System.out.println(e);
        }
        return location;
    }
    @Override
    public MeteoProvider attachMeteoStationToProvider(int meteoStationId, int meteoProviderId){
        MeteoProvider meteoProvider;
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            MeteoStation meteoStation = session.find(MeteoStation.class, meteoStationId);
            meteoProvider = session.find(MeteoProvider.class, meteoProviderId);
            if(meteoProvider == null || meteoStation == null){
                throw new Exception("meteoStation or meteoProvider not found");
            }
            List<MeteoStation> meteoStations = meteoProvider.getMeteoStations();
            meteoStations.add(meteoStation);
            meteoProvider.setMeteoStations(meteoStations);
            session.persist(meteoProvider);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return meteoProvider;
    }
    @Override
    public boolean deleteMeteoStation(int meteoStationId) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            MeteoStation meteoStation = session.find(MeteoStation.class, meteoStationId);
            session.remove(meteoStation);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    @Override
    public MeteoStation createMeteoStationWithLocation(MeteoStation meteoStation) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            Location location = meteoStation.getLocation();
            session.persist(location);
            session.persist(meteoStation);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return meteoStation;
    }

    @Override
    public MeteoProvider createMeteoProvider(MeteoProvider meteoProvider) {
        sessionFactory.inTransaction(session -> {
            session.persist(meteoProvider);
        });
        return meteoProvider;
    }
}
