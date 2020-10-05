DROP TABLE IF EXISTS pet_types;
CREATE TABLE pet_types (
  id SERIAL PRIMARY KEY,
  type VARCHAR(50) NOT NULL,
  description TEXT,
  img_url_random_animal TEXT NOT NULL
);

DROP TABLE IF EXISTS adoptable_pets;
CREATE TABLE adoptable_pets(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  img_url TEXT NOT NULL,
  age INTEGER CHECK(age >= 0),
  vaccination_status BOOLEAN,
  adoption_story TEXT NOT NULL,
  adoption_status VARCHAR(50) NOT NULL,
  type_id INTEGER REFERENCES pet_types(id) NOT NULL
);

DROP TABLE IF EXISTS adoption_applications;
CREATE TABLE adoption_applications(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  phone_number VARCHAR(10) NOT NULL, 
  email VARCHAR(255) NOT NULL,
  home_status TEXT NOT NULL,
  application_status VARCHAR(50) NOT NULL,
  pet_id INTEGER REFERENCES adoptable_pets(id) NOT NULL
);

DROP TABLE IF EXISTS pet_surrender_applications;
CREATE TABLE pet_surrender_applications(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  phone_number VARCHAR(10) NOT NULL,
  email VARCHAR(255) NOT NULL,
  pet_name VARCHAR(50) NOT NULL,
  pet_age INTEGER CHECK(pet_age > 0),
  pet_type_id INTEGER REFERENCES pet_types(id) NOT NULL,
  pet_image_url TEXT NOT NULL,
  vaccination_status BOOLEAN,
  application_status VARCHAR(50) NOT NULL
 );
