import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { registerWebSocket } from './api';

function App() {
    registerWebSocket(handleData);

    /**
     * @param {any} data
     */
    function handleData(data) {
        // eslint-disable-next-line no-console
        console.log(data);
    }

    return (
        <div>
            Hello World
        </div>
    );
}
ReactDOM.render(<App />, document.getElementById('root'));

