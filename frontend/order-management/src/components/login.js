import axios from "axios";
import React, { Component } from "react";
import { Link } from "react-router-dom";
import {
  FacebookLoginButton,
  InstagramLoginButton
} from "react-social-login-buttons";
import Feed from "./feed";


class Login extends Component {

  constructor() {
    super();

    this.state = {
      email: "",
      password: "",
      redirect: false,
      admin: false,
      id: 0,
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
    })
  }

  handleSubmit(event) {
    event.preventDefault();

    var self = this

    console.log("The form was submitted with the following data:");
    console.log(this.state);
    // Fetch request in database (currently only have name and password, not email and password)

    if ( this.state.email === "" || this.state.password === "" ){
        alert("Invalid credentials")
        return;
    }

    axios.get('http://localhost:1234/findByEmailAndPassword',
    { params: { email: this.state.email, password: this.state.password } }
    ).then( 
        function( response ){
            if ( response.data !== "No user matches these credentials." ){
                alert("Success!");
                console.log(self.state.id)
                self.state.id = response.data.id
                console.log( self.state.id )
                self.setState( { redirect:true } )
                return;
            }
            else
                alert("Invalid credentials!");
                return;
        }
     ).catch( function(error) {
         console.log(error);
         alert("Invalid credentials!");
     })
    


  }


  render() {
    return (
      <div>
           { this.state.redirect ? <Feed email={this.state.email} id={this.state.id} /> :  <div className="formCenter">
        <form className="formFields" onSubmit={this.handleSubmit}>
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
            <button className="formFieldButton">Login</button>{" "}
            <Link to="/" className="formFieldLink">
              Create an account
            </Link>
          </div>

          <div className="socialMediaButtons">
            <div className="facebookButton">
              <FacebookLoginButton onClick={() => alert("TODO")} />
            </div>

            <div className="instagramButton">
              <InstagramLoginButton onClick={() => alert("TODO")} />
            </div>
          </div>
        </form>
      </div>
       }
       </div>
    );
  }
}

export default Login;
