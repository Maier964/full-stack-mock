import React, { Component } from "react";
import { Link } from "react-router-dom";
import axios from "axios"

class Register extends Component {
  constructor() {
    super();

    this.state = {
      email: "",
      password: "",
      name: "",
      address: "",
      age: 0,
      hasAgreed: false
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    let target = event.target;
    let value = target.type === "checkbox" ? target.checked : target.value;
    let name = target.name;

    this.setState({
      [name]: value
    });
  }

  handleSubmit(e) {
    e.preventDefault();

    console.log("The form was submitted with the following data:");
    console.log(this.state);

    // Create user in backend DB

    if ( this.state.name === '' || this.state.password === '' ||
    this.state.address === '' || this.state.email === '' ||
    this.state.age === 0 ){
        alert("Fail!")
        return
    }

    if ( this.state.hasAgreed === false ){
        alert("Fail! Please accept the terms and conditions!")
        return
    }
    
    axios.post("http://localhost:1234/addclient", 
    {
        id: 45, // This value is redundant, id can be anything, it will be randomly generated in the backend anyways.. it just need to be here for the "id" json field
        name: this.state.name,
        password: this.state.password,
        address: this.state.address,
        email: this.state.email,
        age: this.state.age
    }).then( function(response){
        console.log( response.data )
        if ( response.data ===  " <b> Success! </b> " )
            alert("Success!");
        else 
            alert("Unexpected error. Possible errors:\n* server was busy\n* username already taken");
    } ).catch( function(error){
        alert("Username already taken!");
    } )

  }

  render() {
    return (
      <div className="formCenter">
        <form onSubmit={this.handleSubmit} className="formFields">
          <div className="formField">
            <label className="formFieldLabel" htmlFor="name">
              Username
            </label>
            <input
              type="text"
              id="name"
              className="formFieldInput"
              placeholder="Enter your username"
              name="name"
              value={this.state.name}
              onChange={this.handleChange}
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="password">
              Password
            </label>
            <input
              type="password"
              id="password"
              className="formFieldInput"
              placeholder="Enter your password"
              name="password"
              value={this.state.password}
              onChange={this.handleChange}
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="email">
              E-Mail Address
            </label>
            <input
              type="email"
              id="email"
              className="formFieldInput"
              placeholder="Enter your email"
              name="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="address">
              Address
            </label>
            <input
              type="text"
              id="address"
              className="formFieldInput"
              placeholder="Enter your (billing) address"
              name="address"
              value={this.state.address}
              onChange={this.handleChange}
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="age">
              Age
            </label>
            <input
              type="number"
              id="age"
              className="formFieldInput"
              placeholder="Enter your age"
              name="age"
              value={this.state.age}
              onChange={this.handleChange}
            />
          </div>

          <div className="formField">
            <label className="formFieldCheckboxLabel">
              <input
                className="formFieldCheckbox"
                type="checkbox"
                name="hasAgreed"
                value={this.state.hasAgreed}
                onChange={this.handleChange}
              />{" "}
              I agree to all the statements in the {" "}
              <a href="https://www.termsfeed.com/live/18d39ca1-4464-4092-94f0-75f48eef49a6" className="formFieldTermsLink">
                terms of service
              </a>
            </label>
          </div>

          <div className="formField">
            <button className="formFieldButton">Register</button>{" "}
            <Link to="/login" className="formFieldLink">
              I'm already a member
            </Link>
          </div>
        </form>
      </div>
    );
  }
}
export default Register;
