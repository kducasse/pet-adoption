package com.launchacademy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pet_surrender_applications")
public class PetSurrenderApplication {
  @Id
  @SequenceGenerator(name="pet_surrender_application_generator", sequenceName="pet_surrender_applications_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="pet_surrender_application_generator")
  @Column(name="id", nullable=false, unique=true)
  private Integer id;

  @NotBlank
  @Size(max = 50)
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Size(min = 10, max = 10, message = "must be 10 digits long")
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @NotBlank
  @Email
  @Column(nullable = false)
  private String email;

  @NotBlank
  @Size(max = 50)
  @Column(name = "pet_name", nullable = false)
  private String petName;

  @NotNull
  @Min(value = 1)
  @Column(name = "pet_age")
  private Integer petAge;

  @NotBlank
  @Column(name = "pet_image_url", nullable = false)
  private String imgUrl;

  @NotNull
  @Column(name = "vaccination_status")
  private Boolean vaccinationStatus;

  @Size(max = 50)
  @Column(name = "application_status", nullable = false)
  private String applicationStatus;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "pet_type_id", nullable = false)
  @JsonIgnoreProperties("petSurrenderApplications")
  private PetType surrenderPetType;
}
