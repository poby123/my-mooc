import express, { Request, Response } from 'express';
import { Connection } from 'typeorm';
import { Organization } from '../entity/Organization';
import { CategoryService } from '../service/CategoryService';
import { CommentService } from '../service/CommentService';
import { MemberService } from '../service/MemberService';
import { OrganizationService } from '../service/OrganizationService';

export class OrganizationController {
    public router: express.Router;
    private organizationService: OrganizationService;
    private memberService : MemberService;
    private categoryService : CategoryService;
    private commentService : CommentService;

    constructor(connection: Connection) {
        this.router = express.Router();
        this.organizationService = new OrganizationService(connection);
        this.memberService = new MemberService(connection);
        this.categoryService = new CategoryService(connection);
        this.commentService = new CommentService(connection);
        this.routing();
    }

    private routing(): void {
        this.router.get('/', async (req: Request, res: Response) => {
            const id: number = +<string>req.query.id;
            console.log(id);
            
            try {
                const result = await this.organizationService.getById(id);
                res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })

        this.router.post('/', async (req: Request, res: Response) => {
            try {
                const { title } = req.body;
                const result = await this.organizationService.add(title);
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
                let organization = <Organization>await this.organizationService.getById(id);

                const targetMembers = await this.memberService.getByOrganization(organization);
                const targetComments = targetMembers.map(async (member)=>{
                    return await this.commentService.getByWriter(member);
                });
                console.log(targetComments);

                // await this.memberService.deleteMembers(targetMembers);

                // const result = await this.organizationService.delete(organization);
                // res.send(result);
            } catch (e) {
                res.send('Error' + e);
            }
        })
    }

}