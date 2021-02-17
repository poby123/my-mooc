import { Connection, Repository } from 'typeorm';
import { Organization } from '../entity/Organization';
import { Category } from '../entity/Category';
import { Member } from '../entity/Member';

export class OrganizationService {

    private organizationRepository: Repository<Organization>;

    constructor(connection: Connection) {
        this.organizationRepository = connection.getRepository(Organization);
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
        return await this.organizationRepository.remove(organization);
    }

}