import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const PetTypesIndex = props => {
  const [petTypesDisplay, setPetTypesDisplay] = useState([]);
  useEffect(() => {
    fetch('/api/v1/pet_types')
      .then((response) => response.json())
      .then((petTypes) => {
        setPetTypesDisplay(petTypes.rows.map((petType) => {
          return (
            <div className="columns medium-5 home-pets" key={petType.id}>
              <Link to={`/pets/${petType.id}`}><img className="pet-index-pictures" src={petType.img_url_random_animal} /></Link>
              <div>
                <Link to={`/pets/${petType.id}`}>{petType.type}</Link>
                <p>{petType.description}</p>
              </div>
            </div>
          )
        }));
      });
  }, []);

  return (
    <>
      <div className="pet-index-page-heading">
        <h1>Adopt a pet!</h1>
        <p>Here are the pet types we have available:</p>
      </div>
      <div className="row">
        {petTypesDisplay}
      </div>
    </>
  );
};

export default PetTypesIndex;
