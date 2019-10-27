import '@babel/polyfill';
import * as $ from 'jquery';

import * as socket from 'atmosphere.js';

/**
 * @typedef {Object} Data
 * @property {string} languages
 */

/**
 * Send a request to get a Github analysis
 * @param {string} username 
 */
export function sendGithubAnalysisRequest(username) {
    $.ajax({
        type: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "/api/analysis/" + username
    });
}


/**
 * @param {(data: Data) => void} callback
 */
export function registerWebSocket(callback) {
    const request = new socket.AtmosphereRequest();
    request.url = document.location.toString() + 'websocket/data';
    request.contentType = "application/json";
    request.trackMessageLength = true;
    request.shared = true;
    request.transport = 'websocket';
    request.fallbackTransport = 'long-polling';

    request.onMessage = (response) => {
        callback(JSON.parse(response.responseBody));
    }

    socket.subscribe(request);
}
