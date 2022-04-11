import axios from "axios";
import React, { Component } from "react";
import EnhancedTable from "./table";
import { Button } from "@mui/material";
import Login from "./login";

class AdminPanel extends Component {

        constructor() {
        super();

        this.state = {
            redirect:false,
        }
    }

    componentDidMount(){
        
    }




  render() {

    // const {products} = this.state;

    return (
        <div>
        { this.state.redirect ? <Login/> : <div>
        <div> Welcome, Admin! </div>
        <br>
        </br>
        
        </div> }
        </div> 
        
    );
  }
}

export default AdminPanel;
