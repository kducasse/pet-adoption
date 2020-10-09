import React, { useState, useEffect } from 'react'

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
                name: currentlySelectedApp.name,
                phoneNumber: currentlySelectedApp.phoneNumber,
                email: currentlySelectedApp.email,
                petName: currentlySelectedApp.petName,
                petAge: currentlySelectedApp.petAge,
                imgUrl: currentlySelectedApp.imgUrl,
                vaccinationStatus: currentlySelectedApp.vaccinationStatus,
                petType: {type: currentlySelectedApp.petType.type},
                applicationStatus: newSurrender
            })
        })
        .then(result => {
            setNewSurrender("")
            setCurrentlySelectedApp(null)
        })
    };


    useEffect(() => {
        fetch("/api/v1/surrender_application").then((response) => response.json())
            .then(surrenderForms => {
                setAllSurrenderForms(surrenderForms)
            })
            .catch(error => {
                console.log(error)
            })
    }
        , [currentlySelectedApp])

        let viewAllForms = allSurrenderForms.map(surrenderForm => {
            return (
                <option key={surrenderForm.id} value={JSON.stringify(surrenderForm)}>
                    --{`${surrenderForm.name}, Surrender Application #${surrenderForm.id}, ${surrenderForm.applicationStatus}`}--
                </option>
            )
        })

    let viewFormDisplay
    if (currentlySelectedApp !== null) {
        viewFormDisplay = (<div>
            <ul className="application-approval">
                <li>{`Applicant Name: ${currentlySelectedApp.name}`}</li>
                <li>{`Phone#: ${currentlySelectedApp.phoneNumber}`}</li>
                <li>{`Email: ${currentlySelectedApp.email}`}</li>
                <li>{`Pet Name: ${currentlySelectedApp.petName}`}</li>
                <li>{`Pet Age: ${currentlySelectedApp.petAge}`}</li>
                <li>{`Pet Type: ${currentlySelectedApp.petType.type}`}</li>
                <li>
                    <img src={currentlySelectedApp.imgUrl} alt={`Photo of ${currentlySelectedApp.name}`} />
                </li>
                <li>{`Is pet Vaccinated: ${currentlySelectedApp.vaccinationStatus}`}</li>
                <li>{`Application Status: ${currentlySelectedApp.applicationStatus}`}</li>
            </ul>
        </div>)
    }

    return (
        <form className="put-pet-up-for-adoption adoption-form-section" onSubmit={handleSurrenderSubmit} >
            <label htmlFor="finishedForms">Select a surrender form to review:</label>
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