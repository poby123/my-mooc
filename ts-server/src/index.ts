import "reflect-metadata";
import { createConnection } from 'typeorm';
import ConnectionProvider from './provider/ConnectionProvider';
import App from './App';

ConnectionProvider.setConnection().then(() => {
    const app = new App().application;
    app.listen(3001, () => {
        console.log('Server listening on port 3001');
    });
}).catch((e) => {
    console.log(e);
})