import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, OneToOne, JoinColumn } from 'typeorm';
import { Board } from './Board';

@Entity()
export class Comment {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column()
    writer!: string;

    @CreateDateColumn()
    regDate!: Date;

    @Column()
    content!: string;

    @OneToOne(type => Board)
    @JoinColumn()
    board!: Board;

}
