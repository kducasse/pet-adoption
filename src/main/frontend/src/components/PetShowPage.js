import React, { useEffect, useState } from 'react';
import AdoptionForm from "./AdoptionForm"

const PetShowPage = (props) => {
  const speciesId = props.match.params.species
  const adoptablePetId = props.match.params.id

  const [adoptablePet, setAdoptablePet] = useState({})
  const [isAdopting, setIsAdopting] = useState(false)
  const [animalPageFound, setAnimalPageFound] = useState(true)

  useEffect(() => {
    // fetch(`/api/v1/show_page?type=${speciesId}&id=${adoptablePetId}`)
    fetch(`/api/v1/adoptable_pets/${adoptablePetId}`)
      .then(response => {
        if (response.ok) {
          return response
        } else {
          setAnimalPageFound(false)
        }
      })
      .then(response => response.json())
      .then(adoptablePet => setAdoptablePet(adoptablePet))
      .catch(error => {
        console.log(error)
      })
  }, [])

  const handleButtonClick = event => {
    event.preventDefault()
    setIsAdopting(true)
  }  
  const animalInformation = (
    <div className="individual-pet">
      <img src={adoptablePet.imgUrl} alt={`Photo of ${adoptablePet.name}`} />
      <div className="individual-pet-information">
        <p id="individual-pet-name"><b>{adoptablePet.name}</b></p>
        <p className="individual-pet-attribute-name"><b>Age:</b> {adoptablePet.age}</p>
        <p className="individual-pet-attribute-name"><b>Vaccination Status:</b> {adoptablePet.vaccinationStatus ? 'Yes' : 'No'}</p>
        <p className="individual-pet-attribute-name"><b>My story:</b> {adoptablePet.adoptionStory}</p>
      </div>
    </div>
  )

  let form = ""
  let submitButton = (
    <form onSubmit={handleButtonClick}>
      <input className="button individual-pet-btn" type="submit" value="Adopt Me!" />
    </form>
  )

  if (isAdopting) {
    form = <AdoptionForm petId={adoptablePetId}/>
    submitButton = ""
  }

  let error
  if (!animalPageFound) {
    error = "404 Error.  Animal is not Found."
    submitButton = ""
    animalInformation = ""
    form = ""
  }

  return (
    <>
      {error}
      {animalInformation}
      {submitButton}
      {form}
    </>
  )
}

export default PetShowPage
