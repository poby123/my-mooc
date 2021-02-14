import express, { Request, Response } from 'express';

// const RestController = express.Router();

class RestController {
    public router: express.Router;
    constructor() {
        this.router = express.Router();
        this.routing();
    }

    private routing(): void {
        this.router.get('/', (req: Request, res: Response) => {
            res.send('Hello Rest');
        })
    }

}

export default RestController;