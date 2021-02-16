import express from 'express';
import RestController from './controller/RestController';
import { Connection } from 'typeorm';
class App {
    public application: express.Application;

    constructor(connection: Connection) {
        this.application = express();
        this.application.use('/rest', new RestController(connection).router);
        this.router();
    }

    private router(): void {
        this.application.get('/', (req: express.Request, res: express.Response) => {
            res.send('Hello!');
        })
    }
}

export default App;