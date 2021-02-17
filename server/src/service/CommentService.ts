import { Connection, Repository } from "typeorm"
import { Comment } from "../entity/Comment"
import { Member } from "../entity/Member";
import { Board } from '../entity/Board';

export class CommentService {
    private commentRepository: Repository<Comment>;

    constructor(connection: Connection) {
        this.commentRepository = connection.getRepository(Comment);
    }

    public async add(writer: Member, content: string, board: Board) {
        let comment = new Comment();
        comment.writer = writer;
        comment.content = content;
        comment.board = board;

        return await this.commentRepository.save(comment);
    }

    public async getByBoard(board: Board) {
        return await this.commentRepository.find({ board: board });
    }

    public async getByWriter(writer: Member) {
        return await this.commentRepository.find({ writer: writer });
    }

    public async delete(comment: Comment) {
        return await this.commentRepository.remove(comment);
    }
}