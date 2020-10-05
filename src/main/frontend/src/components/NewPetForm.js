import React, { useState } from 'react'

const NewPetForm = props => {
  const [newPet, setNewPet] = useState({
    name: "",
    phoneNumber: "",
    email: "",
    petName: "",
    petAge: "",
    petType: "default",
    petImage: "",
    vaccinationStatus: "default"
  })

  const [appStatus, setAppStatus] = useState("");

  const handlePetChange = event => {
    setNewPet({
      ...newPet,
      [event.currentTarget.name]: event.currentTarget.value
    })
  }

  const handlePetSubmit = event => {
    event.preventDefault()
    let payload = {
      name: newPet.name,
      phoneNumber: newPet.phoneNumber,
      email: newPet.email,
      petName: newPet.petName,
      petAge: newPet.petAge,
      petType: newPet.petType,
      petImage: newPet.petImage,
      vaccinationStatus: newPet.vaccinationStatus
    }

    let isFilledOut = true;

    let newPetKeys = Object.keys(payload)
    newPetKeys.forEach(key => {
      if (newPet[key] === "") {
        isFilledOut = false
      }
    })

    if (isFilledOut) {
      fetch("/api/v1/pet_surrender_applications", {
        method: 'POST',
        body: JSON.stringify(payload),
        headers: { 'Content-Type': 'application/json' }
      })
        .then(result => {
          if(result.ok){
          setAppStatus("Your request is in process")
         } })
        .catch(error => {
          console.log(error)
        })
    }
  }

  let form;
  if(appStatus !== "Your request is in process"){
    form = (
      <div className="adoption-form-section">
        <form className="put-pet-up-for-adoption" onSubmit={handlePetSubmit}>
          <h2>Put a Pet Up for Adoption!</h2>
          <label htmlFor="name">Your Name:
            <input type="text" name="name" id="name" onChange={handlePetChange} value={newPet.name} />
          </label>
        
          <label htmlFor="phoneNumber">Phone Number:
            <input type="text" name="phoneNumber" id="phoneNumber" onChange={handlePetChange} value={newPet.phoneNumber} />
          </label>
        
          <label htmlFor="email">Email Address:
            <input type="text" name="email" id="email" onChange={handlePetChange} value={newPet.email} />
          </label>
        
          <label htmlFor="petName">Pet Name:
            <input type="text" name="petName" id="petName" onChange={handlePetChange} value={newPet.petName} />
          </label>
        
          <label htmlFor="petAge">Pet Age:
            <input type="text" name="petAge" id="petAge" onChange={handlePetChange} value={newPet.petAge} />
          </label>
        
          <label htmlFor="petType">Select Pet Type:</label>
            <select name="petType" id="petType" onChange={handlePetChange} value={newPet.petType}>
              <option value="default" disabled hidden>--Select Pet Type--</option>
              <option value="2">Four-Legged</option>
              <option value="1">Two-Legged</option>
            </select>
        
          <label htmlFor="image">Image Source:
            <input type="text" name="petImage" id="petImage" onChange={handlePetChange} value={newPet.petImage} />
          </label>
        
          <label htmlFor="vaccinationStatus">Is your pet vaccinated? </label>
            <select name="vaccinationStatus" id="vaccinationStatus" onChange={handlePetChange} value={newPet.vaccinationStatus}>
              <option value="default" disabled hidden>--Choose one of the following--</option>
              <option value="true">Yes</option>
              <option value="false">No</option>
          </select>
        
          <div>
            <input className="button submit-btn" type="submit" value="Submit" />
          </div>
        </form>
      </div>
     )
    }

  return (
    <>
      <h1>
        <b>{appStatus}</b>
      </h1>
      {form}
    </>
  )
}

export default NewPetForm
