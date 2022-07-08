package app;

import app.entity.Address;
import app.entity.Country;
import app.entity.Region;
import app.entity.User;
import javassist.tools.reflect.Reflection;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.reflection.ReflectionManager;
import org.hibernate.annotations.common.reflection.ReflectionUtil;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static Session getSession(){
        return getSessionFactory().openSession();
    }

    @SneakyThrows
    private static SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.h2.Driver");
            properties.put(Environment.URL, "jdbc:h2:mem:test");
            properties.put(Environment.USER, "su");
            properties.put(Environment.PASS, "");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
            properties.put(Environment.SHOW_SQL, "false");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Region.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }

}
