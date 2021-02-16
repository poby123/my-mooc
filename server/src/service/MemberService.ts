import { Connection, Repository } from "typeorm";
import { Member } from '../entity/Member';
import { Organization } from "../entity/Organization";

export class MemberService {

    private memberRepository: Repository<Member>;

    constructor(connection: Connection) {
        this.memberRepository = connection.getRepository(Member);
    }

    public async add(organization: Organization, name: string, password: string, image: string) {
        let member = new Member();
        member.organization = organization;
        member.name = name;
        member.password = password;
        member.image = image;

        return await this.memberRepository.save(member);
    }

    public async getById(id: number) {
        return await this.memberRepository.findOne(id);
    }

    public async delete(member: Member) {
        return await this.memberRepository.remove(member);
    }
}