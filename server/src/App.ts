import express from 'express';
import RestController from './Controller/RestController';

class App {
    public application: express.Application;

    constructor() {
        this.application = express();
        this.application.use('/rest', new RestController().router);
        this.router();
    }

    private router(): void {
        this.application.get('/', (req: express.Request, res: express.Response) => {
            res.send('Hello!');
        })
    }
}

export default App;