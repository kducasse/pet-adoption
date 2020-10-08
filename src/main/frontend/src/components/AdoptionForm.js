import React, { useState } from 'react'

const AdoptionForm = props => {
  const [newApplication, setNewApplication] = useState({
    name: "",
    phoneNumber: "",
    email: "",
    homeStatus: "default",
  })

  const [adoptAppStatus, setAdoptAppStatus] = useState("")
  const [errors, setErrors] = useState(null)

  const handleAppChange = event => {
    setNewApplication({
      ...newApplication,
      [event.currentTarget.name]: event.currentTarget.value
    })
  }

  const handleAppSubmit = event => {
    event.preventDefault()
    let payload = {
      name: newApplication.name,
      phoneNumber: newApplication.phoneNumber,
      email: newApplication.email,
      homeStatus: newApplication.homeStatus,
      applicationStatus: "pending",
      adoptablePet: { id: props.petId},
    }
   
      fetch("/api/v1/adoption_application", {
        method: 'POST',
        body: JSON.stringify(payload),
        headers: {'Content-Type': 'application/json' }
      })
        .then(response => {
          if (response.ok) {
            setAdoptAppStatus("Your request is in process")
          } else {
            return response.json()
            .then(bindingResults => {
              let results = bindingResults.map( (errorObject, index) => {
                let field = errorObject.field.replace(/([A-Z])/g, ' $1').replace(/^./, function(str){ return str.toUpperCase(); })
                let message = errorObject.defaultMessage
                return <p key={index}>{field} {message}</p>
              })
             setErrors(results)
             })
          }
        })
        .catch(error => {
          console.log(error)
        })
  }

  let errorTemplate;
  if (errors) {
    errorTemplate = errors
  }

  let adoptForm;
  if (adoptAppStatus !== "Your request is in process") {
    adoptForm = (
      <div className="adoption-form-section">
        <form className="put-pet-up-for-adoption" onSubmit={handleAppSubmit}>
          <h2>Pet Adoption Form</h2>
          <div className="errors">
            {errorTemplate}
          </div>
          <div>
            <label htmlFor="name">Your Name:
            <input type="text" name="name" id="name" onChange={handleAppChange} value={newApplication.name} />
            </label>
          </div>

          <div>
            <label htmlFor="phoneNumber">Phone Number:
            <input type="text" name="phoneNumber" id="phoneNumber" onChange={handleAppChange} value={newApplication.phoneNumber} />
            </label>
          </div>

          <div>
            <label htmlFor="email">Email Address:
              <input type="text" name="email" id="email" onChange={handleAppChange} value={newApplication.email} />
            </label>
          </div>

          <div>
            <label htmlFor="homeStatus">Select Home Status:</label>
            <select name="homeStatus" id="homeStatus" onChange={handleAppChange} value={newApplication.homeStatus}>
              <option value="default" disabled hidden>--Select Home Status--</option>
              <option value="own">Own</option>
              <option value="rent">Rent</option>
            </select>
          </div>

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
        <b>{adoptAppStatus}</b>
      </h1>
      {adoptForm}
    </>
  )
}

export default AdoptionForm