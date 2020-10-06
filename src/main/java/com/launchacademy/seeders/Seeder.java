package com.launchacademy.seeders;

import com.google.common.collect.Lists;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

public class Seeder {

  static public <U, T extends CrudRepository<U, Integer>> void seed(T repository, Collection<U> seedData) {
    if (Lists.newArrayList(repository.findAll()).isEmpty()) {
      for (U data: seedData) {
        repository.save(data);
      }
    }
  }
}
