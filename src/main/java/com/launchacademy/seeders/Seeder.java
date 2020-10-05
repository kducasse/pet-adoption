package com.launchacademy.seeders;

import com.google.common.collect.Lists;
import com.launchacademy.models.AdoptablePet;
import com.launchacademy.models.PetType;
import com.launchacademy.repositories.AdoptablePetRepository;
import com.launchacademy.repositories.PetTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

  private PetTypeRepository petTypeRepository;
  private AdoptablePetRepository adoptablePetRepository;

  @Autowired
  public Seeder(PetTypeRepository petTypeRepository, AdoptablePetRepository adoptablePetRepository) {
    this.petTypeRepository = petTypeRepository;
    this.adoptablePetRepository = adoptablePetRepository;
  }

  @Override
  public void run(String... args) throws Exception {
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

    if (Lists.newArrayList(petTypeRepository.findAll()).isEmpty()) {
      petTypeRepository.save(twoLegged);
      petTypeRepository.save(fourLegged);
    }

    if (Lists.newArrayList(adoptablePetRepository.findAll()).isEmpty()) {
      AdoptablePet clifford = new AdoptablePet();
      clifford.setName("Clifford");
      clifford.setImgUrl("https://images2.minutemediacdn.com/image/upload/c_crop,h_1181,w_2100,x_0,y_328/f_auto,q_auto,w_1100/v1554923476/shape/mentalfloss/24523-pbs_scholastic.jpg");
      clifford.setAge(5);
      clifford.setVaccinationStatus(true);
      clifford.setAdoptionStory("The big red dog, he loves dinosaur bones");
      clifford.setAdoptionStatus("null");
      clifford.setPetType(fourLegged);
      adoptablePetRepository.save(clifford);

      AdoptablePet tweety = new AdoptablePet();
      tweety.setName("Tweety");
      tweety.setImgUrl("https://i.pinimg.com/736x/84/25/eb/8425eb206150472144136b4132e6f719.jpg");
      tweety.setAge(4);
      tweety.setVaccinationStatus(true);
      tweety.setAdoptionStory("Aka Tweety Bird or Tweety Pie, is a male yellow canary. He likes to foil cats attempts to catch him, sometimes quite aggressively. Tweety Pie is perhaps the cutest cartoon animal of all time.");
      tweety.setAdoptionStatus("null");
      tweety.setPetType(twoLegged);
      adoptablePetRepository.save(tweety);
    }
  }
}
