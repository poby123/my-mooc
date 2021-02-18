import "reflect-metadata";
import { createConnection } from 'typeorm';
import { Board } from "./entity/Board";
import { Category } from "./entity/Category";
import { Member } from './entity/Member';
import { Comment } from './entity/Comment';
import { Organization } from "./entity/Organization";
import initService from "./service/ServiceInit";
import ConnectionProvider from './provider/ConnectionProvider';
import ServiceProvider from './provider/ServiceProvider';

initService().then(async () => {
    const organization = <Organization>await ServiceProvider.organizationService.getById(7);
    const member = <Member>await ServiceProvider.memberService.getById('test-id5');
    const board = <Board>await ServiceProvider.boardService.getById(2);
    const category = <Category>await ServiceProvider.categoryService.getById(2);
    
    console.log(organization);
    console.log(category);
    
    
    
    // await commentService.add(member, 'test-comment2', board);

    // let res = await ServiceProvider.boardService.add(member, 'test-conent', category);
    // await ServiceProvider.boardService.add(member, 'test-conent', category);
    // await ServiceProvider.boardService.add(member, 'test-conent', category);

    // console.log(res);

}).catch((e) => {

})

    // const categories = <Category[]>await categoryService.getByOrganization(organization);
    // let boards: any;
    // categories.forEach(async (category) => {
    //     boards = [...boards, boardService.getByCategory(category)];
    // });


