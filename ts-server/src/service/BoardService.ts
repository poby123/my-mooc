import { Board } from '../entity/Board';
import { Connection, Repository } from 'typeorm';
import { Member } from '../entity/Member';
import { Category } from '../entity/Category';
import ConnectionProvider from '../provider/ConnectionProvider';
export class BoardService {

    private boardRepository: Repository<Board>;
    private static service : BoardService;

    private constructor() {
        this.boardRepository = ConnectionProvider.connection().getRepository(Board);
    }

    public static getInstance(){
        if(!BoardService.service){
            BoardService.service = new BoardService();
        }
        return BoardService.service;
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

    public async getByCategory(category: Category) {
        return await this.boardRepository.find({ category: category });
    }

    public async delete(board: Board) {
        return await this.boardRepository.remove(board);
    }

    public async deleteBoards(boards: Board[]) {
        boards && boards.forEach(async (board) => {
            await this.delete(board);
        })
    }
}