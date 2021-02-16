import "reflect-metadata";
import { createConnection } from 'typeorm';
import App from './App';

createConnection().then(async (connection) => {
    const app = new App(connection).application;
    app.listen(3001, () => {
        console.log('Server listening on port 3001');
    });
}).catch((e) => {
    console.log('ERROR : ', e);
});