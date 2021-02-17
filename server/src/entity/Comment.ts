import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, OneToOne, JoinColumn, OneToMany, ManyToOne } from 'typeorm';
import { Board } from './Board';
import { Member } from './Member';

@Entity()
export class Comment {
    @PrimaryGeneratedColumn()
    id!: number;

    @ManyToOne(() => Member, writer => writer.comment)
    writer!: Member;

    @CreateDateColumn()
    regDate!: Date;

    @Column()
    content!: string;

    @ManyToOne(() => Board, board => board.comment)
    board!: Board;
}
