import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const AvailablePetsIndex = props => {
  const [availablePetsDisplay, setAvailablePetsDisplay] = useState([]);
  const species = props.match.params.species;

  useEffect(() => {
    fetch(`/api/v1/adoptable_pets?type=${species}`)
      .then((response) => response.json())
      .then((availablePets) => {
        setAvailablePetsDisplay(availablePets.rows.map((pet) => {
          if (pet.adoption_status == "null" || pet.adoption_status == "denied") {
            return (
              <div className="columns small-3 display-pets" key={pet.id}>
                <img src={pet.img_url} alt={`Photo of ${pet.name}`} />
                <div>
                  <Link to={`/pets/${species}/${pet.id}`}>Name: {pet.name}</Link>
                  <p>Age: {pet.age}</p>
                  <p>Vaccination Status: {pet.vaccination_status ? 'Yes' : 'No'}</p>
                </div>
              </div>
            )
          }
        }));
      });
  }, [species]);

  let speciesName = ""
  if (species == 1) {
    speciesName = "Two Legged"
  } else if (species == 2) {
    speciesName = "Four Legged"
  }

  return (
    <>
      <h1>{speciesName}</h1>
      <div class="grid-container">
        <div class="grid-x">
          {availablePetsDisplay}
        </div>
      </div>

    </>
  );
};

export default AvailablePetsIndex;