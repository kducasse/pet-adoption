package com.launchacademy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pet_types")
public class PetType {
  @Id
  @SequenceGenerator(name="pet_type_generator", sequenceName="pet_types_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="pet_type_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @NotBlank
  @Size(max = 50)
  @Column(nullable = false)
  private String type;

  @Column
  private String description;

  @NotBlank
  @Column(name = "img_url_random_animal", nullable = false)
  private String imgUrl;

  @OneToMany(mappedBy = "petType")
  @JsonIgnoreProperties("petType")
  private List<AdoptablePet> adoptablePets;

  @OneToMany(mappedBy = "surrenderPetType", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("surrenderPetType")
  private List<PetSurrenderApplication> petSurrenderApplications;
}
