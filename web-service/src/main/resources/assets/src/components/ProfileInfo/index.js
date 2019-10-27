import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import Title from '../Title';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    root: {
        padding: ".5rem",
        width: "98%"
    },
    avatar: {
        width: 100,
        height: 100,
        margin: '1rem'
    },
    bio: {
        margin: "1rem"
    },
    title: {
        marginLeft: 0
    }
})

export default function ProfileInfo(props) {
    const classes = useStyles();

    return (
        <Paper className={classes.root}>
            <Grid justify="center" container alignItems="center">
                <Grid item sm={3} lg={3} justify="center" container alignItems="center">
                    <Avatar alt="gihub user" src={props.avatarUrl} className={classes.avatar} />
                    <Title className={classes.title}>{props.name}</Title>
                </Grid>
                <Grid item sm={6} lg={9}>
                    <Typography variant="body1" className={classes.bio}>{props.bio}</Typography>
                </Grid>
            </Grid>
        </Paper>
    )
}