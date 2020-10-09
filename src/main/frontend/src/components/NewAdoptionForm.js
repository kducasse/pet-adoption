import React, {useState} from "react"

const NewAdoptionForm = props => {
  const [newApplication, setNewApplication] = useState(props.adoptionApplication)
  const [completedApplication, setCompletedApplication] = useState(null)

  const handleAppChange = event => {
    setNewApplication({
      ...newApplication,
      [event.currentTarget.name]: event.currentTarget.value
    })
  }

  const handleAppSubmit = event => {
    event.preventDefault()
    let payload = {
      id: newApplication.id,
      name: newApplication.name,
      phoneNumber: newApplication.phoneNumber,
      email: newApplication.email,
      homeStatus: newApplication.homeStatus,
      applicationStatus: "pending",
      adoptablePet: { id: props.petId},
    }

    fetch(`/api/v1/adoption-applications/update?id=${props.adoptionApplication.id}`, {
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

  let adoptForm 
  if (!completedApplication) {
    adoptForm= (
      <div className="adoption-form-section">
        <form className="put-pet-up-for-adoption" onSubmit={handleAppSubmit}>
          <h2>Pet Adoption Form</h2>
          <div className="errors">
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
            <input className="button submit-btn" type="submit" value="Update" />
          </div>
        </form>
      </div>
    )
  }

  return (
    <>
      {adoptForm}
      {completedApplication}
    </>
  )
}

export default NewAdoptionForm