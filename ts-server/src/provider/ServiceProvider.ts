import { BoardService } from "../service/BoardService";
import { CategoryService } from "../service/CategoryService";
import { CommentService } from "../service/CommentService";
import { MemberService } from "../service/MemberService";
import { OrganizationService } from "../service/OrganizationService";

export default class ServiceProvider {
    public static boardService: BoardService;
    public static categoryService: CategoryService;
    public static commentService: CommentService;
    public static memberService: MemberService;
    public static organizationService: OrganizationService;
}