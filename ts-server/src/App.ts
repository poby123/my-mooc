import express from 'express';
import { UserController } from './controller/UserController';
import { Connection } from 'typeorm';
import bodyParser from 'body-parser';
import { OrganizationController } from './controller/OrganizationController';
class App {
    public application: express.Application;

    constructor() {
        this.application = express();
        this.application.use(bodyParser.json())
        this.application.use('/user', new UserController().router);
        this.application.use('/organization', new OrganizationController().router);
        this.router();
    }

    private router(): void {
        this.application.get('/', (req: express.Request, res: express.Response) => {
            res.send('Hello!');
        })
    }
}

export default App;