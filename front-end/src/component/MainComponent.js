import React, { useState, useEffect } from 'react';
import { Container, Button, Form, FormGroup, Label, Input, ListGroup, ListGroupItem } from 'reactstrap';
import axios from 'axios';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment'
import 'moment/locale/ko'


const MainComponent = (props) => {
  const [userName, setUserName] = useState("");
  const [gitRepo, setGitRepo] = useState("");
  const [commitDates, setCommitDates] = useState([]);
  const [events, setEvents] = useState([]);

  const [firstDate, setFirstDate] = useState(new Date());
  useEffect(() => {
    console.log(commitDates);
  }, [commitDates]);

  useEffect(() => {
    console.log(firstDate);
  }, [firstDate]);

  const getCommits = (e) => {
    e.preventDefault();
    console.log("getCommit", userName, gitRepo);
    axios.get('http://localhost:8080/api/v1/commits/days/get', { params: { userName: userName, repositoryUrl: gitRepo } }).then(response => {
      console.log(response.data);
      setCommitDates(response.data);
      response.data.map(date => setEvents(prevEvents => [...prevEvents, getEvents(date, userName)]));
      setFirstDate(new Date(response.data[0]));
      console.log(new Date(response.data[0]));
      console.log(firstDate);
    })
  }

  const getEvents = (date, name) => {
    return {
      title: name,
      start: new Date(date),
      end: new Date(date),
      allDay: true,
    }
  }


  moment.locale('ko')
  const localizer = momentLocalizer(moment)

  return (
    <Container>
      <h1>Oneday OneCommit</h1>
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
      <div>
        <Calendar
          localizer={localizer}
          events={events}
          startAccessor="start"
          endAccessor="end"
          style={{ height: 500 }}
          date={firstDate}
          onNavigate={(date) => { setFirstDate(date) }}
        />
      </div>
    </Container>
  );
}


export default MainComponent;