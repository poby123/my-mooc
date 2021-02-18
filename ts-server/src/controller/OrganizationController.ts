import express, { Request, Response } from 'express';
import { Organization } from '../entity/Organization';
import OrganizationService from '../service/OrganizationService';

export class OrganizationController {
    public router: express.Router;

    constructor() {
        this.router = express.Router();
        this.routing();
    }

    private routing(): void {
        this.router.get('/', async (req: Request, res: Response) => {
            const id: number = +<string>req.query.id;
            console.log(id);
            
            try {
                const result = await OrganizationService.getById(id);
                res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })

        this.router.post('/', async (req: Request, res: Response) => {
            try {
                const { title } = req.body;
                const result = await OrganizationService.add(title);
                res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })

        this.router.put('/', (req: Request, res: Response) => {

        })

        this.router.delete('/', async (req: Request, res: Response) => {
            try {
                const id = +<string>req.body.id;
                let organization = <Organization>await OrganizationService.getById(id);

                
            } catch (e) {
                res.send('Error' + e);
            }
        })
    }

}