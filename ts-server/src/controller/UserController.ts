import express, { Request, Response } from 'express';
import { Connection } from 'typeorm';
import { Organization } from '../entity/Organization';
import MemberService from '../service/MemberService';
import { Member } from '../entity/Member';

export class UserController {
    public router: express.Router;

    constructor() {
        this.router = express.Router();
        this.routing();
    }

    private routing(): void {
        this.router.get('/', async (req: Request, res: Response) => {
            const userId = <string>req.query.id;
            const userInfo = await MemberService.getById(userId)
            res.send(userInfo);
        })

        this.router.post('/', async (req: Request, res: Response) => {
            try {
                const { organizationId, id, name, password, image } = req.body;
                const organization = new Organization();
                organization.id = organizationId;
                const result = MemberService.add(id, organization, name, password, image);
                res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })

        this.router.put('/', (req: Request, res: Response) => {

        })

        this.router.delete('/', async (req: Request, res: Response) => {
            try {
                const { id } = req.body;
                const member = <Member>await MemberService.getById(id);
                const result = await MemberService.delete(member);
                res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })
    }

}