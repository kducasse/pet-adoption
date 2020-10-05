import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const AdoptedPetsIndex = props => {
  const [AdoptedPets, setAdoptedPets] = useState([]);

  useEffect(() => {
    fetch(`/api/v1/adoptable_pets?type=all`)
      .then((response) => response.json())
      .then((AdoptedPets) => {
        setAdoptedPets(AdoptedPets.rows.map((pet) => {
          if (pet.adoption_status == "approved"){
            return (
              <tr key={pet.id}>
                <td><img src={pet.img_url} alt={`Photo of ${pet.name}`} /></td>
                <td><Link to={`/pets/${pet.type_id}/${pet.id}`}>{pet.name}</Link></td>
                <td>{pet.age}</td>
                <td>{pet.vaccination_status ? 'Yes' : 'No'}</td>
              </tr>
            )
          }
        }));
      });
  }, []);

  return (
    <>
      <h1>These pets have found their forever home</h1>
      <table>
        <thead>
          <tr>
            <th>Picture</th>
            <th>Name</th>
            <th>Age</th>
            <th>Vaccination Status</th>
          </tr>
        </thead>
        <tbody>
          {AdoptedPets}
        </tbody>
      </table>
    </>
  );
};

export default AdoptedPetsIndex;
