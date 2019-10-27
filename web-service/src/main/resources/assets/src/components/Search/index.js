import React, { useState } from 'react';
import Paper from '@material-ui/core/Paper';
import SearchIcon from '@material-ui/icons/Search';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';
import { makeStyles } from '@material-ui/core/styles';
import { Button } from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  root: {
    padding: '2px 4px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    width: 400
  },
  input: {
    marginLeft: theme.spacing(1),
    flex: 1,
  },
  iconButton: {
    padding: 10,
  },
  divider: {
    height: 28,
    margin: 4,
  },
}));


function SearchBar(props) {
    const [value, setValue] = useState('');
    const classes = useStyles();

    /**
     * @param {KeyboardEvent} event
     */
    function handleUpdate(event) {
        setValue(/** @type {HTMLInputElement} */(event.target).value)
    }

    return (
        <>
            <Paper className={classes.root}>
                <InputBase
                        className={classes.input}
                        placeholder="Search…"
                        value={value}
                        onChange={handleUpdate.bind(this)}
                        inputProps={{ 'aria-label': 'search' }}
                    />
                <IconButton className={classes.iconButton} aria-label="search" onClick={() => {props.handleSearch.bind(this, value)}}>
                    <SearchIcon />
                </IconButton>
            </Paper>
        </>
    );
}

export default SearchBar;
