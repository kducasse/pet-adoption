import React from "react"
import { Route, BrowserRouter } from "react-router-dom"
import { Redirect } from "react-router-dom/cjs/react-router-dom.min"
import Navbar from "./Navbar"

const App = props => {
  return (
    <BrowserRouter>
      <Route path="/" component={Navbar} />
      <Redirect to="/pets"/>
    </BrowserRouter>
  )
}

export default App
