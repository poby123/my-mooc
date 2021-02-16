import express, { Request, Response } from 'express';
import { Connection } from 'typeorm';
// const RestController = express.Router();

class RestController {
    public router: express.Router;
    private connection : Connection;

    constructor(connection: Connection) {
        this.router = express.Router();
        this.connection = connection;
        this.routing();
    }

    private routing(): void {
        this.router.get('/', (req: Request, res: Response) => {
            res.send('Hello Rest');
        })
    }

}

export default RestController;