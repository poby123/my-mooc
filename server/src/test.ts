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
    /* Organization Service Tests */
    const organizationService = new OrganizationService(connection);
    /** Add */
    // const os_res1 = await organizationService.add('test-organization');
    // console.log(os_res1); 

    /** getById */
    const os_res2 = <Organization>await organizationService.getById(5);
    // console.log(os_res2); 

    /** Delete */
    // const os_res3 = await organizationService.delete(os_res2);
    // console.log(os_res3); 



    /* Member Service Tests */
    const memberService = new MemberService(connection);
    /** Add */
    // const ms_res1 = await memberService.add(os_res2, 'test-name', 'test-password', 'test-image-link');
    // console.log('Add Result : ', ms_res1);

    /** getById */
    const ms_res2 = <Member>await memberService.getById(2);
    // console.log('Get Result : ', ms_res2);

    /** Delete */
    // const ms_res3 = await memberService.delete(ms_res2);
    // console.log('Delete Result : ', ms_res3);



    /* Category Service Tests */
    const categoryService = new CategoryService(connection);
    /** Add */
    // const cs_res1 = await categoryService.add(os_res2, 'category-test-name');
    // console.log('Add Result : ', cs_res1);

    /** getById */
    const cs_res2 = <Category>await categoryService.getById(2);
    // console.log('Get Result : ', cs_res2);

    /** Delete */
    // const cs_res3 = await categoryService.delete(cs_res2);
    // console.log('Delete Result : ', cs_res3);



    /* Board Service Tests */
    const boardService = new BoardService(connection);
    // /** Add */ 
    // const bs_res1 = await boardService.add(ms_res2, 'test-content', cs_res2);
    // console.log('Add Result : ', bs_res1);

    /** getById */
    const bs_res2 = <Board>await boardService.getById(12);
    // console.log('Get Result : ', bs_res2);

    /** Delete */
    // const bs_res3 = await boardService.delete(bs_res2);
    // console.log('Delete Result : ', bs_res3);



    /* Comment Service Tests */
    const commentService = new CommentService(connection);
    // /** Add */ 
    const comment_res1 = await commentService.add(ms_res2, 'test-comment-content', bs_res2);
    console.log('Add Result : ', comment_res1);

    /** getById */
    // const comment_res2 = await commentService.getByBoard(bs_res2);
    // console.log('Get Result : ', comment_res2);

    /** Delete */
    // const comment_res3 = await commentService.delete(comment_res2);
    // console.log('Delete Result : ', comment_res3);


}).catch((e) => {
    console.log('ERROR : ', e);
});