import React, { useState, useEffect } from 'react'
import NewPetForm from './NewPetForm'

const SurrenderForm = props => {
    const [newSurrender, setNewSurrender] = useState("")
    const [allSurrenderForms, setAllSurrenderForms] = useState([])
    const [currentlySelectedApp, setCurrentlySelectedApp] = useState(null)

    const handleSurrenderChange = event => {
        setNewSurrender(event.currentTarget.value)
    }

    const handleSelectedChange = event => {
        setCurrentlySelectedApp(JSON.parse(event.currentTarget.value))
    }

    const handleSurrenderSubmit = event => {
        event.preventDefault()
        fetch("/api/v1/adoption_surrender_approval", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: currentlySelectedApp.id,
                applicationStatus: currentlySelectedApp.newSurrender
            })
        })
    };

    useEffect(() => {
        fetch("/api/v1/surrender_application").then((response) => response.json())
            .then(surrenderForms => {
                setAllSurrenderForms(surrenderForms.rows)
            })
    }
        , [])

    let viewAllForms = allSurrenderForms.map(SurrenderForm => {
        return (
            <option key={SurrenderForm.id} value={JSON.stringify(SurrenderForm)}>
                --{`${SurrenderForm.name}, Surrender Application #${SurrenderForm.id}, ${SurrenderForm.application_status}`}--
            </option>
        )
    })

    let viewFormDisplay
    if (currentlySelectedApp !== null) {
        viewFormDisplay = (<div>
            <ul className="form-display">
                <li>{`Applicant Name:${currentlySelectedApp.name}`}</li>
                <li>{`Phone#: ${currentlySelectedApp.phone_number}`}</li>
                <li>{`Email: ${currentlySelectedApp.email}`}</li>
                <li>{`Pet Name# ${currentlySelectedApp.pet_name}`}</li>
                <li>{`Pet Age# ${currentlySelectedApp.pet_age}`}</li>
                <li>{`Pet Type: ${currentlySelectedApp.pet_type}`}</li>
                <li>{`Pet Image: ${currentlySelectedApp.pet_image_url}`}</li>
                <li>{`Is pet Vaccinated: ${currentlySelectedApp.vaccination_status}`}</li>
                <li>{`Application Status: ${currentlySelectedApp.application_status}`}</li>
            </ul>
        </div>)
    }

    return (
        <form className="put-pet-up-for-adoption adoption-form-section" onSubmit={handleSurrenderSubmit} >
            <label htmlFor="finishedForms">Select a form to review:</label>
            <select onChange={handleSelectedChange} name="finishedForms" id="finishedForms">
                <option value="null">--Please choose an option--</option>
                {viewAllForms}
            </select>

            {viewFormDisplay}

            <label htmlFor="approval">Choose Approval Status:</label>
            <select onChange={handleSurrenderChange} value={newSurrender} name="approval" id="approval">
                <option value="">--Please choose an option--</option>
                <option value="approved">Approve</option>
                <option value="denied">Deny</option>
            </select>

            <input className="submit-btn" type="submit" value="Submit" />
        </form>)
}

export default SurrenderForm