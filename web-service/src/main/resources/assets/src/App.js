import * as React from 'react';
import NavBar from './components/NavBar';
import ChartDisplay from './components/ChartDisplay'
import CssBaseline from '@material-ui/core/CssBaseline';

import * as BackGround from "./dot-grid.png";
import { registerWebSocket } from './api';

class App extends React.Component {
    /**
     * @param {any} props
     */
    constructor(props) {
        super(props);

        this.state = {
            data: undefined
        }
        this.handleSearch = this.handleSearch.bind(this);
    }

    componentDidMount() {
        registerWebSocket(this.handleDataUpdate.bind(this));
    }

    handleSearch() {

    }

    /**
     * @param {any} newData
     */
    handleDataUpdate(newData) {
        this.setState({
            data: newData,
        });
    }

    render() {
        return (
            <div style={{ background: BackGround, backgroundRepeat: "repeat" }}>
                <CssBaseline />
                <NavBar />
                <ChartDisplay data={this.state.data} handleSearch={this.handleSearch()} />
            </div>
        )
    }
}

export default App;
