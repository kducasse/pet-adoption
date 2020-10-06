package com.launchacademy.seeders;

import com.launchacademy.models.PetType;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.compress.utils.Lists;

public class PetTypeSeeder {

  public static List<PetType> getSeedData() {
    PetType twoLegged = new PetType();
    twoLegged.setType("Two-legged");
    twoLegged.setDescription("Animals who stand with two legs");
    twoLegged.setImgUrl(
        "https://images.newscientist.com/wp-content/uploads/2014/07/dn25829-2_800.jpg");

    PetType fourLegged = new PetType();
    fourLegged.setType("Four-legged");
    fourLegged.setDescription("Animals who stand on four legs");
    fourLegged.setImgUrl(
        "https://www.ucdavis.edu/sites/default/files/home-site/blogs/one-health/blog-posts/2018/cow-field-one-health-uc-davis.jpg");

    return Arrays.asList(twoLegged, fourLegged);
  }
}
