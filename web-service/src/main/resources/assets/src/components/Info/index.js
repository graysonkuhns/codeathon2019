import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Title from '../Title';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(() => ({
    root: {
        width: '98%'
    }

}));

export default function info() {
    const classes = useStyles();
    return (
        <Paper className={classes.root}>
            <Grid container justify="center">
                <Grid item sm={12} lg={6}>
                    <Title variant="h3">Latest Commit:</Title>
                    <Link href="https://github.com/JohnIrle">Click me</Link>
                </Grid>
                <Grid item sm={12} lg={6}>
                    <Title variant='h3'>Number of files by Language:</Title>
                </Grid>
            </Grid>
        </Paper>
    )
}

