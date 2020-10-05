import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const AvailablePetsIndex = props => {
  const [availablePetsDisplay, setAvailablePetsDisplay] = useState([]);
  let species = props.match.params.species;

  useEffect(() => {
    fetch(`/api/v1/adoptable_pets?type=${species}`)
      .then((response) => response.json())
      .then((availablePets) => {
        setAvailablePetsDisplay(availablePets.map((pet) => {
          if (pet.adoptionStatus == "null" || pet.adoptionStatus == "denied") {
            return (
              <div className="columns small-3 display-pets" key={pet.id}>
                <img src={pet.imgUrl} alt={`Photo of ${pet.name}`} />
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
      <div className="grid-container">
        <div className="grid-x">
          {availablePetsDisplay}
        </div>
      </div>

    </>
  );
};

export default AvailablePetsIndex;