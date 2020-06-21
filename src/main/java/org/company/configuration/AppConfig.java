package org.company.configuration;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;

public class AppConfig {

   private static Configuration configuration;
   private Configurations configs= new Configurations();;


   private AppConfig(String name) throws Exception {
      configuration=configs.properties(new File(name));
   }

   public static  void setFileName(String fn)throws Exception {
      new AppConfig(fn);
   }

   public static synchronized Configuration getConfig() {
      return configuration;
   }
}
