import React, { useState } from 'react';
import { Component } from "react";
import axios from 'axios';
import { TabContent, TabPane, Nav, NavItem, NavLink, Card, Button, CardTitle, CardText, Row, Col, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import classnames from 'classnames';

const HeaderComponent = (props) => {
  const [activeTab, setActiveTab] = useState('commit');
  const [userName, setUserName] = useState("");
  const [gitRepo, setGitRepo] = useState("");

  const toggle = tab => {
    if (activeTab !== tab) setActiveTab(tab);
  }

  const getCommits = (e) => {
    console.log("click", userName, gitRepo);

    // const response = axios.get(
    //   'https://localhost:8080/v1/commits/days/get&usrName=repositoryUrl=',
    // );
    axios.get('http://localhost:8080/api/v1/commits/days/get', { params: { userName: userName, repositoryUrl: gitRepo } }).then(response => console.log(response.data))

  }

  return (
    <div>
      <Nav tabs>
        <NavItem>
          <NavLink
            className={classnames({ active: activeTab === 'commit' })}
            onClick={() => { toggle('commit'); }}
          >
            OneDay OneCommit
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({ active: activeTab === 'test' })}
            onClick={() => { toggle('test'); }}
          >
            Test
          </NavLink>
        </NavItem>
      </Nav>
      <TabContent activeTab={activeTab}>
        <TabPane tabId="commit">
          <Row>
            <Col sm="2">
              <Form onSubmit={getCommits()}>
                <FormGroup>
                  <Label for="Github Name">Github Name</Label>
                  <Input type="text" name="githubName" id="githubName" placeholder="Enter your Github Name" onChange={v => setUserName(v.target.value)} />
                </FormGroup>
                <FormGroup>
                  <Label for="Github RepositoryUrl">Github ReposiotryUrl</Label>
                  <Input type="text" name="githubRepoUrl" id="githubRepoUrl" placeholder="Enter your Github Repository Url" onChange={v => setGitRepo(v.target.value)} />
                </FormGroup>
                <Button>Get Committed Date</Button>
              </Form>
            </Col>
          </Row>
        </TabPane>
        <TabPane tabId="test">
          <div> Test Tab </div>
        </TabPane>
      </TabContent>
    </div>
  );
}

export default HeaderComponent;