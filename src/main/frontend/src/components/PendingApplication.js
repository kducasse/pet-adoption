import React, { useEffect, useState } from "react"
import NewSurrenderForm from "./NewSurrenderForm"
import NewAdoptionForm from "./NewAdoptionForm"

const PendingApplication = props => {

  const [adoptionApplications, setAdoptionApplications] = useState([])
  const [surrenderApplciations, setSurrenderApplications] = useState([])
  const [currentlySelectedForm, setCurrentlySelectedForm] = useState(null)
  const [form, setForm] = useState(null);
  
  const handleSelectionChange = event => {
    setCurrentlySelectedForm(JSON.parse(event.currentTarget.value))
    setForm(null)
  }

  const handleApplicationSubmission = (event) => {
    event.preventDefault();
    
    if (currentlySelectedForm) {
      if(currentlySelectedForm.applicationType.includes("surrender")) {
        setForm(<NewSurrenderForm handleChildComponentSubmission={handleChildComponentSubmission}
          surrenderApplication={currentlySelectedForm}/>)
      }else {
        setForm(<NewAdoptionForm handleChildComponentSubmission={handleChildComponentSubmission} adoptionApplication={currentlySelectedForm}/>)
      }
    }
  }

  const handleChildComponentSubmission = () => {
    setAdoptionApplications([])
    setSurrenderApplications([])
    setCurrentlySelectedForm(null)
  }

  const alternateHandleApplicationSubmission = event => {
    event.preventDefault();
    setForm(<></>)
    if (currentlySelectedForm) {
      let payload;
      if (currentlySelectedForm.applicationType.includes("adoption")) {
        payload = { ...currentlySelectedForm, adoptablePet: { name: currentlySelectedForm.adoptablePet.name } }
      } else {
        payload = { ...currentlySelectedForm, surrenderPetType: { type: currentlySelectedForm.surrenderPetType.type } }
      }
      let confirmation = confirm("Are you sure you want to delete the application?")
      if (confirmation) {
        fetch(`/api/v1/${currentlySelectedForm.applicationType}/delete?id=${currentlySelectedForm.id}`, {
          method: "DELETE",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(payload)
        })
          .then(result => {
            handleChildComponentSubmission()
          })
          .catch(error => {
            console.log(error)
          })
      }
    }
  }

  useEffect(() => {
    fetch("/api/v1/adoption-applications/pending")
      .then(response => {
        if (response.ok) {
          return response
        }
      })
      .then(response => response.json())
      .then(applications => {
        let formattedAppliction = applications.map(application => {
          return { ...application, applicationType: `adoption-applications` }
        })
        setAdoptionApplications(formattedAppliction)
      })
      .catch(error => {
        console.log(error)
      })
  }, [currentlySelectedForm])

  let allAdoptionApplications = adoptionApplications.map(adoptionApp => {
    return (<option key={adoptionApp.id} value={JSON.stringify(adoptionApp)}>{`${adoptionApp.name}, Adoption Application# ${adoptionApp.id},
     ${adoptionApp.applicationStatus}`}</option>
    )
  })

  useEffect(() => {
    fetch("/api/v1/pet-surrender-applications/pending")
      .then(response => {
        if (response.ok) {
          return response
        }
      })
      .then(response => response.json())
      .then(applications => {
        let formattedAppliction = applications.map(application => {
          return { ...application, applicationType: `pet-surrender-applications` }
        })
        setSurrenderApplications(formattedAppliction)
      })
      .catch(error => {
        console.log(error)
      })
  }, [currentlySelectedForm])

  let allSurrenderApplications = surrenderApplciations.map(surrenderApp => {
    return (<option key={surrenderApp.id} value={JSON.stringify(surrenderApp)}>{`${surrenderApp.name}, Surrender Application# ${surrenderApp.id},
     ${surrenderApp.applicationStatus}`}</option>
    )
  })

  return (
    <>
      <form onSubmit={handleApplicationSubmission} className="put-pet-up-for-adoption adoption-form-section">
        <label htmlFor="pendingApplications">Select an application:</label>
        <select onChange={handleSelectionChange} name="pendingApplications" id="pendingApplications">
          <option value="null">--Please choose an option--</option>
          {allAdoptionApplications}
          {allSurrenderApplications}
        </select>

        <input className="submit-btn" type="submit" value="Edit Application" />
        <input onClick={alternateHandleApplicationSubmission} className="submit-btn" type="submit" value="Delete Application" />
      </form>
      {form}
    </>
  )
}

export default PendingApplication