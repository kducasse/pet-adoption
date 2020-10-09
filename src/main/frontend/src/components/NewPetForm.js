import React, { useState } from 'react'

const NewPetForm = props => {
  const [newPet, setNewPet] = useState({
    name: "",
    phoneNumber: "",
    email: "",
    petName: "",
    petAge: "",
    surrenderPetType: "",
    imgUrl: "",
    vaccinationStatus: ""
  })

  const [appStatus, setAppStatus] = useState("")
  const [errors, setErrors] = useState(null)

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
      surrenderPetType: {
        id: 1,
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

      fetch("/api/v1/pet-surrender-applications", {
        method: 'POST',
        body: JSON.stringify(payload),
        headers: { 'Content-Type': 'application/json' }
      })
        .then(response => {
          if(response.ok){
          setAppStatus("Your request is in process")
         } else {
           return response.json()
           .then(bindingResults => {
             let results = bindingResults.map( (errorObject, index) => {
               let field = errorObject.field.replace(/([A-Z])/g, ' $1').replace(/^./, function(str){ return str.toUpperCase(); })
               if(field.includes("Surrender")) {
                 field = field.replace("Surrender", "")
               }
               let message = errorObject.defaultMessage
               return <p key={index}>{field} {message}</p>
             })
             if(newPet.surrenderPetType == "") {
               results.push(<p key={results.length + 1}>Pet Type must not be blank</p>)
             }
            setErrors(results)
            })
         } })
        .catch(error => {
          console.log(error)
        })
  }

  let errorTemplate;
  if (errors) {
    errorTemplate = errors
  }

  let form;

  if(appStatus !== "Your request is in process"){
    form = (
      <div className="adoption-form-section">
        <form className="put-pet-up-for-adoption" onSubmit={handlePetSubmit}>
          <h2>Put a Pet Up for Adoption!</h2>
          <div id="errors">
            {errorTemplate}
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
