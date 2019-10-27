import React, { useState } from 'react';
import { Component } from 'react';

import SearchIcon from '@material-ui/icons/Search';
import InputBase from '@material-ui/core/InputBase';
import Toolbar from '@material-ui/core/Toolbar';
import { makeStyles } from '@material-ui/core/styles';
import { Button } from '@material-ui/core';

var useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
        display: 'none',
        [theme.breakpoints.up('sm')]: {
            display: 'block',
        },
    },
    search: {
        position: 'relative',
        borderRadius: "50%",
        backgroundColor: "#eee",
        marginLeft: 0,
    },
    searchIcon: {
        width: theme.spacing(7),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    inputRoot: {
        color: 'inherit',
    },
    inputInput: {
        padding: theme.spacing(1, 1, 1, 7),
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            width: 120,
            '&:focus': {
                width: 200,
            },
        },
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
            <Toolbar className={classes.root}>
                <div className={classes.search}>
                    <div className={classes.searchIcon}>
                        <SearchIcon />
                    </div>
                    <InputBase
                        placeholder="Search…"
                        value={value}
                        onChange={handleUpdate.bind(this)}
                        classes={{
                            root: classes.inputRoot,
                            input: classes.inputInput,
                        }}
                        inputProps={{ 'aria-label': 'search' }}
                    />

                </div>
            </Toolbar>
            <Button onClick={props.handleSearch.bind(this, value)}>Get results</ Button>
        </>
    );
}

export default SearchBar;
