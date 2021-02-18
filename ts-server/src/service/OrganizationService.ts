import { Connection, Repository } from 'typeorm';
import { Organization } from '../entity/Organization';
import { Category } from '../entity/Category';
import { Member } from '../entity/Member';
import { Board } from '../entity/Board';
import ConnectionProvider from '../provider/ConnectionProvider';

export class OrganizationService {

    private organizationRepository: Repository<Organization>;
    public static service: OrganizationService;

    private constructor() {
        this.organizationRepository = ConnectionProvider.connection().getRepository(Organization);
    }

    public static getInstance() {
        if (!OrganizationService.service) {
            OrganizationService.service = new OrganizationService();
        }
        return OrganizationService.service;
    }

    public async add(title: string, category?: Category, member?: Member) {
        const organization = new Organization();
        organization.title = title;
        organization.category = category ? [category] : [];
        organization.member = member ? [member] : [];

        return await this.organizationRepository.save(organization);
    }

    public async getById(id: number) {
        return await this.organizationRepository.findOne(id);
    }

    public async delete(organization: Organization) {
        // comment
        // member
        // board
        // category

        // const categories = <Category[]>await CategoryService.getByOrganization(organization);
        // let boards: any;
        // categories.forEach(async (category) => {
        //     boards = [...boards, BoardService.getByCategory(category)];
        // });
        // const members = <Member[]>await MemberService.getByOrganization(organization);
        // const comments = await this.commentService.getByWriter();

        return await this.organizationRepository.remove(organization);
    }

}