import React, { useState, useEffect } from 'react';
import { Container, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import axios from 'axios';
import Calendar from 'react-calendar';

const MainComponent = (props) => {
  const [userName, setUserName] = useState("");
  const [gitRepo, setGitRepo] = useState("");
  const [commitDates, setCommitDates] = useState([]);

  useEffect(() => {
    console.log(commitDates);
  }, [commitDates]);

  const getCommits = (e) => {
    e.preventDefault();
    console.log("getCommit", userName, gitRepo);
    axios.get('http://localhost:8080/api/v1/commits/days/get', { params: { userName: userName, repositoryUrl: gitRepo } }).then(response => {
      console.log(response.data);
      setCommitDates(response.data);
    })
  }

  return (
    <Container>
      <Form>
        <FormGroup>
          <Label for="Github Name">Github Name</Label>
          <Input type="text" name="githubName" id="githubName" placeholder="Enter your Github Name" onChange={v => setUserName(v.target.value)} />
        </FormGroup>
        <FormGroup>
          <Label for="Github RepositoryUrl">Github ReposiotryUrl</Label>
          <Input type="text" name="githubRepoUrl" id="githubRepoUrl" placeholder="Enter your Github Repository Url" onChange={v => setGitRepo(v.target.value)} />
        </FormGroup>
        <Button onClick={e => getCommits(e)}>Get Committed Date</Button>
      </Form>
      <div>{commitDates}</div>
    </Container>
  );
}


export default MainComponent;