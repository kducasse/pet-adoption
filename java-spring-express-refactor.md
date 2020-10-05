# Adapt a Pet!

We at Launch Academy are big fans of pets. There are many pets out there looking for a home. Your challenge is to build an app that allows good pets to find their ultimate destination! Wait... does this seem familiar? It should! This challenge will serve as an assessment of what you've learned throughout the program, with an emphasis on Spring. You will be taking your work from the Week 9 group project and refactoring the Express backend to run on Spring! To get started find the git URL in your group project, copy it, and in your challenges folder

```no-highlight
dropdb adopt_a_pet && createdb adopt_a_pet
cd challenges
et get java-spring-express-refactor
git clone <url>
mkdir java-spring-express-refactor/src
mkdir java-spring-express-refactor/src/main
mkdir java-spring-express-refactor/src/main/frontend
```

Ria's group use this command:

```no-highlight
cp -a <name of repo you just cloned>/. ./java-spring-express-refactor/src/main/frontend
```


Tom and John's groups, because you both have a folder within your root which contains the root of the project itself we'll need to give you slightly different commands

```no-highlight
cd <name of repo you just cloned>
cp -a <name of root folder for project>/. ../java-spring-express-refactor/src/main/frontend
```

This will give you all of your files from your original project in the folder you'll need to `et submit`.

For the next couple steps your mileage may vary depending on what you included in your original project. Either way start with the following **from within your `java-spring-express-refactor/src/main/frontend` directory**:

***There are trouble shooting instructions following the code block for common issues***

```no-highlight
yarn install
yarn run dev
```

Open a second terminal instance and run:

```no-highlight
yarn run dev:client
```

Potential Issues:
- you may be asked to select a version of Morgan, if so choose the top option
- You may get an error regarding `import express from 'Express'`. If so add `"type":"module",` to the `package.json` at the project root level.
- You may also need to change your node version to 12.x.x (currently 12.18.4) using `nvm use 12.18.4` this may require you to `nvm install 12.18.4`.
After completing any necessary troubleshooting you can then `yarn install` and try starting your servers again.

## Meeting expectations

Unless otherwise specified

- All data must be served from a Spring Rest Controller to a React Component
  - You will need to create a `seeder` to load your data for each entity
  - Make sure to create new migrations when editing your database
  - All entities should have validation at the model level as well as the database level where possible

**_HINT For each Java Class you will need some or all of the following:_**

- model
- controller
- apiController
- DTO
- repository
- service
- seeder
- mapper

### List of Pet Types

```no-highlight
As a potential pet adopter
I want to select from a list of animal types
So that I decide what type of pet I want to adopt
```

Acceptance Criteria:

- Create a database table for `pet_types`
  - id (primary key)
  - type
  - description (optional)
- Navigating to `/` should redirect to `/pets`
- When I visit `/pets`, I should see a listing of the different types of animals up for adoption and a description if available
  - There should be a picture of each type of animal
  - The name of each animal should be a link to a listing for that animal
  - Clicking the picture can also link to the index page for the animal

### Pets of a Given Type

```no-highlight
As a potential pet adopter
I want to visit an index page which lists each adoptable pet of a particular type
So that I can see which pets are available
```

Acceptance Criteria:

- Create a database table for `adoptable_pets` with the following fields
  - name
  - img_url
  - age (optional)
  - vaccination_status (optional, boolean)
  - adoption_story
  - adoption_status
  - type_id (foreign key for the `pet_types` table)
- Visiting `/pets/{specific pet type name}` should bring me to a listing of all available pets that belong to that type
- Each listing should have the following
  - A small picture of the animal
  - Name
  - Age
  - `Vaccination Status`
    - `Yes/no` reflected as a `boolean` in the database
- Each animal's name should be a link to the show page for that animal
  - Optional: you may have the picture also be a link to that animal's show page

### Display Pet Detail

```no-highlight
As a potential pet adopter
I want to see a specific animal's information
So that I can decide whether I want to apply to adopt them
```

Acceptance Criteria

- Visiting `/pets/{specific pet type}/:id` should bring me to the show page of a specific animal
- The animal's picture should be centered on the top of the page
- Below the animal's picture should be an area listing the information about that animal
  - Name
  - Age
  - Vaccination status
  - A story about why the user should adopt them
- At the bottom of the page there should be a button that says `Adopt Me!` which routes renders a form to adopt the specific animal on the same page
- If the id is not found, or does not belong to the specified pet type, I should be presented with a 404 error

### Add a Pet

```no-highlight
As a sad and reluctant pet owner
I want to have a form to put an animal up for adoption
So that no animal goes without a good home
```

Acceptance Criteria

- Create a database table for `pet_surrender_applications` with the following fields
  - name
  - phone number
  - email
  - pet_name
  - pet_age (optional)
  - pet_type_id (foriegn key from `pet_types` table)
  - pet_image_url
  - vaccination_status (optional, boolean)
  - application_status
- Navigating to `/adoptions/new` displays a form for listing a pet to adopt
- The form should contain the following required fields
  - Name
  - Phone Number
  - Email
  - Pet Name
  - Pet Age
  - Pet Type
    - This should be a drop down with options for each of the animal types your site supports
  - Pet Image
  - Vaccination Status
- The form cannot be submitted if the required fields are not filled out
  - Optional: validate format for `Phone Number`, `Email`, and `Pet Age`
- Upon submission request is persisted to `pet_surrender_applications` with an `application_status` of `pending` via a `fetch` request
  - This should be a separate table than the Adoption Request
- If the request is successful then the specific pet page should re-render without the form and a message stating `Your request is in process.`
- If the request is not successful the form should remain on the page
  - Optional: persist the information the user entered into the form and populate it for them

## Exceeds Expectations

### Apply to Adopt

```no-highlight
As a user
I want to fill out the adoption form
So that I can have an adorable animal
```

Acceptance Criteria

- Create a database table for `adoption_applications` with the following fields
  - name
  - phone_number
  - email
  - home_status
  - application_status
  - pet_id (FOREIGN KEY for the `adoptable_pets` table)
- The form is displayed `pet show page` after the `Adopt Me!` button has been clicked.
- The form requires the following information:
  - Name
  - Phone Number
  - Email
  - Home status (own or rent managed via a dropdown)
- Form fields have front end validation to ensure they are filled out
  - Optional: validate format for `Phone Number` and `Email`
- Upon submission request is persisted to database with an `application_status` of `pending` via a `fetch` request
- If the request is successful then the specific pet page should re-render without the form and a message stating `your request is in process.`
- If the request is not successful the form should remain on the page
  - Optional: persist the information the user entered into the form and populate it for them

### Update an Application

```no-highlight
As a user
I want to be able to edit my application
So that I can keep the shelter up to date
```

Acceptance Criteria

- Navigating to `/pending_applications` should show me applications where the status is pending
- There should be a delete button which prompts the user to confirm that they want to delete their application. Upon confirmation the record should be deleted from the database.
- There should be an edit button which takes me to a form pre-populated with the information for the request. This form should look identical to the `new` form
  - Upon submission the existing record should be updated and no new record created

### Review an Adoption Request

```no-highlight
As an employee of the adoption agency
I want to have a form to review adoption requests
So that I can place a pet in the right home
```

Acceptance Criteria

- Create a form for an admin (using routes, not authentication) to approve or deny an adoption request
  - Form submission updates the pending request in the database to `approved` or `denied`
  - Form submission also updates the `adoption_status` on the `adoptable_pets` table
- The form page should contain all information about pet and applicant
- Update the specific Animal Index Pages to only display animals who have a `null` or `denied` adoption status
- Create a new Index page for animals which have been successfully adopted
  - This should display animals of all types which have an adoption status of `approved`

```no-highlight
  As an employee of the adoption agency
  I want to have a form to review surrender applications
  So that I can determine if we can take the pet
```

Acceptance Criteria

- Create a form for an admin (using routes, not authentication) to approve or deny a request to list an animal for adoption
  - Form submission updates the pending request in the database to `approved` or `denied`
  - Form submission also creates an entry in the `adoptable_pets` table for the approved animal
- When a request to list an animal is approved that animal should then appear on the relevant Specific Animal Index Page
