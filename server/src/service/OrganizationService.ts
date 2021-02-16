import { Connection, Repository } from 'typeorm';
import { Organization } from '../entity/Organization';

export class OrganizationService {

    private organizationRepository: Repository<Organization>;

    constructor(connection: Connection) {
        this.organizationRepository = connection.getRepository(Organization);
    }

    public async add(title: string) {
        const organization = new Organization();
        organization.title = title;

        return await this.organizationRepository.save(organization);
    }

    public async getById(id: number) {
        return await this.organizationRepository.findOne({ id: id });
    }

    public async delete(organization: Organization) {
        return await this.organizationRepository.remove(organization);
    }

}