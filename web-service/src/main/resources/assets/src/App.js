import { Grid, Typography } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import React from 'react';
import NavBar from './components/NavBar';
import ChartDisplay from './components/ChartDisplay'
import CssBaseline from '@material-ui/core/CssBaseline';

import BackGround from "./dot-grid.png";




class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      data: [['Java', 18], ['JavaScript', 49], ['Python', 9], ['go', 24]]
    }
    this.handleSearch = this.handleSearch.bind(this);
  }
  
  handleSearch() {
      
  }
  
    render(){
    return(
      <div style={{background: BackGround , backgroundRepeat: "repeat"}}>
        <CssBaseline/>
        <NavBar/>
        <ChartDisplay data={this.state.data} handleSearch={this.handleSearch()}/>
      </div>
    )
    }
}

export default App;
