import axios from "axios";
import React, { Component } from "react";
import EnhancedTable from "./table";
import { Button } from "@mui/material";
import Login from "./login";
import AdminPanel from "./adminpanel";

class Feed extends Component {

        constructor() {
        super();

        this.state = {
            admin:false,
        }
    }

    componentDidMount(){
        if ( this.props.email === "admin@administrator.adm" ) 
        {
            this.state.admin = true

            console.log("Macar intrii?")
        }
    }




  render() {

    // const {products} = this.state;

    return (
        <div>
        { this.state.admin ? <AdminPanel/> : <div>
        <div> Hello, {this.props.email}! </div>
        <br>
        </br>
        <EnhancedTable email={this.props.email} id={this.props.id}/>
        </div> }
        </div> 
        
    );
  }
}

export default Feed;
