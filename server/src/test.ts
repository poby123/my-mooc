import "reflect-metadata";
import { createConnection } from 'typeorm';
import { Board } from "./entity/Board";
import { Category } from "./entity/Category";
import { Member } from './entity/Member';
import { Comment } from './entity/Comment';
import { Organization } from "./entity/Organization";
import { BoardService } from './service/BoardService';
import { CategoryService } from "./service/CategoryService";
import { CommentService } from "./service/CommentService";
import { MemberService } from "./service/MemberService";
import { OrganizationService } from './service/OrganizationService';

createConnection().then(async (connection) => {
    const memberService = new MemberService(connection);
    let organization = new Organization();
    organization.id = 1;
    const members = await memberService.getByOrganization(organization);
    await memberService.deleteMembers(members);


}).catch((e) => {
    console.log('ERROR : ', e);
});