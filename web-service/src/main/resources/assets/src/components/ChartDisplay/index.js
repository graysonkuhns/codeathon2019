import * as React from 'react';
import { useState, useEffect } from 'react';
import { Grid, Typography, Slide, Card, CardContent } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Search from '../Search';
import Title from '../Title';
import Info from '../Info';
import ProfileInfo from '../ProfileInfo'
import { makeStyles } from '@material-ui/core/styles';
import "chart.js";
import { PieChart } from 'react-chartkick';

const useStyles = makeStyles(() => ({
    root: {
        flexGrow: 1
    },
    paper: {
        margin: "3rem",
        padding: "3rem",
        backgroundColor: "#efefef"
    },
    title: {
        fontSize: "6vw",
        marginBottom: ".5rem"
    },
    card: {
        paddingTop: "2rem",
        paddingBottom: "3rem"
    },
    bigAvatar: {
        margin: 10,
        width: 60,
        height: 60,
    },
    searchBar: {
        marginTop: "2rem"
    },
    profile: {
        marginTop: '.5rem'
    }

}));

/**
 * @typedef {Object} ChartDisplayProps
 * @property {any} data 
 * @property {(username: string) => void} handleSearch
 */

/**
 * @param {ChartDisplayProps} props
 */
function ChartDisplay(props) {
    const classes = useStyles();

    const [freezeData, setFreezeData] = useState(false);
    const [data, setData] = useState();
    const [oldProps, setOldProps] = useState();

    useEffect(() => {
        if (oldProps != props) {
            updateData();
        }
    });

    function updateData() {
        if (props.data && props.data.repositoryLanguages && !freezeData) {
            const langs = props.data.repositoryLanguages;
            setData(Object.keys(langs).map(key => [key, langs[key]]));
            setOldProps(props);
        }
    }

    /**
     * @param {string} username
     */
    function handleSearch(username) {
        if (data) {
            setFreezeData(true);
            setTimeout(() => {
                setData(undefined);
                setFreezeData(false);
            }, 1000)
        }

        props.handleSearch(username);
    }

    return (
        <Paper className={classes.paper}>
            <Grid justify="center" container>
                <Grid item>
                    <Typography className={classes.title} variant="h1">Welcome to DeVet</Typography>
                </Grid>
            </Grid>
            <Grid justify="center" container>
                <Grid item>
                    <Search handleSearch={handleSearch} className={classes.searchBar} />
                </Grid>
            </Grid>
            <Slide in={!!data && data.length > 0 && !freezeData} timeout={1000} direction="up" style={{ display: "none" }}>
                <Grid justify="center" container spacing={3}>
                    <ProfileInfo avatarUrl={props.data.avatar_url} description={props.data.bio}
                        className={classes.profile} />
                    <Grid item xs={12} md={12} lg={6}>
                        <Card className={classes.card}>
                            <CardContent>
                                <Title>Owned Repositories</Title>
                                <PieChart data={data} />
                            </CardContent>
                        </Card>
                    </Grid>

                    <Grid item xs={12} md={12} lg={6}>
                        <Card className={classes.card}>
                            <CardContent>
                                <Title>Owned Repositories</Title>
                                <PieChart data={data} />
                            </CardContent>
                        </Card>
                    </Grid>
                    <Info />
                </Grid>
            </Slide>
        </Paper>
    );
}

export default ChartDisplay;