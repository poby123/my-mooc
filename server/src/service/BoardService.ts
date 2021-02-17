import { Board } from '../entity/Board';
import { Connection, Repository } from 'typeorm';
import { Member } from '../entity/Member';
import { Category } from '../entity/Category';

export class BoardService {

    private boardRepository: Repository<Board>;

    constructor(connection: Connection) {
        this.boardRepository = connection.getRepository(Board);
    }

    public async add(writer: Member, content: string, category: Category, files?: JSON, favorite?: number) {
        let board = new Board();
        board.writer = writer;
        board.content = content;
        board.category = category;
        (files) && (board.files = files);
        (favorite) && (board.favorite = favorite);

        return await this.boardRepository.save(board);
    }

    public async getByWriter(writer: Member) {
        return await this.boardRepository.find({ writer: writer });
    }

    public async getById(id: number) {
        return await this.boardRepository.findOne(id);
    }

    public async delete(board: Board) {
        return await this.boardRepository.remove(board);
    }
}