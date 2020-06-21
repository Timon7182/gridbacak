package org.company.db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.company.domain.AbstractEntity;
import org.company.domain.Company;
import org.company.domain.Consumer;
import org.company.domain.Producer;
import org.company.repository.ConsumerRepository;
import org.company.repository.CompanyRepository;
import org.company.repository.ProducerRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    //session factory - hibernate. and always null

    private static SessionFactory sessionFactory;

    private final static Logger LOG = LogManager.getLogger();
    private final static Boolean DEBUG_TEMPORARY = false;

    /**
     * Session factory to connect to database
     *
     * AppConfig.setFileName("db.properties") - for hsqldb database
     * AppConfig.setFileName("postgresql.properties") - for postgresql database
     * @return session with db
     * @throws Exception throws simple exception
     *
     */
    public synchronized static SessionFactory getSessionFactory() throws Exception {
        if(sessionFactory==null){



//            AppConfig.setFileName("postgresql.properties");
//            org.apache.commons.configuration2.Configuration c = AppConfig.getConfig();
//
//            String driver = c.getString("driver");
//            String url = c.getString("url");
//            String user=c.getString("user");
//            String pas=c.getString("pass");
//            String dialect = c.getString("dialect");

            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            String host ="easygridhu.postgres.database.azure.com";
            String database="easygrid";
            String url = String.format("jdbc:postgresql://%s/%s", host, database);
            properties.put(Environment.URL, url);
            properties.put(Environment.USER, "easy_admin@easygridhu");
            properties.put(Environment.PASS,"36702919972@3a");
            properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
            properties.put(Environment.HBM2DDL_AUTO,"create-drop");
            properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS,"true");
            properties.put(Environment.ALLOW_UPDATE_OUTSIDE_TRANSACTION, "true");




            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Consumer.class);
            configuration.addAnnotatedClass(Producer.class);
            configuration.addAnnotatedClass(Company.class);

            configuration.addAnnotatedClass(AbstractEntity.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            initDb();

        }
        return sessionFactory;
    }

    private static void initDb() {

        try{
            initCompany();
            initConsumer();
            initProducer();



        }catch (Exception e){
            LOG.error(e);

        }
    }

    private static void initProducer() throws Exception {
        List<String> locations1 = new ArrayList<>();
        locations1.add("Pecs, Some 7a");
        locations1.add("Pecs, Place Zoltan 2");
        List<String> locations2 = new ArrayList<>();
        locations2.add("Kaposvaer, Kacsa 3");
        locations2.add("Dombovar, Dasac utca ");
        List<String> locations3 = new ArrayList<>();
        locations3.add("Pecs, Huipoimi 1a");
        locations3.add("Pecs, Da tak ");
        locations3.add("Pecs, Skazhu tebe 7a");
        locations3.add("Pecs, Szoltan Zoltan 2");
        createProducer("aalybayeva","Akmaral",locations1,72,"akmasunny42gmail.csd","213213Akma",300);
        createProducer("bekas123","Beka",locations2,33,"beka33.csd","Beka3213",100);
        createProducer("gulim32","Gulim",locations3,44,"gulim22.csd","213213Akma",90);

    }

    private static void createProducer(String clientId, String name, List<String> location, float pwConsumption, String email, String password, float powerProduced)throws Exception{


        ProducerRepository producerRepository = ProducerRepository.getInstance();
        producerRepository.save(new Producer(clientId,name,location,pwConsumption,email,password,powerProduced));

    }



    private static void initConsumer() throws Exception {
        List<String> locations1 = new ArrayList<>();
        locations1.add("Pecs, Rokus 7a");
        locations1.add("Pecs, Kodaly Zoltan 2");
        List<String> locations2 = new ArrayList<>();
        locations2.add("Pecs, Boszorkany 3");
        locations2.add("Pecs, Kiraly utca ");
        List<String> locations3 = new ArrayList<>();
        locations3.add("Pecs, Alkotmany 1a");
        locations3.add("Pecs, Baranya ");
        locations3.add("Pecs, Rokus 7a");
        locations3.add("Pecs, Kodaly Zoltan 2");

        createConsumer("danik7182","Daniyar",locations1,30,"dserikov07@gmail.com","Daniyar123");
        createConsumer("timon7182","Timon",locations2,50,"timon7182@gmail.com","Daniyar1243");
        createConsumer("akmasunny","Daniyar",locations3,60,"daniyaar55553@gmail.com","Daniyar1423");


    }

    private static void createConsumer(String clientId, String name, List<String> location, float pwConsumption, String email, String password)throws Exception{


        ConsumerRepository c = ConsumerRepository.getInstance();
        c.save(new Consumer(clientId,name,location,pwConsumption,email,password));

    }


    private static void initCompany() throws Exception {
        createCompany(null,"dsds","ele","dsds","dsad");
    }

    private static void createCompany(Long aLong, String reference, String type, String updatingRate,String id)throws Exception{


        CompanyRepository c = CompanyRepository.getInstance();
        c.save(new Company(aLong,reference,type,updatingRate,id));

    }

}
