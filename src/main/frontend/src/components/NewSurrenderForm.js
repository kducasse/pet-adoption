import React, { useState } from "react"

const NewSurrenderForm = (props) => {
  
  const [newPet, setNewPet] = useState({
    name: props.surrenderApplication.name,
    phoneNumber: props.surrenderApplication.phoneNumber,
    email: props.surrenderApplication.email,
    petName: props.surrenderApplication.petName,
    petAge: props.surrenderApplication.petAge,
    surrenderPetType: props.surrenderApplication.surrenderPetType.type,
    imgUrl: props.surrenderApplication.imgUrl,
    vaccinationStatus: props.surrenderApplication.vaccinationStatus == true ? "true": "false",
  })
  const [completedApplication, setCompletedApplication] = useState(null)

  const handlePetChange = event => {
    setNewPet({
      ...newPet,
      [event.currentTarget.name]: event.currentTarget.value
    })
  }

  const handlePetSubmit = event => {
    event.preventDefault()
    let payload = {
      id: props.surrenderApplication.id,
      name: newPet.name,
      phoneNumber: newPet.phoneNumber,
      email: newPet.email,
      petName: newPet.petName,
      petAge: newPet.petAge,
      surrenderPetType: {
        id: 999,
        type: newPet.surrenderPetType,
        description: "pet type",
        imgUrl: "image url",
        adoptablePets: [],
        petSurrenderApplications: []
      },
      imgUrl: newPet.imgUrl,
      vaccinationStatus: newPet.vaccinationStatus,
      applicationStatus: "pending"
    }

    fetch(`/api/v1/pet-surrender-applications/update?id=${props.surrenderApplication.id}`, {
      method: "PUT",
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
      .then(result => {
        setCompletedApplication(<h1>Application Updated!</h1>)
        props.handleChildComponentSubmission()
      })
      .catch(error => {
        console.log(error)
      })
  }

  let form;
  if (!completedApplication) {
    form = (
      <div key={props.bar} className="adoption-form-section">
        <form className="put-pet-up-for-adoption" onSubmit={handlePetSubmit}>
          <h2>Put a Pet Up for Adoption!</h2>
          <div id="errors">
          </div>
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

          <label htmlFor="surrenderPetType">Select Pet Type:</label>
          <select name="surrenderPetType" id="surrenderPetType" onChange={handlePetChange} value={newPet.surrenderPetType}>
            <option value="" disabled hidden>--Select Pet Type--</option>
            <option value="Four-legged">Four-Legged</option>
            <option value="Two-legged">Two-Legged</option>
          </select>

          <label htmlFor="image">Image Url:
        <input type="text" name="imgUrl" id="imgUrl" onChange={handlePetChange} value={newPet.imgUrl} />
          </label>

          <label htmlFor="vaccinationStatus">Is your pet vaccinated? </label>
          <select name="vaccinationStatus" id="vaccinationStatus" onChange={handlePetChange} value={newPet.vaccinationStatus}>
            <option value="" disabled hidden>--Choose one of the following--</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>

          <div>
            <input className="button submit-btn" type="submit" value="Update" />
          </div>
        </form>
      </div>
    )
  }

  return (
    <>
      {form}
      {completedApplication}
    </>
  )
}

export default NewSurrenderForm