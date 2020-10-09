package com.launchacademy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString()
@NoArgsConstructor
@Table(name = "adoptable_pets")
public class AdoptablePet {
  @Id
  @SequenceGenerator(name="adoptable_pet_generator", sequenceName="adoptable_pets_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="adoptable_pet_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @NotBlank
  @Size(max = 50)
  @Column
  private String name;

  @NotBlank
  @Column(name = "img_url")
  private String imgUrl;

  @NotNull
  @Min(value = 0)
  @Column
  private Integer age;

  @NotNull
  @Column(name = "vaccination_status")
  private Boolean vaccinationStatus;

  @NotBlank
  @Column(name = "adoption_story")
  private String adoptionStory;

  @NotBlank
  @Size(max = 50)
  @Column(name = "adoption_status")
  private String adoptionStatus;

  @ManyToOne
  @JoinColumn(name = "type_id")
  @JsonIgnoreProperties("adoptablePets")
  private PetType petType;

  @OneToMany(mappedBy = "adoptablePet", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("adoptablePet")
  private List<AdoptionApplication> adoptionApplicationList;
}
