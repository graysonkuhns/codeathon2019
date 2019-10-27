import React from 'react';
import { Grid, Typography, Slide } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Search from "../Search";
import Title from "../Title";
import { makeStyles } from '@material-ui/core/styles';
import "chart.js";
import { PieChart } from 'react-chartkick';

const useStyles = makeStyles(() => {
    return {
        root: {
            flexGrow: 1
        },
        paper: {
            marginTop: "3rem",
            padding: "1.5rem",
        },
    }
});

/**
 * @param {{ data: { repositoryLanguages: any; }; }} props
 */
function ChartDisplay(props) {
    const classes = useStyles();

    let data;
    if (props.data && props.data.repositoryLanguages) {
        const langs = props.data.repositoryLanguages;
        data = Object.keys(langs).map(key => [key, langs[key]]);
    }

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

            <Slide in={!!data} timeout={1000} direction="up" >
                <Grid justify="center" container spacing={3}>
                    <Grid item xs={12} md={12} lg={6}>
                        <Title>Owned Repositories</Title>
                        <PieChart data={data} />
                    </Grid>

                    <Grid item xs={12} md={12} lg={6}>
                        <Title>Public Contributions</Title>
                        <PieChart data={data} />
                    </Grid>
                </Grid>
            </Slide>
        </Paper>
    );
}

export default ChartDisplay;