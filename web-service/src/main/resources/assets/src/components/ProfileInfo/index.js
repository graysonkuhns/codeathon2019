import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    root: {
        padding: ".grem",
        width: "98%"
    },
    avatar: {
        width: 100,
        height: 100,
        margin: '1rem'
    }
})

export default function ProfileInfo(props) {
    const classes = useStyles();

    return (
        <Paper className={classes.root}>
            <Grid justify="center" container alignItems="center">
                <Grid item sm={3} lg={3} >
                    <Avatar alt="gihub user" src={props.avatarUrl} className={classes.avatar} />
                </Grid>
                <Grid item sm={6} lg={9}>
                    <Typography variant="body1">{props.bio}</Typography>
                </Grid>
            </Grid>
        </Paper>
    )
}