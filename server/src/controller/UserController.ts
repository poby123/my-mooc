import express, { Request, Response } from 'express';
import { Connection } from 'typeorm';
import { Organization } from '../entity/Organization';
import { MemberService } from '../service/MemberService';
import { Member } from '../entity/Member';

export class UserController {
    public router: express.Router;
    private memberService: MemberService;

    constructor(connection: Connection) {
        this.router = express.Router();
        this.memberService = new MemberService(connection);
        this.routing();
    }

    private routing(): void {
        this.router.get('/', async (req: Request, res: Response) => {
            const userId = <string>req.query.id;
            const userInfo = await this.memberService.getById(userId)
            res.send(userInfo);
        })

        this.router.post('/', async (req: Request, res: Response) => {
            try {
                const { organizationId, id, name, password, image } = req.body;
                const organization = new Organization();
                organization.id = organizationId;
                const result = await this.memberService.add(id, organization, name, password, image);
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
                const member = <Member>await this.memberService.getById(id);
                const result = await this.memberService.delete(member);
                res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })
    }

}