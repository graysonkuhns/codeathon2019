import React from 'react';
import { Grid, Typography } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Search from "../Search";
import Title from "../Title";
import { makeStyles, withStyles } from '@material-ui/core/styles';
import "chart.js";
import { PieChart } from 'react-chartkick';
import { getThemeProps } from '@material-ui/styles';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  paper: {
    marginTop: "3rem",
    padding: "1.5rem",   
  },
  
}));

function ChartDisplay(props) {
  const classes = useStyles();
  return (
    <Paper className={classes.paper}>
      <Grid justify="center" container>
        <Grid item>
          <Typography variant="h1">Welcome to DeVet</Typography>
        </Grid>
      </Grid>
      <Grid justify="center" container>
        <Grid item>
          <Search />
        </Grid>
      </Grid>
      <Grid justify="center" container spacing={3}>
        <Grid item xs={12} md={12} lg={6}>
          <Title>Owned Repositories</Title>
          <PieChart data={props.data} />
        </Grid>

        <Grid item xs={12} md={12} lg={6}>
          <Title>Public Contributions</Title>
          <PieChart data={props.data} />
        </Grid>
      </Grid>
    </Paper>
  );
}

export default ChartDisplay;