import ConnectionProvider from '../provider/ConnectionProvider';
import ServiceProvider from '../provider/ServiceProvider';
import { BoardService } from "./BoardService";
import { CategoryService } from './CategoryService';
import { CommentService } from './CommentService';
import { MemberService } from './MemberService';
import { OrganizationService } from './OrganizationService';

export default async function initService() {
    await ConnectionProvider.setConnection();
    const boardService = BoardService.getInstance();
    const categoryService = CategoryService.getInstance();
    const commentService = CommentService.getInstance();
    const memberService = MemberService.getInstance();
    const organizationService = OrganizationService.getInstance();

    ServiceProvider.boardService = boardService
    ServiceProvider.categoryService = categoryService;
    ServiceProvider.commentService = commentService;
    ServiceProvider.memberService = memberService;
    ServiceProvider.organizationService = organizationService;
    
    return { boardService, categoryService, commentService, memberService, organizationService };
}