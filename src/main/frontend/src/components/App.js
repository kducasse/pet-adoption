import React from "react"
import { Route, BrowserRouter } from "react-router-dom"
import { Redirect } from "react-router-dom/cjs/react-router-dom.min"
import Navbar from "./Navbar"

const App = props => {
  return (
    <BrowserRouter>
      <Route path="/pets" component={Navbar} />
      <Route path="/adoptions" component={Navbar} />
      <Route path="/admin/review" component={Navbar}/>
      <Route path="/pending_applications" component={Navbar}/>
      <Route exact path="/">
        <Redirect to="/pets" />
      </Route>
    </BrowserRouter>
  )
}

export default App
