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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "id")
@Table(name = "adoption_applications")
public class AdoptionApplication {
  @Id
  @SequenceGenerator(name="adoption_application_generator", sequenceName="adoption_applications_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="adoption_application_generator")
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
  @Size(max = 4, message = "must not be blank")
  @Column(name = "home_status", nullable = false)
  private String homeStatus;

  @Column(name = "application_status", nullable = false)
  private String applicationStatus;

  @ManyToOne
  @JoinColumn(name = "pet_id", nullable = false)
  @JsonIgnoreProperties("adoptionApplicationList")
  private AdoptablePet adoptablePet;
}
