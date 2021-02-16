import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, OneToOne, JoinColumn } from 'typeorm';
import { Board } from './Board';
import { Member } from './Member';

@Entity()
export class Comment {
    @PrimaryGeneratedColumn()
    id!: number;

    @OneToOne(type => Member)
    @JoinColumn()
    writer!: Member;

    @CreateDateColumn()
    regDate!: Date;

    @Column()
    content!: string;

    @OneToOne(type => Board)
    @JoinColumn()
    board!: Board;
}
