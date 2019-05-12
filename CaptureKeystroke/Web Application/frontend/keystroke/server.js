// Get dependencies

const path = require('path');
const http = require('http');
const express = require('express');

const app = express();

// Point static path to dist
app.use(express.static(path.join(__dirname, 'dist/keystroke')));

// Catch all other routes and return the index file
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/keystroke/index.html'));
});

/**
 * Get port from environment and store in Express.
 */
const port = process.env.PORT || '80';
app.set('port', port);

/**
 * Create HTTP server.
 */
const server = http.createServer(app);

/**
 * Listen on provided port, on all network interfaces.
 */
server.listen(port, () => console.log(`Admin Portal Running on localhost:${port}`));
