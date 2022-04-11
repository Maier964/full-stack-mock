import React, { Component } from "react";
import { HashRouter as Router, Route, NavLink } from "react-router-dom";
import Register from "./components/register";
import Login from "./components/login";
import Feed from "./components/feed";

import "./App.css";

class App extends Component {

  render() {
    return (
      <Router basename="/">
        <div className="App">
          <div className="appAside" />
          <div className="appForm">
            <div className="pageSwitcher">
              <NavLink
                to="/login"
                activeClassName="pageSwitcherItem-active"
                className="pageSwitcherItem"
              >
                Login
              </NavLink>
              <NavLink
                exact
                to="/"
                activeClassName="pageSwitcherItem-active"
                className="pageSwitcherItem"
              >
                Register
              </NavLink>
            </div>

            <div className="formTitle">
              <NavLink
                to="/login"
                activeClassName="formTitleLink-active"
                className="formTitleLink"
              >
                Login
              </NavLink>{" "}
              or{" "}
              <NavLink
                exact
                to="/"
                activeClassName="formTitleLink-active"
                className="formTitleLink"
              >
                Register
              </NavLink>
            </div>
            <Route exact path="/" component={Register} />
            <Route path="/login" component={Login} />
            <Route path="/feed" component={Feed} />
          </div>
        </div>

      </Router>

    );
  }
}

export default App;
