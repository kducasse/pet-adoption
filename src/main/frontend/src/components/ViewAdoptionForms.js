import React, { useState, useEffect } from 'react'
import AdoptionForm from './AdoptionForm'

const ApprovalForm = props => {
    const [newApproval, setNewApproval] = useState("")
    const [allAdoptionForms, setAllAdoptionForms] = useState([])
    const [currentlySelectedForm, setCurrentlySelectedForm] = useState(null)

    const handleApprovalChange = event => {
        setNewApproval(event.currentTarget.value)
    }

    const handleSelectionChange = event => {
        setCurrentlySelectedForm(JSON.parse(event.currentTarget.value))
    }

    const handleApprovalSubmit = event => {
        event.preventDefault()
        fetch("/api/v1/adoption-applications/approval", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...currentlySelectedForm,
                petId: currentlySelectedForm.petId,
                applicationId: currentlySelectedForm.id,
                applicationStatus: newApproval,
                adoptablePet: { id: currentlySelectedForm.adoptablePet.id }
            })
        })
            .then(result => {
                setNewApproval("")
                setCurrentlySelectedForm(null)
            })
            .catch(error => {

            })
    };

    useEffect(() => {
        fetch("/api/v1/adoption-applications").then((response) => response.json())
            .then(adoptionForms => {
                setAllAdoptionForms(adoptionForms)
            })
    }
        , [currentlySelectedForm])

    let allForms = allAdoptionForms.map(adoptionForm => {
        return <option key={adoptionForm.id} value={JSON.stringify(adoptionForm)}>--{`${adoptionForm.name}, Application#${adoptionForm.id}, ${adoptionForm.applicationStatus}`}--</option>
    })

    let formDisplay
    if (currentlySelectedForm !== null) {
        formDisplay = (
            <div>
                <ul className="application-approval" >
                    <li>{`Applicant Id: ${currentlySelectedForm.id}`}</li>
                    <li>{`Pet Id: ${currentlySelectedForm.adoptablePet.id}`}</li>
                    <li>{`Applicant Name: ${currentlySelectedForm.name}`}</li>
                    <li>{`Phone Number: ${currentlySelectedForm.phoneNumber}`}</li>
                    <li>{`Email: ${currentlySelectedForm.email}`}</li>
                    <li>{`Applicant's Home Status: ${currentlySelectedForm.homeStatus}`}</li>
                    <li>{`Application Status: ${currentlySelectedForm.applicationStatus}`}</li>
                    <br></br>
                    <li>
                        <img src={currentlySelectedForm.adoptablePet.imgUrl} alt={`Photo of ${currentlySelectedForm.adoptablePet.name}`} />
                    </li>
                    <li>{`Pet Name: ${currentlySelectedForm.adoptablePet.name}`}</li>
                    <li>{`Pet Age: ${currentlySelectedForm.adoptablePet.age}`}</li>
                    <li>{`Pet Type: ${currentlySelectedForm.adoptablePet.petType.type}`}</li>
                    <li>{`Is pet Vaccinated: ${currentlySelectedForm.adoptablePet.vaccinationStatus}`}</li>
                    <li>{`Application Status: ${currentlySelectedForm.adoptablePet.adoptionStatus}`}</li>
                </ul>
            </div>)
    }

    return (
        <form className="put-pet-up-for-adoption adoption-form-section" onSubmit={handleApprovalSubmit} >
            <label htmlFor="completedForms">Select an adoption form to review:</label>
            <select onChange={handleSelectionChange} name="completedForms" id="completedForms">
                <option value="null">--Please choose an option--</option>
                {allForms}
            </select>

            {formDisplay}

            <label htmlFor="approval">Choose Approval Status:</label>
            <select onChange={handleApprovalChange} value={newApproval} name="approval" id="approval">
                <option value="">--Please choose an option--</option>
                <option value="approved">Approve</option>
                <option value="denied">Deny</option>
            </select>

            <input className="submit-btn" type="submit" value="Submit" />
        </form >)
}

export default ApprovalForm
