import axios from "axios";
import React, { Component } from "react";
import EnhancedTableClient from "./clienttable";
import { Button } from "@mui/material";
import Login from "./login";

class AdminPanel extends Component {

    constructor() {
        super();
    
        this.state = {
          name: "",
          quantity: 0,
          price: 0,
          clientName: "",
          listClient:false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleAdd = this.handleAdd.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleList = this.handleList.bind(this);
    }


    handleChange(event) {
        let target = event.target;
        let value = target.type = target.value;
        let name = target.name;
    
        this.setState({
          [name]: value
        })
      }

    handleAdd(){

        if ( this.state.name === "" || this.state.quantity <= 0 || this.state.price <= 0 )
        {
            alert("Invalid values..")
            return;
        }

        // Add product to DB
        axios.post("http://localhost:1234/addproduct",
        {
            id: 10, // this value will be randomly generated
            name: this.state.name,
            quantity: this.state.quantity,
            price: this.state.price
        }
        ).then ( function(response){
            if( response.data === " <b> Success! </b> " )
                alert("Success!");
            else
                alert("Unexpected error.\nFailed to add product.\n")
        } ).catch( function(error){
            alert("Product name already exists..Did you mean to use update?")
        } )
    }

    handleUpdate(){

        if ( this.state.name === "" || this.state.quantity <= 0 || this.state.price <= 0 )
        {
            alert("Invalid values..")
            return;
        }
        
        if (this.props.sanitise === false){
            alert("Invalid values..")
            return;
        }

        // Check if product exists and update
        let self = this;

        axios.get("http://localhost:1234/findproduct?name=" + this.state.name)
        .then( function( response ){
            if ( response.data !== "Product not found" )
            {
                // response.id - id
                axios.post("http://localhost:1234/updateproduct",
                {
                    id: response.data.id,
                    name: self.state.name,
                    quantity: self.state.quantity,
                    price: self.state.price
                } ).then( function( response2 )
                {
                    if ( response2.data === " <b> Success! </b> " )
                        alert("Success!");
                    else
                        alert("Unexpected error. Failed to update product");
                } )
            }
            else
            alert("Cannot update product. Product doesn't exist.\n Make sure that the name is typed correctly")
        } )
    }

    handleList(){
        if ( this.state.listClient === false )
        this.setState( { listClient:true } )
        else
        this.setState( { listClient:false } )
    }


  render() {

    return (
        <div>
        <div> Welcome, Admin! </div>
        <br>
        </br>
        <div className="formCenter">
           <div className="formField">
             <label className="formFieldLabel">
               Product name
             </label>
             <input
               type="text"
               id="name"
               className="formFieldInput"
               placeholder="Enter product name"
               name="name"
               value={this.state.name}
               onChange={this.handleChange}
             />
           </div>

           <div className="formField">
             <label className="formFieldLabel">
               Quantity
             </label>
             <input
               type="number"
               id="quantity"
               className="formFieldInput"
               placeholder="Enter product quantity"
               name="quantity"
               value={this.state.quantity}
               onChange={this.handleChange}
             />
           </div>


           <div className="formField">
             <label className="formFieldLabel">
                Price
             </label>
             <input
               type="number"
               id="price"
               className="formFieldInput"
               placeholder="Enter product price"
               name="price"
               value={this.state.price}
               onChange={this.handleChange}
             />
           </div>

           <div className="formField">
             <button className="formFieldButton" onClick={this.handleAdd}>Add Product</button>{" "}
           </div>

           
           <div className="formField">
             <button className="formFieldButton">Delete Product</button>{" "}
           </div>

           
           <div className="formField">
             <button className="formFieldButton" onClick={this.handleUpdate}>Update Product</button>{" "}
           </div>

           <br>
           </br>
           <br>
           </br>


           <div className="formField">
             <button className="formFieldButton" onClick={this.handleList}>List Clients</button>{" "}
             <br>
            </br>
             { this.state.listClient ? <EnhancedTableClient/> : null } 
           </div>

       </div> 
        </div>         
    );
  }
}

export default AdminPanel;
