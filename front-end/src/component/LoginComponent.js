import React from 'react';
import { Component } from "react";
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

class LoginComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = { isToggleOn: true };

        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.setState(state => ({
            isToggleOn: !state.isToggleOn
        }));
    }

    render() {
        return (
            <Form>
                <FormGroup>
                    <Label for="User">Email</Label>
                    <Input type="text" name="userName" id="userName" placeholder="Enter your email" />
                </FormGroup>
                <Button onClick={this.handleClick}>Submit</Button>
            </Form>
        )
    }
}

export default LoginComponent;