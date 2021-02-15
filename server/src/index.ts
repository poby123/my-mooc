import "reflect-metadata";
import { createConnection } from 'typeorm';
import { Organization } from './entity/Organization';
import App from './App';

const app = new App().application;

const connection = createConnection({
    "type": "mysql",
    "host": "localhost",
    "port": 3306,
    "username": "admin",
    "password": "12345678",
    "database": "mymooc2",
    "synchronize": true,
    "logging": false,
    entities: [
        __dirname + "/entity/*.ts"
    ]
}).then(async (connection) => {
    // let o = new Organization();
    // o.title = 'test';

    // await connection.manager.save(o);
    // let o = await connection.manager.findOne(Organization, 2);
    // await connection.manager.remove(o);
}).catch((e) => {
    console.log('ERROR : ', e);
});
app.listen(3001, () => {
    console.log('Server listening on port 3001');
});